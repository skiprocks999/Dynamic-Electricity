package dynamicelectricity.common.block.motor.ac;

import dynamicelectricity.common.tile.TileMotorAcHv;
import electrodynamics.common.block.BlockGenericMachine;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BlockMotorAcHv extends BlockGenericMachine{

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileMotorAcHv();
	}

}
