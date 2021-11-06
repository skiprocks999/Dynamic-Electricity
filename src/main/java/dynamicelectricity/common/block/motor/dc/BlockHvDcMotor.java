package dynamicelectricity.common.block.motor.dc;

import dynamicelectricity.common.tile.TileMotorDcHv;
import electrodynamics.common.block.BlockGenericMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockHvDcMotor extends BlockGenericMachine{

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileMotorDcHv(pos, state);
	}
}
