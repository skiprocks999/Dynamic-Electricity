package dynamicelectricity.common.tags;

import dynamicelectricity.common.fluid.FluidLubricant;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

public class DynamicElectricityTags {

	public static void init() {
		Items.init();
		Fluids.init();
	}

	public static class Items {
		
		public static final IOptionalNamedTag<Item> DUST_COAL = forgeTag("dusts/coal");
		public static final IOptionalNamedTag<Item> DUST_PDSM = forgeTag("dusts/pdsm");
		
		public static final IOptionalNamedTag<Item> RING_IRON = forgeTag("rings/iron");
		public static final IOptionalNamedTag<Item> RING_STEEL = forgeTag("rings/steel");

		private static void init() {
		}

		private static IOptionalNamedTag<Item> forgeTag(String name) {
			return ItemTags.createOptional(new ResourceLocation("forge", name));
		}

	}

	public static class Fluids {

		public static final IOptionalNamedTag<Fluid> LUBRICANT = forgeTag(FluidLubricant.FORGE_TAG);

		private static void init() {
			
		}

		private static IOptionalNamedTag<Fluid> forgeTag(String name) {
			return FluidTags.createOptional(new ResourceLocation("forge", name));
		}
	}

}
