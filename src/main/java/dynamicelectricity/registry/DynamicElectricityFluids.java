package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import electrodynamics.registers.ElectrodynamicsItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import voltaic.common.fluid.FluidNonPlaceable;
import voltaic.common.fluid.SimpleWaterBasedFluidType;
import voltaic.prefab.utilities.math.Color;

public class DynamicElectricityFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, DynamicElectricity.ID);
	
	public static final DeferredHolder<Fluid, FluidNonPlaceable> FLUID_LUBRICANT = FLUIDS.register("fluidlubricant", () -> new FluidNonPlaceable(ElectrodynamicsItems.ITEM_CANISTERREINFORCED, new SimpleWaterBasedFluidType(DynamicElectricity.ID, "fluidlubricant", "lubricant", Color.WHITE)));

}
