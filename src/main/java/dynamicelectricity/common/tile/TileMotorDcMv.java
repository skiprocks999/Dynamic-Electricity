package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorDC;

public class TileMotorDcMv extends TileMotorDC{

	public static int FE_CONSUMED = 10000;
	public static int VOLTAGE = 240;
	public static String NAME = "mv";
	
	public TileMotorDcMv() {
		super(DeferredRegisters.TILE_MOTORDC_MV.get(), FE_CONSUMED, VOLTAGE, NAME);
	}

}
