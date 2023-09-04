package dynamicelectricity.common.tile;

import java.util.stream.Stream;

import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityTiles;
import electrodynamics.common.block.VoxelShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TileMotorDcMv extends TileMotorDC {

	public static int FE_CONSUMED = 10000;
	public static int ENERGY_TIER = 1;
	public static String NAME = "mv";

	public TileMotorDcMv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORDC_MV.get(), pos, state, FE_CONSUMED, ENERGY_TIER, NAME);
	}

	static {

		VoxelShape shape = Stream.of(
				//
				Block.box(0, 0, 0, 16, 1, 16),
				//
				Stream.of(
						//
						Block.box(0, 4, 4, 1, 12, 12),
						//
						Block.box(1, 0, 11, 2, 12, 13),
						//
						Block.box(1, 0, 3, 2, 12, 5),
						//
						Block.box(1, 6, 6, 3, 10, 10)
				//
				).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
				//
				Stream.of(
						//
						Stream.of(
								//
								Block.box(8, 1, 12, 14, 2, 14),
								//
								Block.box(8, 1, 2, 14, 2, 4),
								//
								Block.box(8, 2, 11, 14, 3, 13),
								//
								Block.box(8, 2, 3, 14, 3, 5),
								//
								Block.box(8, 3, 10, 14, 4, 12),
								//
								Block.box(8, 3, 4, 14, 4, 6)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(7, 3, 6, 16, 13, 10),
								//
								Block.box(7, 6, 3, 16, 10, 13),
								//
								Block.box(7, 10, 10, 16, 12, 12),
								//
								Block.box(7, 4, 10, 16, 6, 12),
								//
								Block.box(7, 10, 4, 16, 12, 6),
								//
								Block.box(7, 4, 4, 16, 6, 6)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(6, 6, 4, 7, 10, 12),
								//
								Block.box(6, 5, 5, 7, 6, 11),
								//
								Block.box(6, 4, 6, 7, 5, 10),
								//
								Block.box(6, 10, 5, 7, 11, 11),
								//
								Block.box(6, 11, 6, 7, 12, 10),
								//
								Block.box(3, 7, 7, 6, 9, 9)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get()
				//
				).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
				//
				Block.box(5, 1, 6, 13, 2, 10),
				//
				Block.box(9, 2, 6, 13, 3, 10)
		//
		).reduce((v1, v2) -> Shapes.or(v1, v2)).get();

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorDcMv, shape, Direction.WEST);

	}

}
