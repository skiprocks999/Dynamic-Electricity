package dynamicelectricity.common.block.motor.dc;

import dynamicelectricity.common.tile.TileMotorDcLv;
import electrodynamics.common.block.BlockGenericMachine;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockLvDcMotor extends BlockGenericMachine{
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileMotorDcLv();
	}

}
