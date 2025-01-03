package dynamicelectricity.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import dynamicelectricity.References;
import dynamicelectricity.datagen.client.DynamicElectricityBlockStateProvider;
import dynamicelectricity.datagen.client.DynamicElectricityItemModelsProvider;
import dynamicelectricity.datagen.client.DynamicElectricityLangKeyProvider;
import dynamicelectricity.datagen.client.DynamicElectricitySoundProvider;
import dynamicelectricity.datagen.server.DynamicElectricityLootTablesProvider;
import dynamicelectricity.datagen.server.recipe.DynamicElectricityRecipeProvider;
import dynamicelectricity.datagen.server.tags.DynamicElectricityTagsProvider;
import electrodynamics.datagen.client.ElectrodynamicsLangKeyProvider.Locale;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {

		DataGenerator generator = event.getGenerator();

		PackOutput output = generator.getPackOutput();

		ExistingFileHelper helper = event.getExistingFileHelper();

		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		
		if (event.includeServer()) {
			
			generator.addProvider(true, new LootTableProvider(output, Collections.emptySet(), List.of(new LootTableProvider.SubProviderEntry(DynamicElectricityLootTablesProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
			generator.addProvider(true, new DynamicElectricityRecipeProvider(output, lookupProvider));
			DynamicElectricityTagsProvider.addTagProviders(generator, output, lookupProvider, helper);
			
		}
		if (event.includeClient()) {
			generator.addProvider(true, new DynamicElectricityBlockStateProvider(output, helper));
			generator.addProvider(true, new DynamicElectricityItemModelsProvider(output, helper));
			generator.addProvider(true, new DynamicElectricityLangKeyProvider(output, Locale.EN_US));
			generator.addProvider(true, new DynamicElectricitySoundProvider(output, helper));
		}
	}

}
