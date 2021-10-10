package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorAC;

public class TileMotorAcMv extends TileMotorAC{

	public static int JOULES_CONSUMED = 10000;
	public static int VOLTAGE = 240;
	public static String NAME = "mv";
	
	public TileMotorAcMv() {
		super(DeferredRegisters.TILE_MOTORAC_MV.get(), VOLTAGE, JOULES_CONSUMED, NAME);
	}

}
