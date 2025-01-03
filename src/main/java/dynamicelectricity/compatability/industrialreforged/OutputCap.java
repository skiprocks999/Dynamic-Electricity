package dynamicelectricity.compatability.industrialreforged;

import com.indref.industrial_reforged.api.capabilities.energy.IEnergyStorage;
import com.indref.industrial_reforged.api.tiers.EnergyTier;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;

public class OutputCap implements IEnergyStorage {

    private final TileMotorAC motor;
    private final Holder<EnergyTier> tier;

    public OutputCap(TileMotorAC motor, Holder<EnergyTier> tier) {
        this.motor = motor;
        this.tier = tier;
    }

    @Override
    public int getMaxInput() {
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
        motor.feStored.set((int) Mth.clamp((double) energy / IndustrialReforgedHandler.ENERGY_PER_JOULE, 0, motor.feProduced.get()));
    }

    @Override
    public int getEnergyCapacity() {
        return motor.feProduced.get() * IndustrialReforgedHandler.ENERGY_PER_JOULE;
    }

    @Override
    public void setEnergyCapacity(int cap) {

    }
}
