/*
 * Converts Joules to FE
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricitySounds;
import electrodynamics.common.network.FluidUtilities;
import electrodynamics.prefab.properties.Property;
import electrodynamics.prefab.properties.PropertyType;
import electrodynamics.prefab.sound.SoundBarrierMethods;
import electrodynamics.prefab.sound.utils.ITickableSound;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentFluidHandlerSimple;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentInventory.InventoryBuilder;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

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

	public TileMotorAC(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int voltage, double joulesCons, String name) {
		super(tileEntityTypeIn, pos, state);

		joulesConsumed = property(new Property<>(PropertyType.Double, "joulesconsumed", joulesCons));
		feProduced = property(new Property<>(PropertyType.Integer, "feproduced", (int) (joulesCons * CONVERSION_EFFICIENCY)));
		feStored = property(new Property<>(PropertyType.Integer, "festored", 0));

		lubricantRemaining = property(new Property<>(PropertyType.Integer, "lubricantremaining", 0));
		running = property(new Property<>(PropertyType.Boolean, "running", false));
		
		hasRedstoneSignal = property(new Property<>(PropertyType.Boolean, "redstonesignal", false));

		addComponent(new ComponentDirection());
		addComponent(new ComponentTickable().tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler());
		addComponent(new ComponentElectrodynamic(this).relativeInput(Direction.NORTH).maxJoules(joulesCons * 20).voltage(voltage));
		addComponent(new ComponentInventory(this, InventoryBuilder.newInv().bucketInputs(1)).valid(machineValidator()));
		addComponent(new ComponentContainerProvider("container.motorac" + name).createMenu((id, player) -> new ContainerMotorAC(id, player, getComponent(ComponentType.Inventory), getCoordsArray())));
		addComponent(new ComponentFluidHandlerSimple(1000, this, "lubricant").setInputDirections(Direction.DOWN).setValidFluidTags(DynamicElectricityTags.Fluids.LUBRICANT));
	}

	public void tickServer(ComponentTickable tickable) {

		if(hasRedstoneSignal.get()) {
			running.set(false);
			return;
		}
		
		ComponentElectrodynamic electro = getComponent(ComponentType.Electrodynamic);
		Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();

		boolean canRun = false;

		ComponentFluidHandlerSimple tank = getComponent(ComponentType.FluidHandler);
		if (electro.getJoulesStored() >= joulesConsumed.get()) {
			if (lubricantRemaining.get() > 0) {
				lubricantRemaining.set(lubricantRemaining.get() - 1);
				canRun = true;
			} else if (tank.getFluidAmount() > 0 && lubricantRemaining.get() == 0) {
				tank.drain(1, FluidAction.EXECUTE);
				lubricantRemaining.set(LUBRICANT_PER_MB);
			}
		}

		FluidUtilities.drainItem(this, this.<ComponentFluidHandlerSimple>getComponent(ComponentType.FluidHandler).asArray());

		running.set(canRun);

		if (!canRun) {
			return;
		}

		electro.joules(electro.getJoulesStored() - joulesConsumed.get());
		feStored.set(feProduced.get());

		BlockPos pos = this.getBlockPos().relative(facing);
		BlockState state = level.getBlockState(pos);

		if (!state.hasBlockEntity()) {
			return;
		}

		BlockEntity tile = level.getBlockEntity(pos);

		boolean hasFeEnergyCap = tile.getCapability(ForgeCapabilities.ENERGY, facing.getOpposite()).map(m -> true).orElse(false);

		if (tile == null || !hasFeEnergyCap) {
			return;	
		}

			
		int amtAccepted = tile.getCapability(ForgeCapabilities.ENERGY, facing.getOpposite()).map(m -> {
			return m.receiveEnergy(feStored.get(), true);
		}).orElse(0);
		
		
		if (amtAccepted > 0) {
			tile.getCapability(ForgeCapabilities.ENERGY, facing.getOpposite()).ifPresent(h -> {
				h.receiveEnergy(amtAccepted, false);
			});
			feStored.set(feStored.get() - amtAccepted);
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
		Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();
		if (capability == ForgeCapabilities.ENERGY && face == facing) {
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
	public void onNeightborChanged(BlockPos neighbor) {
		hasRedstoneSignal.set(level.hasNeighborSignal(getBlockPos()));
	}
	
}
