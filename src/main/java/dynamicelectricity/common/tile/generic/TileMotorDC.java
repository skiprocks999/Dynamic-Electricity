/*
 * Converts FE to Joules
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.registry.DynamicElectricitySounds;
import electrodynamics.api.capability.ElectrodynamicsCapabilities;
import electrodynamics.prefab.properties.Property;
import electrodynamics.prefab.properties.PropertyType;
import electrodynamics.prefab.sound.SoundBarrierMethods;
import electrodynamics.prefab.sound.utils.ITickableSound;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.IComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentInventory.InventoryBuilder;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import electrodynamics.prefab.utilities.ElectricityUtils;
import electrodynamics.prefab.utilities.object.CachedTileOutput;
import electrodynamics.prefab.utilities.object.TransferPack;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileMotorDC extends GenericTile implements IEnergyStorage, ITickableSound {

	public static final int BRUSH_SLOT = 0;
	public static final int LUBE_SLOT = 1;
	
	public static final double CONVERSION_EFFICIENCY = 0.95;

	public static final int LUBRICANT_PER_MB = 200000;

	public final Property<Integer> maxFeConsumed;
	public final Property<Integer> feStored;
	public final Property<Double> joulesProduced;

	public final Property<Integer> lubricantRemaining;
	public final Property<Boolean> running;

	public final Property<Boolean> hasRedstoneSignal;

	protected CachedTileOutput output;

	private boolean isPlaying = false;

	public TileMotorDC(TileEntityType<?> tileEntityTypeIn, int feConsumed, int energyTier, String name) {
		super(tileEntityTypeIn);
		
		maxFeConsumed = property(new Property<>(PropertyType.Integer, "feconsumed", feConsumed));
		feStored = property(new Property<>(PropertyType.Integer, "festored", 0));
		joulesProduced = property(new Property<>(PropertyType.Double, "joulesproduced", feConsumed * CONVERSION_EFFICIENCY));

		lubricantRemaining = property(new Property<>(PropertyType.Integer, "lubricantremaining", 0));
		running = property(new Property<>(PropertyType.Boolean, "running", false));

		hasRedstoneSignal = property(new Property<>(PropertyType.Boolean, "redstonesignal", false));

		addComponent(new ComponentTickable(this).tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler(this));
		addComponent(new ComponentElectrodynamic(this, true, false).setOutputDirections(Direction.SOUTH).voltage(Math.pow(2, energyTier) * ElectrodynamicsCapabilities.DEFAULT_VOLTAGE));
		addComponent(new ComponentInventory(this, InventoryBuilder.newInv().inputs(2)).valid(machineValidator()).setDirectionsBySlot(0, Direction.EAST, Direction.WEST, Direction.UP));
		addComponent(new ComponentContainerProvider("container.motordc" + name, this).createMenu((id, player) -> new ContainerMotorDC(id, player, getComponent(IComponentType.Inventory), getCoordsArray())));
	}

	public void tickServer(ComponentTickable tickable) {
		if (hasRedstoneSignal.get()) {
			running.set(false);
			return;
		}

		ComponentInventory inventory = getComponent(IComponentType.Inventory);
		ComponentElectrodynamic electro = getComponent(IComponentType.Electrodynamic);
		Direction facing = getFacing();

		if (output == null) {
			output = new CachedTileOutput(level, getBlockPos().relative(facing));
		}

		if (tickable.getTicks() % 20 == 0) {
			output.update(worldPosition.relative(facing));
		}

		boolean canRun = false;

		ItemStack brush = inventory.getItem(BRUSH_SLOT);
		ItemStack lubricant = inventory.getItem(LUBE_SLOT);

		if (!brush.isEmpty() && feStored.get() >= maxFeConsumed.get()) {
			if (lubricantRemaining.get() > 0) {
				lubricantRemaining.set(lubricantRemaining.get() - 1);
				brush.setDamageValue(brush.getDamageValue() + 1);
				canRun = true;
			} else if (lubricantRemaining.get() <= 0 && !lubricant.isEmpty()) {
				inventory.removeItem(LUBE_SLOT, 1);
				lubricantRemaining.set(LUBRICANT_PER_MB);
			}

		}

		if (brush.getDamageValue() >= brush.getMaxDamage() && canRun) {
			inventory.removeItem(BRUSH_SLOT, 1);
		}

		running.set(canRun);

		if (!canRun) {
			return;
		}

		this.feStored.set(0);
		if (output.valid()) {
			TransferPack pack = TransferPack.joulesVoltage(joulesProduced.get(), electro.getVoltage());
			ElectricityUtils.receivePower(output.getSafe(), facing.getOpposite(), pack, false);
		}

	}

	public void tickClient(ComponentTickable tickable) {

		if (shouldPlaySound() && !isPlaying) {
			isPlaying = true;
			SoundBarrierMethods.playTileSound(DynamicElectricitySounds.SOUND_MOTORRUNNING.get(), this, true);
		}

	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction face) {
		Direction facing = getFacing();

		if (capability == CapabilityEnergy.ENERGY && face == facing.getOpposite()) {
			return (LazyOptional<T>) LazyOptional.of(() -> this);
		} 
		return super.getCapability(capability, face);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int room = maxFeConsumed.get() - feStored.get();
		int feAdded = room >= maxReceive ? maxReceive : room;
		if (!simulate) {
			feStored.set(feStored.get() + feAdded);
		}
		return room;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return feStored.get();
	}

	@Override
	public int getMaxEnergyStored() {
		return maxFeConsumed.get();
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	@Override
	public void setNotPlaying() {
		isPlaying = false;
	}

	@Override
	public boolean shouldPlaySound() {
		return running.get();
	}

	@Override
	public void onNeightborChanged(BlockPos neighbor, boolean blockStateTrigger) {
		hasRedstoneSignal.set(level.hasNeighborSignal(getBlockPos()));
	}

}
