package dynamicelectricity.common.block.motor.dc;

import dynamicelectricity.common.tile.TileMotorDcMv;
import electrodynamics.prefab.block.GenericMachineBlock;

public class BlockMvDcMotor extends GenericMachineBlock {

	public BlockMvDcMotor() {
		super(TileMotorDcMv::new);
	}

}
