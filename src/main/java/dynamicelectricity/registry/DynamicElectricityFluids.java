package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.fluid.FluidLubricant;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricityFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, References.ID);
	
	public static FluidLubricant fluidLubricant;
	
	static {
		FLUIDS.register("fluidlubricant", () -> fluidLubricant = new FluidLubricant());
	}
}
