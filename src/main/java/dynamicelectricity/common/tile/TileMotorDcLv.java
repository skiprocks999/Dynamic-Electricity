package dynamicelectricity.common.tile;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorDcLv extends TileMotorDC{

	public static int FE_CONSUMED = 1000;
	public static int VOLTAGE = 120;
	public static String NAME = "lv";
	
	public TileMotorDcLv(BlockPos pos, BlockState state) {
		super(DeferredRegisters.TILE_MOTORDC_LV.get(), pos, state, FE_CONSUMED, VOLTAGE, NAME);
	}

}
