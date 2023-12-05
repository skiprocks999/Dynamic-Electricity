package dynamicelectricity.datagen.server.recipe.custom.item2item;

import java.util.function.Consumer;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.datagen.server.recipe.types.custom.item2item.ElectrodynamicsMineralCrusherRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

public class DynamicElectricityMineralCrusherRecipes extends ElectrodynamicsMineralCrusherRecipes {

	public DynamicElectricityMineralCrusherRecipes() {
		super(References.ID);
	}

	@Override
	public void addRecipes(Consumer<FinishedRecipe> consumer) {

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_DUSTCARBON.get()), 0, MINERALCRUSHER_REQUIRED_TICKS, MINERALCRUSHER_USAGE_PER_TICK, "carbon_dust")
				//
				.addItemTagInput(ItemTags.COALS, 1)
				//
				.complete(consumer);

	}

}
