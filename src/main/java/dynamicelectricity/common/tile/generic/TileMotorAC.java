/*
 * Converts Joules to FE
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricitySounds;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

import voltaic.common.network.utils.FluidUtilities;
import voltaic.prefab.properties.types.PropertyTypes;
import voltaic.prefab.properties.variant.SingleProperty;
import voltaic.prefab.sound.ITickableSound;
import voltaic.prefab.sound.SoundBarrierMethods;
import voltaic.prefab.tile.GenericTile;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.*;
import voltaic.prefab.utilities.BlockEntityUtils;
import voltaic.prefab.utilities.CapabilityUtils;
import voltaic.registers.VoltaicCapabilities;

public class TileMotorAC extends GenericTile implements IEnergyStorage, ITickableSound {

	public static double CONVERSION_EFFICIENCY = 1;

	public static final int LUBRICANT_PER_MB = 20000;

	public final SingleProperty<Integer> feProduced;
	public final SingleProperty<Integer> feStored;
	public final SingleProperty<Double> joulesConsumed;

	public final SingleProperty<Integer> lubricantRemaining;
	public final SingleProperty<Boolean> running;

	public final SingleProperty<Boolean> hasRedstoneSignal;

	private boolean isPlaying = false;
	
	public final int energyTier;

	public TileMotorAC(TileEntityType<?> tileEntityTypeIn, int energyTier, double joulesCons, String name) {
		super(tileEntityTypeIn);

		this.energyTier = energyTier;
		
		joulesConsumed = property(new SingleProperty<>(PropertyTypes.DOUBLE, "joulesconsumed", joulesCons));
		feProduced = property(new SingleProperty<>(PropertyTypes.INTEGER, "feproduced", (int) (joulesCons * CONVERSION_EFFICIENCY)));
		feStored = property(new SingleProperty<>(PropertyTypes.INTEGER, "festored", 0));

		lubricantRemaining = property(new SingleProperty<>(PropertyTypes.INTEGER, "lubricantremaining", 0));
		running = property(new SingleProperty<>(PropertyTypes.BOOLEAN, "running", false));

		hasRedstoneSignal = property(new SingleProperty<>(PropertyTypes.BOOLEAN, "redstonesignal", false));

		addComponent(new ComponentTickable(this).tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler(this));
		addComponent(new ComponentElectrodynamic(this, false, true).setInputDirections(BlockEntityUtils.MachineDirection.BACK).maxJoules(joulesCons * 20).voltage(Math.pow(2, energyTier) * VoltaicCapabilities.DEFAULT_VOLTAGE));
		addComponent(new ComponentInventory(this, ComponentInventory.InventoryBuilder.newInv().bucketInputs(1)).valid(machineValidator()));
		addComponent(new ComponentContainerProvider("motorac" + name, this).createMenu((id, player) -> new ContainerMotorAC(id, player, getComponent(IComponentType.Inventory), getCoordsArray())));
		addComponent(new ComponentFluidHandlerSimple(1000, this, "lubricant").setInputDirections(BlockEntityUtils.MachineDirection.BOTTOM).setValidFluidTags(DynamicElectricityTags.Fluids.LUBRICANT));
	}

	public void tickServer(ComponentTickable tickable) {

		if (hasRedstoneSignal.getValue()) {
			running.setValue(false);
			return;
		}

		ComponentElectrodynamic electro = getComponent(IComponentType.Electrodynamic);
		Direction facing = getFacing();

		boolean canRun = false;

		ComponentFluidHandlerSimple tank = getComponent(IComponentType.FluidHandler);
		if (electro.getJoulesStored() >= joulesConsumed.getValue()) {
			if (lubricantRemaining.getValue() > 0) {
				lubricantRemaining.setValue(lubricantRemaining.getValue() - 1);
				canRun = true;
			} else if (tank.getFluidAmount() > 0 && lubricantRemaining.getValue() == 0) {
				tank.drain(1, FluidAction.EXECUTE);
				lubricantRemaining.setValue(LUBRICANT_PER_MB);
			}
		}

		FluidUtilities.drainItem(this, this.<ComponentFluidHandlerSimple>getComponent(IComponentType.FluidHandler).asArray());

		running.setValue(canRun);

		if (!canRun) {
			return;
		}

		electro.joules(electro.getJoulesStored() - joulesConsumed.getValue());
		feStored.setValue(feProduced.getValue());

		BlockPos pos = this.getBlockPos().relative(facing);

		TileEntity tile = level.getBlockEntity(pos);
		
		if(tile == null) {
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
		if(tile == null) {
			return;
		}

		IEnergyStorage feCap = tile.getCapability(CapabilityEnergy.ENERGY, motorFacing.getOpposite()).orElse(CapabilityUtils.EMPTY_FE);

		if(feCap == CapabilityUtils.EMPTY_FE) {
			return;
		}

		int amtAccepted = feCap.receiveEnergy(feStored.getValue(), true);

		if (amtAccepted > 0) {
			feCap.receiveEnergy(amtAccepted, false);
			feStored.setValue(feStored.getValue() - amtAccepted);
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction face) {
		Direction facing = getFacing();
		if (capability == CapabilityEnergy.ENERGY && face == facing) {
			return LazyOptional.of(() -> this).cast();
		}
		return super.getCapability(capability, face);
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int amtExtracted = maxExtract >= feStored.getValue() ? feStored.getValue() : maxExtract;
		if (!simulate) {
			feStored.setValue(feStored.getValue() - amtExtracted);
		}
		return amtExtracted;
	}

	@Override
	public int getEnergyStored() {
		return feStored.getValue();
	}

	@Override
	public int getMaxEnergyStored() {
		return feProduced.getValue();
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
		return running.getValue();
	}

	@Override
	public void onNeightborChanged(BlockPos neighbor, boolean blockStateTrigger) {
		hasRedstoneSignal.setValue(level.hasNeighborSignal(getBlockPos()));
	}

}
