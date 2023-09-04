package dynamicelectricity.compatability.industrialreborn;

//import com.maciej916.indreb.common.api.energy.interfaces.IEnergyStorage;
//import com.maciej916.indreb.common.api.enums.EnergyTier;
//import com.maciej916.indreb.common.api.enums.EnergyType;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;

public class OutputCap {//implements IEnergyStorage {

//	private final TileMotorAC motor;
//	private final EnergyTier tier;
//
//	public OutputCap(TileMotorAC motor, EnergyTier tier) {
//		this.motor = motor;
//		this.tier = tier;
//	}
//
//	@Override
//	public boolean canExtractEnergy(Direction face) {
//		return face == motor.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();
//	}
//
//	@Override
//	public boolean canReceiveEnergy(Direction face) {
//		return false;
//	}
//
//	@Override
//	public int energyStored() {
//		return motor.feStored.get() / IndustrialRebornHandler.ENERGY_PER_JOULE;
//	}
//
//	@Override
//	public EnergyTier energyTier() {
//		return tier;
//	}
//
//	@Override
//	public EnergyType energyType() {
//		return EnergyType.EXTRACT;
//	}
//
//	@Override
//	public int maxEnergy() {
//		return motor.feProduced.get() / IndustrialRebornHandler.ENERGY_PER_JOULE;
//	}
//
//	@Override
//	public int maxExtractTick() {
//		return maxEnergy();
//	}
//
//	@Override
//	public int maxReceiveTick() {
//		return 0;
//	}
//
//	@Override
//	public int setEnergy(int amt) {
//		motor.feStored.set(Mth.clamp(amt * IndustrialRebornHandler.ENERGY_PER_JOULE, 0, motor.feProduced.get()));
//		return motor.feStored.get();
//	}
//
//	@Override
//	public void setEnergyTier(EnergyTier tier) {
//		// not used
//	}
//
//	@Override
//	public void setMaxEnergy(int max) {
//		// not used
//	}
//
//	@Override
//	public void setEnergyType(EnergyType arg0) {
//		// TODO Auto-generated method stub
//		
//	}
	
	

}
