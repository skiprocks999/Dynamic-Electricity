package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorAcMv extends TileMotorAC{

	public static int JOULES_CONSUMED = 10000;
	public static int VOLTAGE = 240;
	public static String NAME = "mv";
	
	public TileMotorAcMv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORAC_MV.get(), pos, state, VOLTAGE, JOULES_CONSUMED, NAME);
	}

}
