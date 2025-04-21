package dynamicelectricity.datagen.server.recipe.custom.item2item;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.datagen.server.recipe.types.custom.item2item.ElectrodynamicsMineralCrusherRecipes;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

public class DynamicElectricityMineralCrusherRecipes extends ElectrodynamicsMineralCrusherRecipes {

	public DynamicElectricityMineralCrusherRecipes() {
		super(DynamicElectricity.ID);
	}

	@Override
	public void addRecipes(RecipeOutput output) {

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_DUSTCARBON.get()), 0, MINERALCRUSHER_REQUIRED_TICKS, MINERALCRUSHER_USAGE_PER_TICK, "carbon_dust", modID)
				//
				.addItemTagInput(ItemTags.COALS, 1)
				//
				.save(output);

	}

}
