package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorAcLv extends TileMotorAC {

	public static int JOULES_CONSUMED = 1000;
	public static int ENERGY_TIER = 0;
	public static String NAME = "lv";

	public TileMotorAcLv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORAC_LV.get(), pos, state, ENERGY_TIER, JOULES_CONSUMED, NAME);
	}

}
