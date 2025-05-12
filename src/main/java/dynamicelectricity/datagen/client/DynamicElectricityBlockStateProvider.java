package dynamicelectricity.datagen.client;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import voltaic.datagen.utils.client.BaseBlockstateProvider;

public class DynamicElectricityBlockStateProvider extends BaseBlockstateProvider {

	public DynamicElectricityBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, exFileHelper, DynamicElectricity.ID);
	}

	@Override
	protected void registerStatesAndModels() {
		
		horrRotatedBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv), existingBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv)), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv), existingBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv)), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv), existingBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv)), 180, 0, false);
		
		horrRotatedBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv), existingBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv)), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv), existingBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv)), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv), existingBlock(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv)), 180, 0, false);
	
	}
	
	
}
