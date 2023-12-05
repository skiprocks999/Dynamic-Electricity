package dynamicelectricity.datagen.server;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityBlockTagsProvider extends BlockTagsProvider {

	public DynamicElectricityBlockTagsProvider(DataGenerator pGenerator, ExistingFileHelper existingFileHelper) {
		super(pGenerator, References.ID, existingFileHelper);
	}

	@Override
	protected void addTags() {

		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DynamicElectricityBlocks.blockMotorAcLv, DynamicElectricityBlocks.blockMotorAcMv, DynamicElectricityBlocks.blockMotorAcHv, DynamicElectricityBlocks.blockMotorDcLv, DynamicElectricityBlocks.blockMotorDcMv, DynamicElectricityBlocks.blockMotorDcHv);
		
		tag(BlockTags.NEEDS_STONE_TOOL).add(DynamicElectricityBlocks.blockMotorAcLv, DynamicElectricityBlocks.blockMotorAcMv, DynamicElectricityBlocks.blockMotorAcHv, DynamicElectricityBlocks.blockMotorDcLv, DynamicElectricityBlocks.blockMotorDcMv, DynamicElectricityBlocks.blockMotorDcHv);

	}

}
