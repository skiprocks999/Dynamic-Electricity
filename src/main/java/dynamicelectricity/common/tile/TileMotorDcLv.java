package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorDC;

public class TileMotorDcLv extends TileMotorDC{

	public static int FE_CONSUMED = 1000;
	public static int VOLTAGE = 120;
	public static String NAME = "lv";
	
	public TileMotorDcLv() {
		super(DeferredRegisters.TILE_MOTORDC_LV.get(), FE_CONSUMED, VOLTAGE, NAME);
	}

}
