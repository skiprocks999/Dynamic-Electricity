package dynamicelectricity.datagen.server.recipe.custom.item2item;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.datagen.server.recipe.types.custom.item2item.ElectrodynamicsOxidationFurnaceRecipes;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import voltaic.common.tags.VoltaicTags;

public class DynamicElectricityOxidationFurnaceRecipes extends ElectrodynamicsOxidationFurnaceRecipes {

	public DynamicElectricityOxidationFurnaceRecipes() {
		super(DynamicElectricity.ID);
	}

	@Override
	public void addRecipes(RecipeOutput output) {

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_DUSTPDSM.get(), 2), 0, OXIDATIONFURNACE_REQUIRED_TICKS, OXIDATIONFURNACE_USAGE_PER_TICK, "pdsm_dust", modID)
				//
				.addItemTagInput(VoltaicTags.Items.DUST_SILICA, 1)
				//
				.addItemTagInput(DynamicElectricityTags.Items.DUST_COAL, 1)
				//
				.save(output);

	}

}
