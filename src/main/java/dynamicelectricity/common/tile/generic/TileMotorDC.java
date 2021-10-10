/*
 * Converts FE to Joules
 */
package dynamicelectricity.common.tile.generic;

import dynamicelectricity.SoundRegister;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import electrodynamics.api.sound.SoundAPI;
import electrodynamics.common.network.ElectricityUtilities;
import electrodynamics.prefab.tile.GenericTileTicking;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import electrodynamics.prefab.utilities.object.CachedTileOutput;
import electrodynamics.prefab.utilities.object.TransferPack;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileMotorDC extends GenericTileTicking implements IEnergyStorage{

	public static final double CONVERSION_EFFICIENCY = 0.85;
	
	public static final int LUBRICANT_PER_ITEM = 100000;
	
	private int MAX_FE_CONSUMED;
	private int FE_STORED;
	private double JOULES_PRODUCED;
	
	public int LUBRICANT_REMAINING = 0;
	
	//Tick-sensitive status for rendering
	private boolean RUNNING_UPDATE;

	protected CachedTileOutput output;
	
	public int CLIENT_LUBRICANT;
	
	public boolean CLIENT_ISPOWERED;
	
	public long clientRunningTicks = 0;
	
	public TileMotorDC(TileEntityType<?> tileEntityTypeIn, int feConsumed, int voltage, String name) {
			super(tileEntityTypeIn);
			MAX_FE_CONSUMED = feConsumed;
			JOULES_PRODUCED = feConsumed * CONVERSION_EFFICIENCY;
			addComponent(new ComponentDirection());
			addComponent(new ComponentTickable().tickServer(this::tickServer).tickClient(this::tickClient));
			addComponent(new ComponentPacketHandler().customPacketReader(this::readPacket).customPacketWriter(this::createPacket)
				.guiPacketReader(this::readPacket).guiPacketWriter(this::write));
			addComponent(new ComponentElectrodynamic(this).relativeOutput(Direction.SOUTH).voltage(voltage));
			addComponent(new ComponentInventory(this).size(2).valid((slot, stack) -> true));
			addComponent(new ComponentContainerProvider("container.motordc" + name)
				.createMenu((id, player) -> new ContainerMotorDC(id, player, getComponent(ComponentType.Inventory), getCoordsArray())));
		}
		
		public void tickServer(ComponentTickable tickable) {
			ComponentInventory inventory = getComponent(ComponentType.Inventory);
			ComponentElectrodynamic electro = getComponent(ComponentType.Electrodynamic);
			Direction facing = this.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();
			
			if (output == null) {
			    output = new CachedTileOutput(world, pos.offset(facing));
			}
			
			boolean tickUpdate = tickable.getTicks() % 20 == 0;
			
			if (tickUpdate) {
			    output.update();
			}
			
			boolean canRun = false;
			
			ItemStack brush = inventory.getStackInSlot(0);
			ItemStack lubricant = inventory.getStackInSlot(1);
			
			if(!brush.isEmpty()) {
				brush.setDamage(brush.getDamage() + 1);
				if(brush.getDamage() >= brush.getMaxDamage()) {
					brush.shrink(1);
					inventory.setInventorySlotContents(0, brush);
				}
				if(LUBRICANT_REMAINING > 0) {
					LUBRICANT_REMAINING --;
					canRun = true;
				} else if(LUBRICANT_REMAINING == 0 && !lubricant.isEmpty()) {
					lubricant.shrink(1);
					inventory.setInventorySlotContents(1, lubricant);
					LUBRICANT_REMAINING = LUBRICANT_PER_ITEM;
				}
			}
			
			if(tickUpdate) {
				RUNNING_UPDATE = canRun;
				this.<ComponentPacketHandler>getComponent(ComponentType.PacketHandler).sendGuiPacketToTracking();
				clientRunningTicks = 0;
			}
			
			if(canRun) {
				this.FE_STORED = 0;
				if(output.valid()) {
					TransferPack pack = TransferPack.joulesVoltage(JOULES_PRODUCED, electro.getVoltage());
					ElectricityUtilities.receivePower(output.getSafe(), facing.getOpposite(), pack, false);
				}
			}
			
		}
		
		public void tickClient(ComponentTickable tickable) {
			
			double progress = Math.sin(0.05 * Math.PI * (clientRunningTicks % 20));
			if(CLIENT_ISPOWERED && progress == 1.0) {
				
				SoundAPI.playSound(SoundRegister.SOUND_MOTORRUNNING.get(), SoundCategory.BLOCKS, 5, .75f, pos);
				clientRunningTicks = 0;
			}
			clientRunningTicks ++;
		}
		
		//if I store it in both NBTs with the same name it just works
		protected void createPacket(CompoundNBT nbt) {
			nbt.putInt("lubricant", LUBRICANT_REMAINING);
			nbt.putBoolean("powered", RUNNING_UPDATE);
		}
		
		protected void readPacket(CompoundNBT nbt) {
			CLIENT_LUBRICANT = nbt.getInt("lubricant");
			CLIENT_ISPOWERED = nbt.getBoolean("powered");
		}
		
		@Override
		public void read(BlockState arg0, CompoundNBT nbt) {
			this.LUBRICANT_REMAINING = nbt.getInt("lubricant");
			nbt.getBoolean("powered");
			super.read(arg0, nbt);
		}
		
		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putInt("lubricant", LUBRICANT_REMAINING);
			nbt.putBoolean("powered", RUNNING_UPDATE);
			return super.write(nbt);
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
