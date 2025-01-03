package dynamicelectricity.datagen.server.tags.types;

import java.util.concurrent.CompletableFuture;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import electrodynamics.common.block.BlockMachine;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DynamicElectricityBlockTagsProvider extends BlockTagsProvider {

	public DynamicElectricityBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, References.ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {

		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getAllValuesArray(new BlockMachine[0]));
		
		tag(BlockTags.NEEDS_STONE_TOOL).add(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getAllValuesArray(new BlockMachine[0]));

	}

}
