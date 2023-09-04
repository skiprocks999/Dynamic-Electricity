package dynamicelectricity.datagen.client;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import electrodynamics.datagen.client.ElectrodynamicsBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityBlockStateProvider extends ElectrodynamicsBlockStateProvider {

	public DynamicElectricityBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, exFileHelper, References.ID);
	}

	@Override
	protected void registerStatesAndModels() {
		
		horrRotatedBlock(DynamicElectricityBlocks.blockMotorAcLv, existingBlock(DynamicElectricityBlocks.blockMotorAcLv), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.blockMotorAcMv, existingBlock(DynamicElectricityBlocks.blockMotorAcMv), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.blockMotorAcHv, existingBlock(DynamicElectricityBlocks.blockMotorAcHv), 180, 0, false);
		
		horrRotatedBlock(DynamicElectricityBlocks.blockMotorDcLv, existingBlock(DynamicElectricityBlocks.blockMotorDcLv), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.blockMotorDcMv, existingBlock(DynamicElectricityBlocks.blockMotorDcMv), 180, 0, false);
		horrRotatedBlock(DynamicElectricityBlocks.blockMotorDcHv, existingBlock(DynamicElectricityBlocks.blockMotorDcHv), 180, 0, false);
	
	}
	
	
}
