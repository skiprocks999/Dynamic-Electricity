/*
 * Converts Joules to FE
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.registry.DynamicElectricityItems;
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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileMotorAC extends GenericTile implements IEnergyStorage, ITickableSound {

	public static final int SLOT = 0;
	
	public static double CONVERSION_EFFICIENCY = 1;

	public static final int LUBRICANT_PER_ITEM = 200000;

	public final Property<Integer> feProduced;
	public final Property<Integer> feStored;
	public final Property<Double> joulesConsumed;

	public final Property<Integer> lubricantRemaining;
	public final Property<Boolean> running;

	public final Property<Boolean> hasRedstoneSignal;

	private boolean isPlaying = false;

	public TileMotorAC(TileEntityType<?> tileEntityTypeIn, int energyTier, double joulesCons, String name) {
		super(tileEntityTypeIn);
		joulesConsumed = property(new Property<>(PropertyType.Double, "joulesconsumed", joulesCons));
		feProduced = property(new Property<>(PropertyType.Integer, "feproduced", (int) (joulesCons * CONVERSION_EFFICIENCY)));
		feStored = property(new Property<>(PropertyType.Integer, "festored", 0));

		lubricantRemaining = property(new Property<>(PropertyType.Integer, "lubricantremaining", 0));
		running = property(new Property<>(PropertyType.Boolean, "running", false));

		hasRedstoneSignal = property(new Property<>(PropertyType.Boolean, "redstonesignal", false));

		addComponent(new ComponentTickable(this).tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler(this));
		addComponent(new ComponentElectrodynamic(this, false, true).setInputDirections(Direction.NORTH).maxJoules(joulesCons * 20).voltage(Math.pow(2, energyTier) * ElectrodynamicsCapabilities.DEFAULT_VOLTAGE));
		addComponent(new ComponentInventory(this, InventoryBuilder.newInv().inputs(1)).valid((slot, stack, inv) -> stack.getItem() == DynamicElectricityItems.ITEM_LUBRICANTGEL.get()).setDirectionsBySlot(0, Direction.EAST, Direction.WEST, Direction.UP));
		addComponent(new ComponentContainerProvider("container.motorac" + name, this).createMenu((id, player) -> new ContainerMotorAC(id, player, getComponent(IComponentType.Inventory), getCoordsArray())));
	}

	public void tickServer(ComponentTickable tickable) {

		if (hasRedstoneSignal.get()) {
			running.set(false);
			return;
		}

		ComponentElectrodynamic electro = getComponent(IComponentType.Electrodynamic);

		ComponentInventory inventory = getComponent(IComponentType.Inventory);

		Direction facing = getFacing();

		boolean canRun = false;

		ItemStack lubricant = inventory.getItem(SLOT);

		if (electro.getJoulesStored() >= joulesConsumed.get()) {
			if (lubricantRemaining.get() > 0) {
				lubricantRemaining.set(lubricantRemaining.get() - 1);
				canRun = true;
			} else if (!lubricant.isEmpty() && lubricantRemaining.get() <= 0) {
				inventory.removeItem(SLOT, 1);
				lubricantRemaining.set(LUBRICANT_PER_ITEM);
			}
		}

		running.set(canRun);

		if (!canRun) {
			return;
		}

		electro.joules(electro.getJoulesStored() - joulesConsumed.get());
		feStored.set(feProduced.get());

		BlockPos pos = this.getBlockPos().relative(facing);

		TileEntity tile = level.getBlockEntity(pos);

		if (tile == null) {
			return;
		}

		handleFe(tile, facing);

	}

	public void tickClient(ComponentTickable tickable) {
		if (shouldPlaySound() && !isPlaying) {
			isPlaying = true;
			SoundBarrierMethods.playTileSound(DynamicElectricitySounds.SOUND_MOTORRUNNING.get(), this, true);
		}
	}

	private void handleFe(TileEntity tile, Direction motorFacing) {
		boolean hasFeEnergyCap = tile.getCapability(CapabilityEnergy.ENERGY, motorFacing.getOpposite()).map(m -> true).orElse(false);

		if (tile == null || !hasFeEnergyCap) {
			return;
		}

		int amtAccepted = tile.getCapability(CapabilityEnergy.ENERGY, motorFacing.getOpposite()).map(m -> {
			return m.receiveEnergy(feStored.get(), true);
		}).orElse(0);

		if (amtAccepted > 0) {
			tile.getCapability(CapabilityEnergy.ENERGY, motorFacing.getOpposite()).ifPresent(h -> {
				h.receiveEnergy(amtAccepted, false);
			});
			feStored.set(feStored.get() - amtAccepted);
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction face) {
		Direction facing = getFacing();
		if (capability == CapabilityEnergy.ENERGY && face == facing) {
			return (LazyOptional<T>) LazyOptional.of(() -> this);
		}
		return super.getCapability(capability, face);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int amtExtracted = maxExtract >= feStored.get() ? feStored.get() : maxExtract;
		if (!simulate) {
			feStored.set(feStored.get() - amtExtracted);
		}
		return amtExtracted;
	}

	@Override
	public int getEnergyStored() {
		return feStored.get();
	}

	@Override
	public int getMaxEnergyStored() {
		return feProduced.get();
	}

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return false;
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
