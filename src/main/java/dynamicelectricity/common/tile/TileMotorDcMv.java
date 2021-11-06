package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorDcMv extends TileMotorDC{

	public static int FE_CONSUMED = 10000;
	public static int VOLTAGE = 240;
	public static String NAME = "mv";
	
	public TileMotorDcMv(BlockPos pos, BlockState state) {
		super(DeferredRegisters.TILE_MOTORDC_MV.get(), pos, state, FE_CONSUMED, VOLTAGE, NAME);
	}

}
