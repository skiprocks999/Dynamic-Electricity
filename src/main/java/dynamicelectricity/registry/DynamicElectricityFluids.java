package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import electrodynamics.registers.ElectrodynamicsItems;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import voltaic.common.fluid.FluidNonPlaceable;
import voltaic.prefab.utilities.math.Color;

public class DynamicElectricityFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, DynamicElectricity.ID);
	
	public static final RegistryObject<FluidNonPlaceable> FLUID_LUBRICANT = FLUIDS.register("fluidlubricant", () -> new FluidNonPlaceable(() -> ElectrodynamicsItems.ITEM_CANISTERREINFORCED.get(), DynamicElectricity.ID, "fluidlubricant", "lubricant", Color.WHITE));
}
