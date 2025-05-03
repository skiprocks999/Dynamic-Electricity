package dynamicelectricity.datagen.server;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityFluidTagsProvider extends FluidTagsProvider {

	public DynamicElectricityFluidTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, DynamicElectricity.ID, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		
		tag(DynamicElectricityTags.Fluids.LUBRICANT).add(DynamicElectricityFluids.FLUID_LUBRICANT.get());
		
	}

}
