package dynamicelectricity.common.tags;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.common.fluid.FluidLubricant;
import electrodynamics.common.item.gear.tools.ItemCanister;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class DynamicElectricityTags {

	public static List<TagKey<Fluid>> FLUID_TAGS = new ArrayList<>();
	
	public static void init() {
		Fluids.init();
	}
	
	
	public static class Fluids {
		
		public static final TagKey<Fluid> LUBRICANT = forgeTag(FluidLubricant.FORGE_TAG);
		
		private static void init() {
			FLUID_TAGS.add(LUBRICANT);
			
			ItemCanister.addTag(LUBRICANT);
		}
		
		private static TagKey<Fluid> forgeTag(String name){
			return FluidTags.create(new ResourceLocation("forge", name));
		}
	}
	
}
