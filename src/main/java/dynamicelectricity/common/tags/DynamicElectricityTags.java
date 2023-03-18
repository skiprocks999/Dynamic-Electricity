package dynamicelectricity.common.tags;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.common.fluid.FluidLubricant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

public class DynamicElectricityTags {

	public static List<TagKey<Fluid>> FLUID_TAGS = new ArrayList<>();

	public static void init() {
		Items.init();
		Fluids.init();
	}

	public static class Items {
		
		public static final TagKey<Item> DUST_COAL = forgeTag("dusts/coal");
		public static final TagKey<Item> DUST_PDSM = forgeTag("dusts/pdsm");
		
		public static final TagKey<Item> RING_IRON = forgeTag("rings/iron");
		public static final TagKey<Item> RING_STEEL = forgeTag("rings/steel");

		private static void init() {
		}

		private static TagKey<Item> forgeTag(String name) {
			return ItemTags.create(new ResourceLocation("forge", name));
		}

	}

	public static class Fluids {

		public static final TagKey<Fluid> LUBRICANT = forgeTag(FluidLubricant.FORGE_TAG);

		private static void init() {
			
		}

		private static TagKey<Fluid> forgeTag(String name) {
			return FluidTags.create(new ResourceLocation("forge", name));
		}
	}

}
