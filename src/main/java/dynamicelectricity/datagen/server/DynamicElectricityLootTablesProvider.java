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
		
		addIEFTable(DynamicElectricityBlocks.blockMotorAcLv, DynamicElectricityTiles.TILE_MOTORAC_LV);
		addIEFTable(DynamicElectricityBlocks.blockMotorAcMv, DynamicElectricityTiles.TILE_MOTORAC_MV);
		addIEFTable(DynamicElectricityBlocks.blockMotorAcHv, DynamicElectricityTiles.TILE_MOTORAC_HV);
		
		addIEFTable(DynamicElectricityBlocks.blockMotorDcLv, DynamicElectricityTiles.TILE_MOTORDC_LV);
		addIEFTable(DynamicElectricityBlocks.blockMotorDcMv, DynamicElectricityTiles.TILE_MOTORDC_MV);
		addIEFTable(DynamicElectricityBlocks.blockMotorDcHv, DynamicElectricityTiles.TILE_MOTORDC_HV);
		
	}
	

}
