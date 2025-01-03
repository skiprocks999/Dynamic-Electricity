package dynamicelectricity.compatability.industrialreforged;

import com.indref.industrial_reforged.api.capabilities.energy.IEnergyStorage;
import com.indref.industrial_reforged.api.tiers.EnergyTier;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;

public class InputCap implements IEnergyStorage {

    private final TileMotorDC motor;
    private final Holder<EnergyTier> tier;

    public InputCap(TileMotorDC motor, Holder<EnergyTier> tier) {
        this.motor = motor;
        this.tier = tier;
    }

    @Override
    public int getMaxOutput() {
        return 0;
    }

    @Override
    public Holder<EnergyTier> getEnergyTier() {
        return tier;
    }

    @Override
    public int getEnergyStored() {
        return motor.feStored.get() * IndustrialReforgedHandler.ENERGY_PER_JOULE;
    }

    @Override
    public void setEnergyStored(int energy) {
        motor.feStored.set((int) Mth.clamp((double) energy / IndustrialReforgedHandler.ENERGY_PER_JOULE, 0, motor.maxFeConsumed.get()));
    }

    @Override
    public int getEnergyCapacity() {
        return motor.maxFeConsumed.get() * IndustrialReforgedHandler.ENERGY_PER_JOULE;
    }

    @Override
    public void setEnergyCapacity(int cap) {

    }
}
