package dynamicelectricity.common.block.motor.dc;

import dynamicelectricity.common.tile.TileMotorDcLv;
import electrodynamics.prefab.block.GenericMachineBlock;

public class BlockLvDcMotor extends GenericMachineBlock {

	public BlockLvDcMotor() {
		super(TileMotorDcLv::new);
	}

}
