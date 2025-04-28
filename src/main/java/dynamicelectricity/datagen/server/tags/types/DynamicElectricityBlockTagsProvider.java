package dynamicelectricity.datagen.server.tags.types;

import java.util.concurrent.CompletableFuture;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import voltaic.common.block.BlockMachine;

public class DynamicElectricityBlockTagsProvider extends BlockTagsProvider {

	public DynamicElectricityBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, DynamicElectricity.ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {

		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getAllValuesArray(new BlockMachine[0]));

		tag(BlockTags.NEEDS_STONE_TOOL).add(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getAllValuesArray(new BlockMachine[0]));

	}

}
