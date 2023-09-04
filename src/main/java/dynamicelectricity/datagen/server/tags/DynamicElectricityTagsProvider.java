package dynamicelectricity.datagen.server.tags;

import java.util.concurrent.CompletableFuture;

import dynamicelectricity.datagen.server.tags.types.DynamicElectricityBlockTagsProvider;
import dynamicelectricity.datagen.server.tags.types.DynamicElectricityFluidTagsProvider;
import dynamicelectricity.datagen.server.tags.types.DynamicElectricityItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityTagsProvider {
	
	public static void addTagProviders(DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
		DynamicElectricityBlockTagsProvider blockProvider = new DynamicElectricityBlockTagsProvider(output, lookupProvider, helper);
		generator.addProvider(true, blockProvider);
		generator.addProvider(true, new DynamicElectricityItemTagsProvider(output, lookupProvider, blockProvider, helper));
		generator.addProvider(true, new DynamicElectricityFluidTagsProvider(output, lookupProvider, helper));
	}

}
