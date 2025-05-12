package dynamicelectricity.datagen.server.recipe.custom.fluiditem2item;

import java.util.function.Consumer;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityFluids;
import electrodynamics.datagen.server.recipe.types.custom.fluiditem2fluid.ElectrodynamicsChemicalMixerRecipes;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.fluids.FluidStack;

public class DynamicElectricityChemicalMixerRecipes extends ElectrodynamicsChemicalMixerRecipes {

	public DynamicElectricityChemicalMixerRecipes() {
		super(DynamicElectricity.ID);
	}

	@Override
	public void addRecipes(Consumer<IFinishedRecipe> consumer) {

		newRecipe(new FluidStack(DynamicElectricityFluids.FLUID_LUBRICANT.get(), 1000), 0, CHEMICALMIXER_REQUIRED_TICKS, CHEMICALMIXER_USAGE_PER_TICK, "lubricant")
				//
				.addItemTagInput(DynamicElectricityTags.Items.DUST_PDSM, 1)
				//
				.addFluidTagInput(FluidTags.WATER, 1000)
				//
				.complete(consumer);

	}

}
