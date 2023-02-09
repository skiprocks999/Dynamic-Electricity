package dynamicelectricity.datagen.server.recipe.custom.item2item;

import java.util.function.Consumer;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.common.tags.ElectrodynamicsTags;
import electrodynamics.datagen.server.recipe.types.custom.item2item.ElectrodynamicsLatheRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;

public class DynamicElectricityLatheRecipes extends ElectrodynamicsLatheRecipes {

	public DynamicElectricityLatheRecipes() {
		super(References.ID);
	}

	@Override
	public void addRecipes(Consumer<FinishedRecipe> consumer) {

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_RINGIRON.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "ring_iron")
				//
				.addItemTagInput(ElectrodynamicsTags.Items.PLATE_IRON, 1)
				//
				.complete(consumer);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_RINGSTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "ring_steel")
				//
				.addItemTagInput(ElectrodynamicsTags.Items.PLATE_STEEL, 1)
				//
				.complete(consumer);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_SHAFTSTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "shaft_steel")
				//
				.addItemTagInput(ElectrodynamicsTags.Items.STORAGE_BLOCK_STEEL, 1)
				//
				.complete(consumer);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_SHAFTSTAINLESSSTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "shaft_stainlesssteel")
				//
				.addItemTagInput(ElectrodynamicsTags.Items.STORAGE_BLOCK_STAINLESSSTEEL, 1)
				//
				.complete(consumer);

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_SHAFTHSLASTEEL.get()), 0, LATHE_REQUIRED_TICKS, LATHE_USAGE_PER_TICK, "shaft_hslasteel")
				//
				.addItemTagInput(ElectrodynamicsTags.Items.STORAGE_BLOCK_HSLASTEEL, 1)
				//
				.complete(consumer);

	}

}
