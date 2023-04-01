package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorAcMv extends TileMotorAC{

	public static int JOULES_CONSUMED = 10000;
	public static int ENERGY_TIER = 1;
	public static String NAME = "mv";
	
	public TileMotorAcMv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORAC_MV.get(), pos, state, ENERGY_TIER, JOULES_CONSUMED, NAME);
	}

}
