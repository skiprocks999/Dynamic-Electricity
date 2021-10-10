package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorAC;

public class TileMotorAcHv extends TileMotorAC{

	public static int JOULES_CONSUMED = 50000;
	public static int VOLTAGE = 480;
	public static String NAME = "hv";
	
	public TileMotorAcHv() {
		super(DeferredRegisters.TILE_MOTORAC_HV.get(), VOLTAGE, JOULES_CONSUMED, NAME);
	}

}
