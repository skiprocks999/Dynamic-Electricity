package dynamicelectricity.registry;

import dynamicelectricity.References;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DynamicElectricityFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, References.ID);

    public static final DeferredHolder<FluidType, FluidType> FLUID_TYPE_LUBRICANT = FLUID_TYPES.register("fluidlubricant", () -> DynamicElectricityFluids.FLUID_LUBRICANT.get().getFluidType());

}
