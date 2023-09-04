package dynamicelectricity.datagen.server.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import dynamicelectricity.datagen.server.recipe.custom.fluiditem2item.DynamicElectricityChemicalMixerRecipes;
import dynamicelectricity.datagen.server.recipe.custom.item2item.DynamicElectricityLatheRecipes;
import dynamicelectricity.datagen.server.recipe.custom.item2item.DynamicElectricityMineralCrusherRecipes;
import dynamicelectricity.datagen.server.recipe.custom.item2item.DynamicElectricityOxidationFurnaceRecipes;
import dynamicelectricity.datagen.server.recipe.vanilla.DynamicElectricityCraftingTableRecipes;
import electrodynamics.datagen.utils.recipe.AbstractRecipeGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public class DynamicElectricityRecipeProvider extends RecipeProvider {

	public final List<AbstractRecipeGenerator> GENERATORS = new ArrayList<>();
	
	
	public DynamicElectricityRecipeProvider(PackOutput output) {
		super(output);
		addRecipes();
	}
	
	public void addRecipes() {
		GENERATORS.add(new DynamicElectricityCraftingTableRecipes());
		GENERATORS.add(new DynamicElectricityOxidationFurnaceRecipes());
		GENERATORS.add(new DynamicElectricityMineralCrusherRecipes());
		GENERATORS.add(new DynamicElectricityLatheRecipes());
		GENERATORS.add(new DynamicElectricityChemicalMixerRecipes());
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		for(AbstractRecipeGenerator generator : GENERATORS) {
			generator.addRecipes(consumer);
		}
	}



}
