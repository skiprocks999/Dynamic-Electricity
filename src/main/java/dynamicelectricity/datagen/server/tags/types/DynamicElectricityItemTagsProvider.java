package dynamicelectricity.datagen.server.tags.types;

import java.util.concurrent.CompletableFuture;

import dynamicelectricity.References;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityItemTagsProvider extends ItemTagsProvider {

	public DynamicElectricityItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, provider.contentsGetter(), References.ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {

		tag(DynamicElectricityTags.Items.DUST_COAL).add(DynamicElectricityItems.ITEM_DUSTCARBON.get());
		tag(DynamicElectricityTags.Items.DUST_PDSM).add(DynamicElectricityItems.ITEM_DUSTPDSM.get());
		
		tag(DynamicElectricityTags.Items.RING_IRON).add(DynamicElectricityItems.ITEM_RINGIRON.get());
		tag(DynamicElectricityTags.Items.RING_STEEL).add(DynamicElectricityItems.ITEM_RINGSTEEL.get());
	}

}
