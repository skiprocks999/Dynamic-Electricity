package dynamicelectricity.datagen.server.recipe.custom.item2item;

import java.util.function.Consumer;

import dynamicelectricity.References;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.common.tags.ElectrodynamicsTags;
import electrodynamics.datagen.server.recipe.types.custom.item2item.ElectrodynamicsOxidationFurnaceRecipes;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;

public class DynamicElectricityOxidationFurnaceRecipes extends ElectrodynamicsOxidationFurnaceRecipes {

	public DynamicElectricityOxidationFurnaceRecipes() {
		super(References.ID);
	}

	@Override
	public void addRecipes(Consumer<IFinishedRecipe> consumer) {

		newRecipe(new ItemStack(DynamicElectricityItems.ITEM_DUSTPDSM.get(), 2), 0, OXIDATIONFURNACE_REQUIRED_TICKS, OXIDATIONFURNACE_USAGE_PER_TICK, "pdsm_dust")
				//
				.addItemTagInput(ElectrodynamicsTags.Items.DUST_SILICA, 1)
				//
				.addItemTagInput(DynamicElectricityTags.Items.DUST_COAL, 1)
				//
				.complete(consumer);

	}

}
