package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicElectricityFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, DynamicElectricity.ID);

    public static final RegistryObject<FluidType> FLUID_TYPE_LUBRICANT = FLUID_TYPES.register("fluidlubricant", () -> DynamicElectricityFluids.FLUID_LUBRICANT.get().getFluidType());

}
