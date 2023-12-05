package dynamicelectricity.compatability.industrialreborn;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;

public class OutputCap implements IEnergy {

	private final TileMotorAC motor;
	private final EnergyTier tier;

	public OutputCap(TileMotorAC motor, EnergyTier tier) {
		this.motor = motor;
		this.tier = tier;
	}

	@Override
	public boolean canExtractEnergy(Direction face) {
		return face == motor.getFacing();
	}

	@Override
	public boolean canReceiveEnergy(Direction face) {
		return false;
	}

	@Override
	public int energyStored() {
		return motor.feStored.get() / IndustrialRebornHandler.ENERGY_PER_JOULE;
	}

	@Override
	public EnergyTier energyTier() {
		return tier;
	}

	@Override
	public EnergyType energyType() {
		return EnergyType.EXTRACT;
	}

	@Override
	public int maxEnergy() {
		return motor.feProduced.get() / IndustrialRebornHandler.ENERGY_PER_JOULE;
	}

	@Override
	public int maxExtractTick() {
		return maxEnergy();
	}

	@Override
	public int maxReceiveTick() {
		return 0;
	}

	@Override
	public int setEnergy(int amt) {
		motor.feStored.set(Mth.clamp(amt * IndustrialRebornHandler.ENERGY_PER_JOULE, 0, motor.feProduced.get()));
		return motor.feStored.get();
	}

	@Override
	public void setEnergyTier(EnergyTier arg0) {

	}

	@Override
	public void setMaxEnergy(int arg0) {

	}

}
