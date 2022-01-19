package dynamicelectricity.common.block.motor.ac;

import dynamicelectricity.common.tile.TileMotorAcHv;
import electrodynamics.prefab.block.GenericMachineBlock;

public class BlockMotorAcHv extends GenericMachineBlock {
	
	public BlockMotorAcHv() {
		super(TileMotorAcHv::new);
	}

}
