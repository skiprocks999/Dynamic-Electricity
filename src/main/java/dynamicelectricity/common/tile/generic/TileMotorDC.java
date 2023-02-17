/*
 * Converts FE to Joules
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.common.inventory.container.ContainerMotorDC;
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
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
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

public class TileMotorDC extends GenericTile implements IEnergyStorage, ITickableSound {

	public static final double CONVERSION_EFFICIENCY = 0.95;

	public static final int LUBRICANT_PER_MB = 20000;

	public final Property<Integer> maxFeConsumed;
	public final Property<Integer> feStored;
	public final Property<Double> joulesProduced;

	public final Property<Integer> lubricantRemaining;
	public final Property<Boolean> running;

	protected CachedTileOutput output;

	private boolean isPlaying = false;

	public TileMotorDC(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int feConsumed, int voltage, String name) {
		super(tileEntityTypeIn, pos, state);
		maxFeConsumed = property(new Property<>(PropertyType.Integer, "feconsumed", feConsumed));
		feStored = property(new Property<>(PropertyType.Integer, "festored", 0));
		joulesProduced = property(new Property<>(PropertyType.Double, "joulesproduced", feConsumed * CONVERSION_EFFICIENCY));
		
		lubricantRemaining = property(new Property<>(PropertyType.Integer, "lubricantremaining", 0));
		running = property(new Property<>(PropertyType.Boolean, "running", false));
		
		addComponent(new ComponentDirection());
		addComponent(new ComponentTickable().tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler());
		addComponent(new ComponentElectrodynamic(this).relativeOutput(Direction.SOUTH).voltage(voltage));
		addComponent(new ComponentInventory(this).size(2).inputs(1).bucketInputs(1).valid(machineValidator()).relativeFaceSlots(Direction.EAST, 0).relativeFaceSlots(Direction.WEST, 0).relativeFaceSlots(Direction.UP, 0));
		addComponent(new ComponentContainerProvider("container.motordc" + name).createMenu((id, player) -> new ContainerMotorDC(id, player, getComponent(ComponentType.Inventory), getCoordsArray())));
		addComponent(new ComponentFluidHandlerSimple(1000, this, "lubricant").setValidFluidTags(DynamicElectricityTags.Fluids.LUBRICANT).setInputDirections(Direction.DOWN));
	}

	public void tickServer(ComponentTickable tickable) {
		ComponentInventory inventory = getComponent(ComponentType.Inventory);
		ComponentElectrodynamic electro = getComponent(ComponentType.Electrodynamic);
		Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();

		if (output == null) {
			output = new CachedTileOutput(level, getBlockPos().relative(facing));
		}

		if (tickable.getTicks() % 20 == 0) {
			output.update(worldPosition.relative(facing));
		}

		boolean canRun = false;

		ItemStack brush = inventory.getItem(0);
		ComponentFluidHandlerSimple tank = getComponent(ComponentType.FluidHandler);
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
		
		if(!canRun) {
			return;
		}

		this.feStored.set(0);
		if (output.valid()) {
			TransferPack pack = TransferPack.joulesVoltage(joulesProduced.get(), electro.getVoltage());
			ElectricityUtils.receivePower(output.getSafe(), facing.getOpposite(), pack, false);
		}

	}

	public void tickClient(ComponentTickable tickable) {

		if(shouldPlaySound() && !isPlaying) {
			isPlaying = true;
			SoundBarrierMethods.playTileSound(DynamicElectricitySounds.SOUND_MOTORRUNNING.get(), this, true);
		}
		
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction face) {
		Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();

		if (capability == ForgeCapabilities.ENERGY && face == facing.getOpposite()) {
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

}
