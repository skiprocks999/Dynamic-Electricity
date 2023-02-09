package dynamicelectricity.common.fluid;

import dynamicelectricity.References;
import electrodynamics.common.fluid.FluidNonPlaceable;
import electrodynamics.common.fluid.types.SimpleWaterBasedFluidType;
import electrodynamics.registers.ElectrodynamicsItems;
import net.minecraftforge.fluids.FluidType;

public class FluidLubricant extends FluidNonPlaceable {
	
	public static final String FORGE_TAG = "lubricant";
	
	private final FluidType type;
	
	public FluidLubricant() {
		super(() -> ElectrodynamicsItems.ITEM_CANISTERREINFORCED);
		type = new SimpleWaterBasedFluidType(References.ID, "fluidlubricant");
	}
	
	@Override
	public FluidType getFluidType() {
		return type;
	}

}
