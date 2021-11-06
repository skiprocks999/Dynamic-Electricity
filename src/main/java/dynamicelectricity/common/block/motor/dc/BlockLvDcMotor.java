package dynamicelectricity.common.block.motor.dc;

import dynamicelectricity.common.tile.TileMotorDcLv;
import electrodynamics.common.block.BlockGenericMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockLvDcMotor extends BlockGenericMachine{
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TileMotorDcLv(pos, state);
	}

}
