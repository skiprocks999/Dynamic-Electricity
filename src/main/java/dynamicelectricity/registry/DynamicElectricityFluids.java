package dynamicelectricity.registry;

import dynamicelectricity.References;
import electrodynamics.common.fluid.FluidNonPlaceable;
import electrodynamics.common.fluid.SimpleWaterBasedFluidType;
import electrodynamics.prefab.utilities.math.Color;
import electrodynamics.registers.ElectrodynamicsItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DynamicElectricityFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, References.ID);
	
	public static final DeferredHolder<Fluid, FluidNonPlaceable> FLUID_LUBRICANT = FLUIDS.register("fluidlubricant", () -> new FluidNonPlaceable(ElectrodynamicsItems.ITEM_CANISTERREINFORCED, new SimpleWaterBasedFluidType(References.ID, "fluidlubricant", "lubricant", Color.WHITE)));

}
