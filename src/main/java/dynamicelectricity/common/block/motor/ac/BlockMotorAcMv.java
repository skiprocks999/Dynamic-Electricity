package dynamicelectricity.common.block.motor.ac;

import dynamicelectricity.common.tile.TileMotorAcMv;
import electrodynamics.common.block.BlockGenericMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockMotorAcMv extends BlockGenericMachine{

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileMotorAcMv(pos, state);
	}
}
