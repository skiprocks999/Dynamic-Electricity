package dynamicelectricity.common.tags;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import voltaic.Voltaic;

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
			return ItemTags.create(Voltaic.forgerl(name));
		}

	}

	public static class Fluids {

		public static final TagKey<Fluid> LUBRICANT = forgeTag("lubricant");

		private static void init() {
			
		}

		private static TagKey<Fluid> forgeTag(String name) {
			return FluidTags.create(Voltaic.forgerl(name));
		}
	}

}
