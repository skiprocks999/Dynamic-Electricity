package dynamicelectricity.datagen.server.tags.types;

import java.util.concurrent.CompletableFuture;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityBlockTagsProvider extends BlockTagsProvider {

	public DynamicElectricityBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, References.ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {

		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DynamicElectricityBlocks.blockMotorAcLv, DynamicElectricityBlocks.blockMotorAcMv, DynamicElectricityBlocks.blockMotorAcHv, DynamicElectricityBlocks.blockMotorDcLv, DynamicElectricityBlocks.blockMotorDcMv, DynamicElectricityBlocks.blockMotorDcHv);
		
		tag(BlockTags.NEEDS_STONE_TOOL).add(DynamicElectricityBlocks.blockMotorAcLv, DynamicElectricityBlocks.blockMotorAcMv, DynamicElectricityBlocks.blockMotorAcHv, DynamicElectricityBlocks.blockMotorDcLv, DynamicElectricityBlocks.blockMotorDcMv, DynamicElectricityBlocks.blockMotorDcHv);

	}

}
