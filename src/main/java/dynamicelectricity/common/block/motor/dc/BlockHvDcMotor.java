package dynamicelectricity.common.block.motor.dc;

import dynamicelectricity.common.tile.TileMotorDcHv;
import electrodynamics.prefab.block.GenericMachineBlock;

public class BlockHvDcMotor extends GenericMachineBlock {

	public BlockHvDcMotor() {
		super(TileMotorDcHv::new);
	}

}
