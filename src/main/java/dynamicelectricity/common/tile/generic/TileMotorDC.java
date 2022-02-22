/*
 * Converts FE to Joules
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.SoundRegister;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import electrodynamics.api.sound.SoundAPI;
import electrodynamics.common.network.FluidUtilities;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentFluidHandlerMulti;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import electrodynamics.prefab.utilities.ElectricityUtils;
import electrodynamics.prefab.utilities.object.CachedTileOutput;
import electrodynamics.prefab.utilities.object.TransferPack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class TileMotorDC extends GenericTile implements IEnergyStorage{

	public static final double CONVERSION_EFFICIENCY = 0.95;
	
	public static final int LUBRICANT_PER_MB = 20000;
	
	private int MAX_FE_CONSUMED;
	private int FE_STORED;
	private double JOULES_PRODUCED;
	
	public int LUBRICANT_REMAINING = 0;
	
	//Tick-sensitive status for rendering
	private boolean RUNNING_UPDATE;

	protected CachedTileOutput output;
	
	public int CLIENT_LUBRICANT;
	public double CLIENT_JOULESPRODUCED;
	public int CLIENT_FEUSE;
	
	public boolean CLIENT_ISPOWERED;
	
	public long clientRunningTicks = 0;
	
	public TileMotorDC(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int feConsumed, int voltage, String name) {
		super(tileEntityTypeIn, pos, state);
		MAX_FE_CONSUMED = feConsumed;
		JOULES_PRODUCED = feConsumed * CONVERSION_EFFICIENCY;
		addComponent(new ComponentDirection());
		addComponent(new ComponentTickable().tickServer(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler().customPacketReader(this::readPacket).customPacketWriter(this::createPacket)
			.guiPacketReader(this::readPacket).guiPacketWriter(this::createPacket));
		addComponent(new ComponentElectrodynamic(this).relativeOutput(Direction.SOUTH).voltage(voltage));
		addComponent(new ComponentInventory(this).size(2).inputs(1).bucketInputs(1).valid(machineValidator()).relativeFaceSlots(Direction.EAST, 0)
				.relativeFaceSlots(Direction.WEST, 0).relativeFaceSlots(Direction.UP, 0));
		addComponent(new ComponentContainerProvider("container.motordc" + name)
			.createMenu((id, player) -> new ContainerMotorDC(id, player, getComponent(ComponentType.Inventory), getCoordsArray())));
		addComponent(new ComponentFluidHandlerMulti(this).setManualFluidTags(1, true, 1000, DynamicElectricityTags.Fluids.LUBRICANT).relativeInput(Direction.DOWN));
	}
	
	public void tickServer(ComponentTickable tickable) {
		ComponentInventory inventory = getComponent(ComponentType.Inventory);
		ComponentElectrodynamic electro = getComponent(ComponentType.Electrodynamic);
		Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();
		
		if (output == null) {
		    output = new CachedTileOutput(level, getBlockPos().relative(facing));
		}
		
		if (tickable.getTicks() % 20 == 0) {
		    output.update();
		}
		
		boolean canRun = false;
		
		ItemStack brush = inventory.getItem(0);
		ComponentFluidHandlerMulti multi = getComponent(ComponentType.FluidHandler);
		FluidTank tank = multi.getInputTanks().length > 0 ? multi.getInputTanks()[0] : new FluidTank(0);
		if(!brush.isEmpty() && isPowered()) {
			brush.setDamageValue(brush.getDamageValue() + 1);
			if(brush.getDamageValue() >= brush.getMaxDamage()) {
				brush.shrink(1);
				inventory.setItem(0, brush);
			}
			if(LUBRICANT_REMAINING > 0) {
				LUBRICANT_REMAINING --;
				canRun = true;
			} else if(LUBRICANT_REMAINING == 0 && tank.getFluidAmount() > 0) {
				tank.drain(1, FluidAction.EXECUTE);
				LUBRICANT_REMAINING = LUBRICANT_PER_MB;
			}
		}
		
		if(inventory.getItems().size() > 0) {
			FluidUtilities.drainItem(this);
		}
		
		if(tickable.getTicks() % 5 == 0) {
			RUNNING_UPDATE = canRun;
			this.<ComponentPacketHandler>getComponent(ComponentType.PacketHandler).sendGuiPacketToTracking();
		}
		
		if(canRun) {
			this.FE_STORED = 0;
			if(output.valid()) {
				TransferPack pack = TransferPack.joulesVoltage(JOULES_PRODUCED, electro.getVoltage());
				ElectricityUtils.receivePower(output.getSafe(), facing.getOpposite(), pack, false);
			}
		}
		
	}
	
	public void tickClient(ComponentTickable tickable) {
		
		double progress = Math.sin(0.05 * Math.PI * (clientRunningTicks % 20));
		if(CLIENT_ISPOWERED && progress == 1.0) {
			
			SoundAPI.playSound(SoundRegister.SOUND_MOTORRUNNING.get(), SoundSource.BLOCKS, 5, .75f, getBlockPos());
			clientRunningTicks = 0;
		}
		clientRunningTicks ++;
	}
	
	//if I store it in both NBTs with the same name it just works
	protected void createPacket(CompoundTag nbt) {
		nbt.putInt("lubricant", LUBRICANT_REMAINING);
		nbt.putBoolean("powered", RUNNING_UPDATE);
		nbt.putDouble("produced", JOULES_PRODUCED);
		nbt.putInt("clientuse", MAX_FE_CONSUMED);
	}
	
	protected void readPacket(CompoundTag nbt) {
		CLIENT_LUBRICANT = nbt.getInt("lubricant");
		CLIENT_ISPOWERED = nbt.getBoolean("powered");
		CLIENT_JOULESPRODUCED = nbt.getDouble("produced");
		CLIENT_FEUSE = nbt.getInt("clientuse");
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		this.LUBRICANT_REMAINING = nbt.getInt("lubricant");
		nbt.getBoolean("powered");
		super.deserializeNBT(nbt);
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = super.serializeNBT();
		nbt.putInt("lubricant", LUBRICANT_REMAINING);
		nbt.putBoolean("powered", RUNNING_UPDATE);
		return nbt;
	}
	
	@Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction face) {
		Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();

		if (capability == CapabilityEnergy.ENERGY && face == facing.getOpposite() ) {
		    return (LazyOptional<T>) LazyOptional.of(() -> this);
		}
		return super.getCapability(capability, face);
    }

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int room = MAX_FE_CONSUMED - FE_STORED;
		int feAdded = room >= maxReceive ? maxReceive : room;
		if(!simulate) {
			FE_STORED += feAdded;
		}
		return room;
	}
	
	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return FE_STORED;
	}

	@Override
	public int getMaxEnergyStored() {
		return MAX_FE_CONSUMED;
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return true;
	}
	
	public boolean isPowered() {
		return FE_STORED >= MAX_FE_CONSUMED;
	}

	public double getMaxJoules() {
		return JOULES_PRODUCED;
	}
		
}
