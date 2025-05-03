package dynamicelectricity.datagen.server;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import voltaic.common.block.BlockMachine;

public class DynamicElectricityBlockTagsProvider extends BlockTagsProvider {

	public DynamicElectricityBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, DynamicElectricity.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getAllValuesArray(new BlockMachine[0]));

		tag(BlockTags.NEEDS_STONE_TOOL).add(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getAllValuesArray(new BlockMachine[0]));

	}

}
