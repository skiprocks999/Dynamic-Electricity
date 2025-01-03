package dynamicelectricity.datagen.server.recipe.custom.fluiditem2item;

import dynamicelectricity.References;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityFluids;
import electrodynamics.datagen.server.recipe.types.custom.fluiditem2fluid.ElectrodynamicsChemicalMixerRecipes;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.FluidTags;
import net.neoforged.neoforge.fluids.FluidStack;

public class DynamicElectricityChemicalMixerRecipes extends ElectrodynamicsChemicalMixerRecipes {

	public DynamicElectricityChemicalMixerRecipes() {
		super(References.ID);
	}

	@Override
	public void addRecipes(RecipeOutput output) {

		newRecipe(new FluidStack(DynamicElectricityFluids.FLUID_LUBRICANT.get(), 1000), 0, CHEMICALMIXER_REQUIRED_TICKS, CHEMICALMIXER_USAGE_PER_TICK, "lubricant", modID)
				//
				.addItemTagInput(DynamicElectricityTags.Items.DUST_PDSM, 1)
				//
				.addFluidTagInput(FluidTags.WATER, 1000)
				//
				.save(output);

	}

}
