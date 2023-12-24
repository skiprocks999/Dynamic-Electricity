package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityTiles;

public class TileMotorDcMv extends TileMotorDC{

	public static int FE_CONSUMED = 10000;
	public static int ENERGY_TIER = 1;
	public static String NAME = "mv";
	
	public TileMotorDcMv() {
		super(DynamicElectricityTiles.TILE_MOTORDC_MV.get(), FE_CONSUMED, ENERGY_TIER, NAME);
	}

}