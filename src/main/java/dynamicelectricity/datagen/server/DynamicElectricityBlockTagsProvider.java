package dynamicelectricity.datagen.server;

import dynamicelectricity.DynamicElectricity;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityBlockTagsProvider extends BlockTagsProvider {

	public DynamicElectricityBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, DynamicElectricity.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

	}

}
