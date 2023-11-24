package dynamicelectricity.common.block;

import java.util.stream.Stream;

import dynamicelectricity.registry.DynamicElectricityBlocks;
import electrodynamics.common.block.voxelshapes.VoxelShapes;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DynamicElectricityVoxelShapeRegistry {

	/**
	 * By convention this will be in alphabetical order
	 */
	public static void init() {

		/* HV AC MOTOR */

		VoxelShape hvacmotor = Stream.of(Block.box(0, 0, 0, 16, 1, 16),
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

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorAcHv, hvacmotor, Direction.WEST);

		/* HV DC MOTOR */

		VoxelShape hvdcmotor = Stream.of(
				//
				Stream.of(
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
								Block.box(1, 5.5, 5.5, 3, 10.5, 10.5)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(), Stream.of(
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
										Block.box(7, 11, 11, 16, 12, 13),
										//
										Block.box(7, 12, 11, 16, 13, 12),
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
				).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
				//
				Block.box(5, 1, 6, 9, 1.5, 10),
				//
				Block.box(9, 1, 6, 13, 2, 10)
		//
		).reduce((v1, v2) -> Shapes.or(v1, v2)).get();

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorDcHv, hvdcmotor, Direction.WEST);

		/* LV AC MOTOR */

		VoxelShape lvacmotor = Stream.of(
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
						Block.box(1, 6.5, 6.5, 3, 9.5, 9.5)
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
								Block.box(8, 3, 4, 14, 4, 12)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(7, 4, 6, 16, 12, 10),
								//
								Block.box(7, 6, 4, 16, 10, 12),
								//
								Block.box(7, 10, 10, 16, 11, 11),
								//
								Block.box(7, 5, 10, 16, 6, 11),
								//
								Block.box(7, 10, 5, 16, 11, 6),
								//
								Block.box(7, 5, 5, 16, 6, 6)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(6, 6, 5, 7, 10, 11),
								//
								Block.box(6, 5, 6, 7, 6, 10),
								//
								Block.box(6, 10, 6, 7, 11, 10),
								//
								Block.box(3, 7.5, 7.5, 6, 8.5, 8.5)
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

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorAcLv, lvacmotor, Direction.WEST);

		/* LV DC MOTOR */

		VoxelShape lvdcmotor = Stream.of(
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
						Block.box(1, 6.5, 6.5, 3, 9.5, 9.5)
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
								Block.box(8, 3, 4, 14, 4, 12)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(7, 4, 6, 16, 12, 10),
								//
								Block.box(7, 6, 4, 16, 10, 12),
								//
								Block.box(7, 10, 10, 16, 11, 11),
								//
								Block.box(7, 5, 10, 16, 6, 11),
								//
								Block.box(7, 10, 5, 16, 11, 6),
								//
								Block.box(7, 5, 5, 16, 6, 6)
						//
						).reduce((v1, v2) -> Shapes.or(v1, v2)).get(),
						//
						Stream.of(
								//
								Block.box(6, 6, 5, 7, 10, 11),
								//
								Block.box(6, 5, 6, 7, 6, 10),
								//
								Block.box(6, 10, 6, 7, 11, 10),
								//
								Block.box(3, 7.5, 7.5, 6, 8.5, 8.5)
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

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorDcLv, lvdcmotor, Direction.WEST);

		/* MV AC MOTOR */

		VoxelShape mvacmotor = Stream.of(
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

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorAcMv, mvacmotor, Direction.WEST);

		/* MV DC MOTOR */

		VoxelShape mvdcmotor = Stream.of(
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

		VoxelShapes.registerShape(DynamicElectricityBlocks.blockMotorDcMv, mvdcmotor, Direction.WEST);

	}
	
}
