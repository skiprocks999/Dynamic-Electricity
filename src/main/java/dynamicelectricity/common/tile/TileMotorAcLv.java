package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorAC;

public class TileMotorAcLv extends TileMotorAC{

	public static int JOULES_CONSUMED = 1000;
	public static int VOLTAGE = 120;
	public static String NAME = "lv";
	
	public TileMotorAcLv() {
		super(DeferredRegisters.TILE_MOTORAC_LV.get(), VOLTAGE, JOULES_CONSUMED, NAME);
	}

}
