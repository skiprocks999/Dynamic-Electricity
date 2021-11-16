package dynamicelectricity.common.block.motor.ac;

import dynamicelectricity.common.tile.TileMotorAcMv;
import electrodynamics.prefab.block.GenericMachineBlock;

public class BlockMotorAcMv extends GenericMachineBlock{

	public BlockMotorAcMv() {
		super(TileMotorAcMv::new);
	}

	
}
