package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorAcHv extends TileMotorAC{

	public static int JOULES_CONSUMED = 100000;
	public static int VOLTAGE = 480;
	public static String NAME = "hv";
	
	public TileMotorAcHv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), pos, state, VOLTAGE, JOULES_CONSUMED, NAME);
	}

}
