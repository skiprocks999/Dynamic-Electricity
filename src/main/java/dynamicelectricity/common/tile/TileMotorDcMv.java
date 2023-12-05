package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorDcMv extends TileMotorDC{

	public static int FE_CONSUMED = 10000;
	public static int ENERGY_TIER = 1;
	public static String NAME = "mv";
	
	public TileMotorDcMv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORDC_MV.get(), pos, state, FE_CONSUMED, ENERGY_TIER, NAME);
	}

}