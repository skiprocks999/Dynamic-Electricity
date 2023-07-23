package dynamicelectricity.datagen.server;

import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityTiles;
import electrodynamics.datagen.server.ElectrodynamicsLootTablesProvider;
import net.minecraft.data.DataGenerator;

public class DynamicElectricityLootTablesProvider extends ElectrodynamicsLootTablesProvider {

	public DynamicElectricityLootTablesProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void addTables() {
		
		addMachineTable(DynamicElectricityBlocks.blockMotorAcLv, DynamicElectricityTiles.TILE_MOTORAC_LV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.blockMotorAcMv, DynamicElectricityTiles.TILE_MOTORAC_MV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.blockMotorAcHv, DynamicElectricityTiles.TILE_MOTORAC_HV, true, true, false, true, false);
		
		addMachineTable(DynamicElectricityBlocks.blockMotorDcLv, DynamicElectricityTiles.TILE_MOTORDC_LV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.blockMotorDcMv, DynamicElectricityTiles.TILE_MOTORDC_MV, true, true, false, true, false);
		addMachineTable(DynamicElectricityBlocks.blockMotorDcHv, DynamicElectricityTiles.TILE_MOTORDC_HV, true, true, false, true, false);
		
	}
	

}
