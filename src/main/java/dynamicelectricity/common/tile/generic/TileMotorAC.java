/*
 * Converts Joules to FE
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.compatability.industrialreborn.IndustrialRebornHandler;
import dynamicelectricity.registry.DynamicElectricitySounds;
import electrodynamics.common.network.utils.FluidUtilities;
import electrodynamics.prefab.properties.Property;
import electrodynamics.prefab.properties.PropertyTypes;
import electrodynamics.prefab.sound.SoundBarrierMethods;
import electrodynamics.prefab.sound.utils.ITickableSound;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.IComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentFluidHandlerSimple;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentInventory.InventoryBuilder;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import electrodynamics.prefab.utilities.BlockEntityUtils;
import electrodynamics.registers.ElectrodynamicsCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;

public class TileMotorAC extends GenericTile implements IEnergyStorage, ITickableSound {

	public static double CONVERSION_EFFICIENCY = 1;

	public static final int LUBRICANT_PER_MB = 20000;

	public final Property<Integer> feProduced;
	public final Property<Integer> feStored;
	public final Property<Double> joulesConsumed;

	public final Property<Integer> lubricantRemaining;
	public final Property<Boolean> running;

	public final Property<Boolean> hasRedstoneSignal;

	private boolean isPlaying = false;
	
	public final int energyTier;

	public TileMotorAC(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int energyTier, double joulesCons, String name) {
		super(tileEntityTypeIn, pos, state);

		this.energyTier = energyTier;
		
		joulesConsumed = property(new Property<>(PropertyTypes.DOUBLE, "joulesconsumed", joulesCons));
		feProduced = property(new Property<>(PropertyTypes.INTEGER, "feproduced", (int) (joulesCons * CONVERSION_EFFICIENCY)));
		feStored = property(new Property<>(PropertyTypes.INTEGER, "festored", 0));

		lubricantRemaining = property(new Property<>(PropertyTypes.INTEGER, "lubricantremaining", 0));
		running = property(new Property<>(PropertyTypes.BOOLEAN, "running", false));

		hasRedstoneSignal = property(new Property<>(PropertyTypes.BOOLEAN, "redstonesignal", false));

		addComponent(new ComponentTickable(this).tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler(this));
		addComponent(new ComponentElectrodynamic(this, false, true).setInputDirections(BlockEntityUtils.MachineDirection.FRONT).maxJoules(joulesCons * 20).voltage(Math.pow(2, energyTier) * ElectrodynamicsCapabilities.DEFAULT_VOLTAGE));
		addComponent(new ComponentInventory(this, InventoryBuilder.newInv().bucketInputs(1)).valid(machineValidator()));
		addComponent(new ComponentContainerProvider("container.motorac" + name, this).createMenu((id, player) -> new ContainerMotorAC(id, player, getComponent(IComponentType.Inventory), getCoordsArray())));
		addComponent(new ComponentFluidHandlerSimple(1000, this, "lubricant").setInputDirections(BlockEntityUtils.MachineDirection.BOTTOM).setValidFluidTags(DynamicElectricityTags.Fluids.LUBRICANT));
	}

	public void tickServer(ComponentTickable tickable) {

		if (hasRedstoneSignal.get()) {
			running.set(false);
			return;
		}

		ComponentElectrodynamic electro = getComponent(IComponentType.Electrodynamic);
		Direction facing = getFacing();

		boolean canRun = false;

		ComponentFluidHandlerSimple tank = getComponent(IComponentType.FluidHandler);
		if (electro.getJoulesStored() >= joulesConsumed.get()) {
			if (lubricantRemaining.get() > 0) {
				lubricantRemaining.set(lubricantRemaining.get() - 1);
				canRun = true;
			} else if (tank.getFluidAmount() > 0 && lubricantRemaining.get() == 0) {
				tank.drain(1, IFluidHandler.FluidAction.EXECUTE);
				lubricantRemaining.set(LUBRICANT_PER_MB);
			}
		}

		FluidUtilities.drainItem(this, this.<ComponentFluidHandlerSimple>getComponent(IComponentType.FluidHandler).asArray());

		running.set(canRun);

		if (!canRun) {
			return;
		}

		electro.joules(electro.getJoulesStored() - joulesConsumed.get());
		feStored.set(feProduced.get());

		BlockPos pos = this.getBlockPos().relative(facing);

		BlockEntity tile = level.getBlockEntity(pos);
		
		if(tile == null) {
			return;
		}

		handleFe(tile, facing);
		
		handleIndustrialReborn(tile, facing);

	}

	public void tickClient(ComponentTickable tickable) {
		if (shouldPlaySound() && !isPlaying) {
			isPlaying = true;
			SoundBarrierMethods.playTileSound(DynamicElectricitySounds.SOUND_MOTORRUNNING.get(), this, true);
		}
	}

	private void handleFe(BlockEntity tile, Direction motorFacing) {
		if(tile == null) {
			return;
		}

		IEnergyStorage feCap = level.getCapability(Capabilities.EnergyStorage.BLOCK, tile.getBlockPos(), tile.getBlockState(), tile, motorFacing.getOpposite());

		if(feCap == null) {
			return;
		}

		int amtAccepted = feCap.receiveEnergy(feStored.get(), true);

		if (amtAccepted > 0) {
			feCap.receiveEnergy(amtAccepted, true);
			feStored.set(feStored.get() - amtAccepted);
		}
	}

	private void handleIndustrialReborn(BlockEntity tile, Direction motorFacing) {

		if(!ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID)) {
			return;
		}
		
		IndustrialRebornHandler.handleEnergyOutput(this, tile, motorFacing);
		
	}

	/*
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction face) {
		Direction facing = getFacing();
		if (capability == ForgeCapabilities.ENERGY && face == facing) {
			return (LazyOptional<T>) LazyOptional.of(() -> this);
		} else if (ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID)) {

			if (IndustrialRebornHandler.isCapability(capability) && face == facing.getOpposite()) {
				//return (LazyOptional<T>) IndustrialRebornHandler.getACMotorCap(this, energyTier);
			}

		}
		return super.getCapability(capability, face);
	}

	 */

	public @Nullable IEnergyStorage getFECapability(@Nullable Direction side) {
		if (side == null) {
			return null;
		} else {
			Direction facing = this.getFacing();
			if (side == facing) {
				return this;
			} else {
				return null;
			}
		}
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
