package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorDcHv extends TileMotorDC{

	public static int FE_CONSUMED = 100000;
	public static int VOLTAGE = 480;
	public static String NAME = "hv";
	
	public TileMotorDcHv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORDC_HV.get(), pos, state, FE_CONSUMED, VOLTAGE, NAME);
	}

}
