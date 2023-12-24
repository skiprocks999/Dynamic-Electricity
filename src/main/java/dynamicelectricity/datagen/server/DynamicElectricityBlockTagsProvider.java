package dynamicelectricity.datagen.server;

import dynamicelectricity.References;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityBlockTagsProvider extends BlockTagsProvider {

	public DynamicElectricityBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, References.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

	}

}
