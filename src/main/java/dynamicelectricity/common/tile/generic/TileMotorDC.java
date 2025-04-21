/*
 * Converts FE to Joules
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.Config;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricitySounds;
import electrodynamics.prefab.utilities.ElectricityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;
import voltaic.common.network.utils.FluidUtilities;
import voltaic.prefab.properties.types.PropertyTypes;
import voltaic.prefab.properties.variant.SingleProperty;
import voltaic.prefab.sound.ITickableSound;
import voltaic.prefab.sound.SoundBarrierMethods;
import voltaic.prefab.tile.GenericTile;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.*;
import voltaic.prefab.utilities.BlockEntityUtils;
import voltaic.prefab.utilities.object.CachedTileOutput;
import voltaic.prefab.utilities.object.TransferPack;
import voltaic.registers.VoltaicCapabilities;

public class TileMotorDC extends GenericTile implements IEnergyStorage, ITickableSound {

	public static final double CONVERSION_EFFICIENCY = 0.95;

	public static final int LUBRICANT_PER_MB = 20000;

	public final SingleProperty<Integer> maxFeConsumed;
	public final SingleProperty<Integer> feStored;
	public final SingleProperty<Double> joulesProduced;

	public final SingleProperty<Integer> lubricantRemaining;
	public final SingleProperty<Boolean> running;
	
	public final SingleProperty<Boolean> hasRedstoneSignal;

	protected CachedTileOutput output;

	private boolean isPlaying = false;
	
	public final int energyTier;

	public TileMotorDC(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int feConsumed, int energyTier, String name) {
		super(tileEntityTypeIn, pos, state);
		
		this.energyTier = energyTier;
		
		maxFeConsumed = property(new SingleProperty<>(PropertyTypes.INTEGER, "feconsumed", feConsumed));
		feStored = property(new SingleProperty<>(PropertyTypes.INTEGER, "festored", 0));
		joulesProduced = property(new SingleProperty<>(PropertyTypes.DOUBLE, "joulesproduced", feConsumed * CONVERSION_EFFICIENCY));
		
		lubricantRemaining = property(new SingleProperty<>(PropertyTypes.INTEGER, "lubricantremaining", 0));
		running = property(new SingleProperty<>(PropertyTypes.BOOLEAN, "running", false));
		
		hasRedstoneSignal = property(new SingleProperty<>(PropertyTypes.BOOLEAN, "redstonesignal", false));
		
		addComponent(new ComponentTickable(this).tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler(this));
		addComponent(new ComponentElectrodynamic(this, true, false).setOutputDirections(BlockEntityUtils.MachineDirection.FRONT).voltage(Math.pow(2, energyTier) * VoltaicCapabilities.DEFAULT_VOLTAGE));
		addComponent(new ComponentInventory(this, ComponentInventory.InventoryBuilder.newInv().inputs(1).bucketInputs(1)).valid(machineValidator()).setDirectionsBySlot(0, BlockEntityUtils.MachineDirection.LEFT, BlockEntityUtils.MachineDirection.RIGHT, BlockEntityUtils.MachineDirection.TOP));
		addComponent(new ComponentContainerProvider("motordc" + name, this).createMenu((id, player) -> new ContainerMotorDC(id, player, getComponent(IComponentType.Inventory), getCoordsArray())));
		addComponent(new ComponentFluidHandlerSimple(1000, this, "lubricant").setValidFluidTags(DynamicElectricityTags.Fluids.LUBRICANT).setInputDirections(BlockEntityUtils.MachineDirection.BOTTOM));
	}

	public void tickServer(ComponentTickable tickable) {
		
		if(hasRedstoneSignal.getValue()) {
			running.setValue(false);
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
		if (!brush.isEmpty() && feStored.getValue() >= maxFeConsumed.getValue()) {
			if(Config.useConductorBrushDurability) {
				brush.setDamageValue(brush.getDamageValue() + 1);
			}
			if (lubricantRemaining.getValue() > 0) {
				lubricantRemaining.setValue(lubricantRemaining.getValue() - 1);
				canRun = true;
			} else if (lubricantRemaining.getValue() == 0 && tank.getFluidAmount() > 0) {
				tank.drain(1, IFluidHandler.FluidAction.EXECUTE);
				lubricantRemaining.setValue(LUBRICANT_PER_MB);
			}
			
		}
		
		if (brush.getDamageValue() >= brush.getMaxDamage() && canRun) {
			brush.shrink(1);
			inventory.setItem(0, brush);
		}

		FluidUtilities.drainItem(this, tank.asArray());
		running.setValue(canRun);
		
		if(!canRun) {
			return;
		}

		this.feStored.setValue(0);
		if (output.valid()) {
			TransferPack pack = TransferPack.joulesVoltage(joulesProduced.getValue(), electro.getVoltage());
			ElectricityUtils.receivePower(output.getSafe(), facing.getOpposite(), pack, false);
		}

	}

	public void tickClient(ComponentTickable tickable) {

		if(shouldPlaySound() && !isPlaying) {
			isPlaying = true;
			SoundBarrierMethods.playTileSound(DynamicElectricitySounds.SOUND_MOTORRUNNING.get(), this, true);
		}
		
	}

	public @Nullable IEnergyStorage getFECapability(@Nullable Direction side) {
		if (side == null) {
			return null;
		} else {
			if (side == getFacing().getOpposite()) {
				return this;
			} else {
				return null;
			}
		}
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int room = maxFeConsumed.getValue() - feStored.getValue();
		int feAdded = room >= maxReceive ? maxReceive : room;
		if (!simulate) {
			feStored.setValue(feStored.getValue() + feAdded);
		}
		return room;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return feStored.getValue();
	}

	@Override
	public int getMaxEnergyStored() {
		return maxFeConsumed.getValue();
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
		return running.getValue();
	}
	
	@Override
	public void onNeightborChanged(BlockPos neighbor, boolean blockStateTrigger) {
		hasRedstoneSignal.setValue(level.hasNeighborSignal(getBlockPos()));
	}

}
