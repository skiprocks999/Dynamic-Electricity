package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityTiles;

public class TileMotorAcHv extends TileMotorAC{

	public static int JOULES_CONSUMED = 100000;
	public static int ENERGY_TIER = 2;
	public static String NAME = "hv";
	
	public TileMotorAcHv() {
		super(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), ENERGY_TIER, JOULES_CONSUMED, NAME);
	}

}