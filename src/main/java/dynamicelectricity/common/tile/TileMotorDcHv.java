package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityTiles;

public class TileMotorDcHv extends TileMotorDC{

	public static int FE_CONSUMED = 100000;
	public static int ENERGY_TIER = 2;
	public static String NAME = "hv";
	
	public TileMotorDcHv() {
		super(DynamicElectricityTiles.TILE_MOTORDC_HV.get(), FE_CONSUMED, ENERGY_TIER, NAME);
	}

}