package dynamicelectricity.datagen.server;

import dynamicelectricity.References;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityFluidTagsProvider extends FluidTagsProvider {

	public DynamicElectricityFluidTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, References.ID, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		
		tag(DynamicElectricityTags.Fluids.LUBRICANT).add(DynamicElectricityFluids.fluidLubricant);
		
	}

}
