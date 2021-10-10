package dynamicelectricity.common.block.motor.ac;

import dynamicelectricity.common.tile.TileMotorAcLv;
import electrodynamics.common.block.BlockGenericMachine;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockMotorAcLv extends BlockGenericMachine{

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileMotorAcLv();
	}
}
