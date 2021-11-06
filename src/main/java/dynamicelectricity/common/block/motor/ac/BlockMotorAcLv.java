package dynamicelectricity.common.block.motor.ac;

import dynamicelectricity.common.tile.TileMotorAcLv;
import electrodynamics.common.block.BlockGenericMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockMotorAcLv extends BlockGenericMachine{

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileMotorAcLv(pos, state);
	}
}
