package dynamicelectricity.datagen.server;

import dynamicelectricity.References;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityItemTagsProvider extends ItemTagsProvider {

	public DynamicElectricityItemTagsProvider(DataGenerator generator, BlockTagsProvider provider,
			ExistingFileHelper existingFileHelper) {
		super(generator, provider, References.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		tag(DynamicElectricityTags.Items.DUST_COAL).add(DynamicElectricityItems.ITEM_DUSTCARBON.get());
		tag(DynamicElectricityTags.Items.DUST_PDSM).add(DynamicElectricityItems.ITEM_DUSTPDSM.get());
		
		tag(DynamicElectricityTags.Items.RING_IRON).add(DynamicElectricityItems.ITEM_RINGIRON.get());
		tag(DynamicElectricityTags.Items.RING_STEEL).add(DynamicElectricityItems.ITEM_RINGSTEEL.get());
	}

}
