package dynamicelectricity.datagen.server.recipe.custom.item2item;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.datagen.server.recipe.types.custom.item2item.ElectrodynamicsLatheRecipes;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import voltaic.common.tags.VoltaicTags;

public class DynamicElectricityLatheRecipes extends ElectrodynamicsLatheRecipes {

	public DynamicElectricityLatheRecipes() {
		super(DynamicElectricity.ID);
	}

	@Override
	public void addRecipes(RecipeOutput output) {

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_RINGIRON.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "ring_iron", modID)
				//
				.addItemTagInput(VoltaicTags.Items.PLATE_IRON, 1)
				//
				.save(output);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_RINGSTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "ring_steel", modID)
				//
				.addItemTagInput(VoltaicTags.Items.PLATE_STEEL, 1)
				//
				.save(output);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_SHAFTSTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "shaft_steel", modID)
				//
				.addItemTagInput(VoltaicTags.Items.STORAGE_BLOCK_STEEL, 1)
				//
				.save(output);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_SHAFTSTAINLESSSTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "shaft_stainlesssteel", modID)
				//
				.addItemTagInput(VoltaicTags.Items.STORAGE_BLOCK_STAINLESSSTEEL, 1)
				//
				.save(output);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_SHAFTHSLASTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "shaft_hslasteel", modID)
				//
				.addItemTagInput(VoltaicTags.Items.STORAGE_BLOCK_HSLASTEEL, 1)
				//
				.save(output);

	}

}
