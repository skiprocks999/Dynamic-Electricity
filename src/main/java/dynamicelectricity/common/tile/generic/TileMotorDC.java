/*
 * Converts FE to Joules
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.compatability.industrialreborn.IndustrialRebornHandler;
import dynamicelectricity.registry.DynamicElectricitySounds;
import electrodynamics.api.capability.ElectrodynamicsCapabilities;
import electrodynamics.common.network.FluidUtilities;
import electrodynamics.prefab.properties.Property;
import electrodynamics.prefab.properties.PropertyType;
import electrodynamics.prefab.sound.SoundBarrierMethods;
import electrodynamics.prefab.sound.utils.ITickableSound;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.IComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentFluidHandlerSimple;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import electrodynamics.prefab.tile.components.type.ComponentInventory.InventoryBuilder;
import electrodynamics.prefab.utilities.ElectricityUtils;
import electrodynamics.prefab.utilities.object.CachedTileOutput;
import electrodynamics.prefab.utilities.object.TransferPack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fml.ModList;

public class TileMotorDC extends GenericTile implements IEnergyStorage, ITickableSound {

	public static final double CONVERSION_EFFICIENCY = 0.95;

	public static final int LUBRICANT_PER_MB = 20000;

	public final Property<Integer> maxFeConsumed;
	public final Property<Integer> feStored;
	public final Property<Double> joulesProduced;

	public final Property<Integer> lubricantRemaining;
	public final Property<Boolean> running;

	public final Property<Boolean> hasRedstoneSignal;

	protected CachedTileOutput output;

	private boolean isPlaying = false;

	public final int energyTier;

	public TileMotorDC(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int feConsumed, int energyTier, String name) {
		super(tileEntityTypeIn, pos, state);

		this.energyTier = energyTier;

		maxFeConsumed = property(new Property<>(PropertyType.Integer, "feconsumed", feConsumed));
		feStored = property(new Property<>(PropertyType.Integer, "festored", 0));
		joulesProduced = property(new Property<>(PropertyType.Double, "joulesproduced", feConsumed * CONVERSION_EFFICIENCY));

		lubricantRemaining = property(new Property<>(PropertyType.Integer, "lubricantremaining", 0));
		running = property(new Property<>(PropertyType.Boolean, "running", false));

		hasRedstoneSignal = property(new Property<>(PropertyType.Boolean, "redstonesignal", false));

		addComponent(new ComponentTickable(this).tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler(this));
		addComponent(new ComponentElectrodynamic(this, true, false).setOutputDirections(Direction.SOUTH).voltage(Math.pow(2, energyTier) * ElectrodynamicsCapabilities.DEFAULT_VOLTAGE));
		addComponent(new ComponentInventory(this, InventoryBuilder.newInv().inputs(1).bucketInputs(1)).valid(machineValidator()).setDirectionsBySlot(0, Direction.EAST, Direction.WEST, Direction.UP));
		addComponent(new ComponentContainerProvider("container.motordc" + name, this).createMenu((id, player) -> new ContainerMotorDC(id, player, getComponent(IComponentType.Inventory), getCoordsArray())));
		addComponent(new ComponentFluidHandlerSimple(1000, this, "lubricant").setValidFluidTags(DynamicElectricityTags.Fluids.LUBRICANT).setInputDirections(Direction.DOWN));
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

		ItemStack brush = inventory.getItem(0);
		ComponentFluidHandlerSimple tank = getComponent(IComponentType.FluidHandler);
		if (!brush.isEmpty() && feStored.get() >= maxFeConsumed.get()) {
			brush.setDamageValue(brush.getDamageValue() + 1);
			if (lubricantRemaining.get() > 0) {
				lubricantRemaining.set(lubricantRemaining.get() - 1);
				canRun = true;
			} else if (lubricantRemaining.get() == 0 && tank.getFluidAmount() > 0) {
				tank.drain(1, FluidAction.EXECUTE);
				lubricantRemaining.set(LUBRICANT_PER_MB);
			}

		}

		if (brush.getDamageValue() >= brush.getMaxDamage() && canRun) {
			brush.shrink(1);
			inventory.setItem(0, brush);
		}

		FluidUtilities.drainItem(this, tank.asArray());
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

		if (capability == ForgeCapabilities.ENERGY && face == facing.getOpposite()) {
			return (LazyOptional<T>) LazyOptional.of(() -> this);
		} else if (ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID)) {

			if (IndustrialRebornHandler.isCapability(capability) && face == facing) {
				return (LazyOptional<T>) IndustrialRebornHandler.getDCMotorCap(this, energyTier);
			}

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
