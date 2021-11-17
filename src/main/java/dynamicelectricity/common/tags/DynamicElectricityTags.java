package dynamicelectricity.common.tags;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.common.fluid.FluidLubricant;
import electrodynamics.common.item.gear.tools.ItemCanister;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class DynamicElectricityTags {

	public static List<Tags.IOptionalNamedTag<Fluid>> FLUID_TAGS = new ArrayList<>();
	
	public static void init() {
		Fluids.init();
	}
	
	
	public static class Fluids {
		
		public static final Tags.IOptionalNamedTag<Fluid> LUBRICANT = forgeTag(FluidLubricant.FORGE_TAG);
		
		private static void init() {
			FLUID_TAGS.add(LUBRICANT);
			
			ItemCanister.addTag(LUBRICANT);
		}
		
		private static Tags.IOptionalNamedTag<Fluid> forgeTag(String name){
			return FluidTags.createOptional(new ResourceLocation("forge", name));
		}
	}
	
}
