package dynamicelectricity.common.fluid;

import dynamicelectricity.References;
import electrodynamics.common.fluid.FluidNonPlaceable;

public class FluidLubricant extends FluidNonPlaceable {
	
	public FluidLubricant() {
		super(() -> electrodynamics.DeferredRegisters.ITEM_CANISTERREINFORCED, References.ID, "lubricant");
	}

}
