package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorDcLv extends TileMotorDC{

	public static int FE_CONSUMED = 1000;
	public static int ENERGY_TIER = 0;
	public static String NAME = "lv";
	
	public TileMotorDcLv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORDC_LV.get(), pos, state, FE_CONSUMED, ENERGY_TIER, NAME);
	}

}