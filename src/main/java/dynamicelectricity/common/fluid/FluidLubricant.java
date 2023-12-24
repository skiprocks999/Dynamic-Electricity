package dynamicelectricity.common.fluid;

import dynamicelectricity.References;
import electrodynamics.common.fluid.FluidNonPlaceable;
import electrodynamics.registers.ElectrodynamicsItems;

public class FluidLubricant extends FluidNonPlaceable {
	
	public static final String FORGE_TAG = "lubricant";
	
	public FluidLubricant() {
		super(() -> ElectrodynamicsItems.ITEM_CANISTERREINFORCED, References.ID, "lubricant");
	}

}
