package dynamicelectricity.common.tags;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import voltaic.Voltaic;

public class DynamicElectricityTags {

	public static List<INamedTag<Fluid>> FLUID_TAGS = new ArrayList<>();

	public static void init() {
		Items.init();
		Fluids.init();
	}

	public static class Items {
		
		public static final INamedTag<Item> DUST_COAL = forgeTag("dusts/coal");
		public static final INamedTag<Item> DUST_PDSM = forgeTag("dusts/pdsm");
		
		public static final INamedTag<Item> RING_IRON = forgeTag("rings/iron");
		public static final INamedTag<Item> RING_STEEL = forgeTag("rings/steel");

		private static void init() {
		}

		private static INamedTag<Item> forgeTag(String name) {
			return ItemTags.createOptional(Voltaic.forgerl(name));
		}

	}

	public static class Fluids {

		public static final INamedTag<Fluid> LUBRICANT = forgeTag("lubricant");

		private static void init() {
			
		}

		private static INamedTag<Fluid> forgeTag(String name) {
			return FluidTags.createOptional(Voltaic.forgerl(name));
		}
	}

}
