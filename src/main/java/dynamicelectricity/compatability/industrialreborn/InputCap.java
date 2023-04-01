package dynamicelectricity.compatability.industrialreborn;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.enums.EnergyType;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;

public class InputCap implements IEnergy {

	private final TileMotorDC motor;
	private final EnergyTier tier;

	public InputCap(TileMotorDC motor, EnergyTier tier) {
		this.motor = motor;
		this.tier = tier;
	}

	@Override
	public boolean canExtractEnergy(Direction face) {
		return false;
	}

	@Override
	public boolean canReceiveEnergy(Direction face) {
		return face == motor.<ComponentDirection>getComponent(ComponentType.Direction).getDirection().getOpposite();
	}

	@Override
	public int energyStored() {
		return motor.feStored.get();
	}

	@Override
	public EnergyTier energyTier() {
		return tier;
	}

	@Override
	public EnergyType energyType() {
		return EnergyType.RECEIVE;
	}

	@Override
	public int maxEnergy() {
		return motor.maxFeConsumed.get() / IndustrialRebornHandler.ENERGY_PER_JOULE;
	}

	@Override
	public int maxExtractTick() {
		return 0;
	}

	@Override
	public int maxReceiveTick() {
		return maxEnergy();
	}

	@Override
	public int setEnergy(int amt) {
		motor.feStored.set(Mth.clamp(amt * IndustrialRebornHandler.ENERGY_PER_JOULE, 0, motor.maxFeConsumed.get()));
		return motor.feStored.get();
	}

	@Override
	public void setEnergyTier(EnergyTier arg0) {
		// never ussed
	}

	@Override
	public void setMaxEnergy(int arg0) {
		// never used
	}

}
