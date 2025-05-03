package dynamicelectricity.datagen.server;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.data.DataGenerator;
import voltaic.datagen.utils.server.loottable.BaseLootTablesProvider;

public class DynamicElectricityLootTablesProvider extends BaseLootTablesProvider {

	public DynamicElectricityLootTablesProvider(DataGenerator generator) {
		super(generator, DynamicElectricity.ID);
	}

	@Override
	protected void addTables() {
		
		addMachineTable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv), DynamicElectricityTiles.TILE_MOTORAC_LV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv), DynamicElectricityTiles.TILE_MOTORAC_MV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv), DynamicElectricityTiles.TILE_MOTORAC_HV, true, true, false, true, false);
		
		addMachineTable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv), DynamicElectricityTiles.TILE_MOTORDC_LV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv), DynamicElectricityTiles.TILE_MOTORDC_MV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv), DynamicElectricityTiles.TILE_MOTORDC_HV, true, true, false, true, false);
		
	}
	

}
