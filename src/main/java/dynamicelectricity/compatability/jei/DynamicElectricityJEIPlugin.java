package dynamicelectricity.compatability.jei;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.registry.DynamicElectricityFluids;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.registration.IExtraIngredientRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class DynamicElectricityJEIPlugin implements IModPlugin {

	public static final ResourceLocation ID = DynamicElectricity.rl("jei");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerExtraIngredients(IExtraIngredientRegistration registration) {
		List<FluidStack> fluids = new ArrayList<>();
		for (DeferredHolder<Fluid, ? extends Fluid> fluid : DynamicElectricityFluids.FLUIDS.getEntries()) {
			fluids.add(new FluidStack(fluid.get(), 1000));
		}
		registration.addExtraIngredients(NeoForgeTypes.FLUID_STACK, fluids);
	}

}
