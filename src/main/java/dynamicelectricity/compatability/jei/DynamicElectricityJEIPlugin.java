package dynamicelectricity.compatability.jei;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityFluids;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.registration.IExtraIngredientRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collection;

@JeiPlugin
public class DynamicElectricityJEIPlugin implements IModPlugin
{

	private static FluidStack makeFluidStack(RegistryObject<Fluid> fluid)
	{
		FluidStack fluidStack;
		fluidStack = new FluidStack(fluid.get(), 1000);
		return fluidStack;
	}

	@Override
	public void registerExtraIngredients(IExtraIngredientRegistration registration)
	{
		Collection<RegistryObject<Fluid>> fluids = DynamicElectricityFluids.FLUIDS.getEntries();
		Collection<FluidStack> modFluids = new ArrayList<>();
		for (RegistryObject<Fluid> fluid : fluids)
		{
			modFluids.add(makeFluidStack(fluid));
		}
		registration.addExtraIngredients(ForgeTypes.FLUID_STACK, modFluids);
	}

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(References.ID, "jei");
	}

}
