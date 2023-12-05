package dynamicelectricity.datagen;

import dynamicelectricity.References;
import dynamicelectricity.datagen.client.DynamicElectricityBlockStateProvider;
import dynamicelectricity.datagen.client.DynamicElectricityItemModelsProvider;
import dynamicelectricity.datagen.client.DynamicElectricityLangKeyProvider;
import dynamicelectricity.datagen.client.DynamicElectricitySoundProvider;
import dynamicelectricity.datagen.server.DynamicElectricityBlockTagsProvider;
import dynamicelectricity.datagen.server.DynamicElectricityFluidTagsProvider;
import dynamicelectricity.datagen.server.DynamicElectricityItemTagsProvider;
import dynamicelectricity.datagen.server.DynamicElectricityLootTablesProvider;
import dynamicelectricity.datagen.server.recipe.DynamicElectricityRecipeProvider;
import electrodynamics.datagen.client.ElectrodynamicsLangKeyProvider.Locale;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = References.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {

		DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			
			DynamicElectricityBlockTagsProvider blockProvider = new DynamicElectricityBlockTagsProvider(generator, event.getExistingFileHelper());
			generator.addProvider(blockProvider);
			generator.addProvider(new DynamicElectricityItemTagsProvider(generator, blockProvider, event.getExistingFileHelper()));
			generator.addProvider(new DynamicElectricityFluidTagsProvider(generator, event.getExistingFileHelper()));
			generator.addProvider(new DynamicElectricityLootTablesProvider(generator));
			generator.addProvider(new DynamicElectricityRecipeProvider(generator));
			
		}
		if (event.includeClient()) {
			generator.addProvider(new DynamicElectricityBlockStateProvider(generator, event.getExistingFileHelper()));
			generator.addProvider(new DynamicElectricityItemModelsProvider(generator, event.getExistingFileHelper()));
			generator.addProvider(new DynamicElectricityLangKeyProvider(generator, Locale.EN_US));
			generator.addProvider(new DynamicElectricitySoundProvider(generator, event.getExistingFileHelper()));
		}
	}

}
