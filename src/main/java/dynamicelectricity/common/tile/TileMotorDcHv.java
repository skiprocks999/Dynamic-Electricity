package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorDC;

public class TileMotorDcHv extends TileMotorDC{

	public static int FE_CONSUMED = 100000;
	public static int VOLTAGE = 480;
	public static String NAME = "hv";
	
	public TileMotorDcHv() {
		super(DeferredRegisters.TILE_MOTORDC_HV.get(), FE_CONSUMED, VOLTAGE, NAME);
	}

}
