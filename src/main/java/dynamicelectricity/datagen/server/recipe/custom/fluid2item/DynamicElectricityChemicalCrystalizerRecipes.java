package dynamicelectricity.datagen.server.recipe.custom.fluid2item;

import java.util.function.Consumer;

import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.datagen.server.recipe.types.custom.fluid2item.ElectrodynamicsChemicalCrystallizerRecipes;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;

public class DynamicElectricityChemicalCrystalizerRecipes extends ElectrodynamicsChemicalCrystallizerRecipes {

	@Override
	public void addRecipes(Consumer<IFinishedRecipe> consumer) {
		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_LUBRICANTGEL.get()), 0, 200, 800.0, "lubricant_gel_from_lubricant")
				//
				.addFluidTagInput(DynamicElectricityTags.Fluids.LUBRICANT, 250)
				//
				.complete(consumer);
	}

}
