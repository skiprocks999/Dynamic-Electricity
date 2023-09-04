package dynamicelectricity.common.tile;

import java.util.stream.Stream;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityTiles;
import electrodynamics.common.block.VoxelShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TileMotorAcHv extends TileMotorAC {

	public static int JOULES_CONSUMED = 100000;
	public static int ENERGY_TIER = 2;
	public static String NAME = "hv";

	public TileMotorAcHv(BlockPos pos, BlockState state) {
		super(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), pos, state, ENERGY_TIER, JOULES_CONSUMED, NAME);
	}

	static {

		VoxelShape shape = Stream.of(Block.box(0, 0, 0, 16, 1, 16),
				//
				Stream.of(
						//
						Block.box(0, 4, 4, 1, 12, 12),
						//
						Block.box(1, 0, 11, 2, 12, 13),
						//
						Block.box(1, 0, 3, 2, 12, 5),
						//
						Block.box(1, 5.5, 5.5, 3, 10.5, 10.5)
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
								Block.box(8, 2, 11.5, 14, 3, 12.5),
								//
								Block.box(8, 2, 3.5, 14, 3, 4.5)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(7, 2, 5, 16, 14, 11),
								//
								Block.box(7, 5, 2, 16, 11, 14),
								//
								Block.box(7, 11, 11, 16, 12, 13), Block.box(7, 12, 11, 16, 13, 12),
								//
								Block.box(7, 3, 11, 16, 4, 12),
								//
								Block.box(7, 4, 11, 16, 5, 13),
								//
								Block.box(7, 12, 4, 16, 13, 5),
								//
								Block.box(7, 11, 3, 16, 12, 5),
								//
								Block.box(7, 4, 3, 16, 5, 5),
								//
								Block.box(7, 3, 4, 16, 4, 5)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(6, 5, 4, 7, 11, 12),
								//
								Block.box(6, 4, 5, 7, 5, 11),
								//
								Block.box(6, 11, 5, 7, 12, 11),
								//
								Block.box(3, 6.5, 6.5, 6, 9.5, 9.5)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get()
				//
				).reduce((v1, v2) -> Shapes.or(v1, v2)).get()
		//
		).reduce((v1, v2) -> Shapes.or(v1, v2)).get();

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorAcHv, shape, Direction.WEST);

	}

}
