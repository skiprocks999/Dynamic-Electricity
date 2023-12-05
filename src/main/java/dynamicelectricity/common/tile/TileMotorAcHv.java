package dynamicelectricity.common.tile;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileMotorAcHv extends TileMotorAC{

	public static int JOULES_CONSUMED = 100000;
	public static int ENERGY_TIER = 2;
	public static String NAME = "hv";
	
	public TileMotorAcHv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), pos, state, ENERGY_TIER, JOULES_CONSUMED, NAME);
	}

}