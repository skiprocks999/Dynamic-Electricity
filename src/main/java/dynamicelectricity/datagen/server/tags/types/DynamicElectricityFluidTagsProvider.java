package dynamicelectricity.datagen.server.tags.types;

import java.util.concurrent.CompletableFuture;

import dynamicelectricity.References;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityFluidTagsProvider extends FluidTagsProvider {

	public DynamicElectricityFluidTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, References.ID, existingFileHelper);
	}
	
	@Override
	protected void addTags(Provider provider) {
		
		tag(DynamicElectricityTags.Fluids.LUBRICANT).add(DynamicElectricityFluids.fluidLubricant);
		
	}

}
