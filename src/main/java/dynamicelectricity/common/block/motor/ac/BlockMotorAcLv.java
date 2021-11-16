package dynamicelectricity.common.block.motor.ac;

import dynamicelectricity.common.tile.TileMotorAcLv;
import electrodynamics.prefab.block.GenericMachineBlock;


public class BlockMotorAcLv extends GenericMachineBlock {
	
	public BlockMotorAcLv() {
		super(TileMotorAcLv::new);
	
	}
}
