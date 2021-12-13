/*
 * Converts Joules to FE
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.SoundRegister;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import electrodynamics.api.sound.SoundAPI;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileMotorAC extends GenericTile implements IEnergyStorage{
	
	public static double CONVERSION_EFFICIENCY = 0.9;
	
	public static final int LUBRICANT_PER_ITEM = 200000;
	
	private int FE_PRODUCED;
	private int FE_STORED;
	private double JOULES_CONSUMED;
	
	public int LUBRICANT_REMAINING;
	private boolean RUNNING_UPDATE;
	
	public int CLIENT_LUBRICANT;
	public boolean CLIENT_ISPOWERED;
	
	public long clientRunningTicks = 0;
	
	public TileMotorAC(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state, int voltage, double joulesConsumed, String name) {
		super(tileEntityTypeIn, pos, state);
		FE_PRODUCED = (int) (joulesConsumed * CONVERSION_EFFICIENCY);
		JOULES_CONSUMED = joulesConsumed;
		addComponent(new ComponentDirection());
		addComponent(new ComponentTickable().tickCommon(this::tickServer).tickClient(this::tickClient));
		addComponent(new ComponentPacketHandler().customPacketReader(this::readPacket).customPacketWriter(this::createPacket)
			.guiPacketReader(this::readPacket).guiPacketWriter(this::createPacket));
		addComponent(new ComponentElectrodynamic(this).relativeInput(Direction.NORTH).maxJoules(joulesConsumed * 10).voltage(voltage));
		addComponent(new ComponentInventory(this).size(1).inputs(1).valid(machineValidator()));
		addComponent(new ComponentContainerProvider("container.motorac" + name)
			.createMenu((id, player) -> new ContainerMotorAC(id, player, getComponent(ComponentType.Inventory), getCoordsArray())));
	}
	
	public void tickServer(ComponentTickable tickable) {
		ComponentInventory inventory = getComponent(ComponentType.Inventory);
		ComponentElectrodynamic electro = getComponent(ComponentType.Electrodynamic);
		Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();
		
		boolean canRun = false;
		
		ItemStack lubricant = inventory.getItem(0);
		
		if(electro.getJoulesStored() >= JOULES_CONSUMED) {
			if(LUBRICANT_REMAINING > 0) {
				LUBRICANT_REMAINING--;
				canRun = true;
			} else if(!lubricant.isEmpty() && LUBRICANT_REMAINING == 0) {
				lubricant.shrink(1);
				inventory.setItem(0, lubricant);
				LUBRICANT_REMAINING = LUBRICANT_PER_ITEM;
			}
		}
		
		if(tickable.getTicks() % 20 == 0) {
			RUNNING_UPDATE = canRun;
			this.<ComponentPacketHandler>getComponent(ComponentType.PacketHandler).sendGuiPacketToTracking();
		}
		
		if(canRun) {
			
			electro.joules(electro.getJoulesStored() - JOULES_CONSUMED);
			FE_STORED = FE_PRODUCED;
			
			BlockPos pos = this.getBlockPos().relative(facing);
			BlockState state = level.getBlockState(pos);
			if(state.hasBlockEntity()) {
				BlockEntity tile = level.getBlockEntity(pos);
				if(tile != null && tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).map(m -> {return true;}).orElse(false)){
					
					int amtAccepted = tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).map(m -> {
						return m.receiveEnergy(FE_STORED, true);
					}).orElse(0);
					if(amtAccepted > 0) {
						tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite()).ifPresent(h ->{
							h.receiveEnergy(amtAccepted, false);
						});
						FE_STORED -= amtAccepted;
					}
				}
			}
		} 
		
		
	}
	
	public void tickClient(ComponentTickable tickable) {
		double progress = Math.sin(0.05 * Math.PI * (clientRunningTicks % 20));
		if(CLIENT_ISPOWERED && progress == 1.0) {
			
			SoundAPI.playSound(SoundRegister.SOUND_MOTORRUNNING.get(), SoundSource.BLOCKS, 5, .75f, this.getBlockPos());
			clientRunningTicks = 0;
		}
		clientRunningTicks ++;
	}
	
	protected void createPacket(CompoundTag nbt) {
		nbt.putInt("lubricant", LUBRICANT_REMAINING);
		nbt.putBoolean("powered", RUNNING_UPDATE);
	}
	
	protected void readPacket(CompoundTag nbt) {
		CLIENT_LUBRICANT = nbt.getInt("lubricant");
		CLIENT_ISPOWERED = nbt.getBoolean("powered");
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
		if (capability == CapabilityEnergy.ENERGY && face == facing ) {
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
		int amtExtracted = maxExtract >= FE_STORED ? FE_STORED : maxExtract;
		if(!simulate) {
			FE_STORED -= amtExtracted;
		}
		return amtExtracted;
	}

	@Override
    public int getEnergyStored() {
		return FE_STORED;
    }

    @Override
    public int getMaxEnergyStored() {
    	return FE_PRODUCED;
    }

	@Override
	public boolean canExtract() {
		return true;
	}

	@Override
	public boolean canReceive() {
		return false;
	}
}
