package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityTiles;

public class TileMotorAcLv extends TileMotorAC{

	public static int JOULES_CONSUMED = 1000;
	public static int ENERGY_TIER = 0;
	public static String NAME = "lv";
	
	public TileMotorAcLv() {
		super(DynamicElectricityTiles.TILE_MOTORAC_LV.get(), ENERGY_TIER, JOULES_CONSUMED, NAME);
	}

}