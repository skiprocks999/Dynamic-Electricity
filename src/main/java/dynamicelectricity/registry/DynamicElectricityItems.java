package dynamicelectricity.registry;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.References;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.common.item.ItemConductorBrush;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.creativetab.CreativeTabSupplier;
import electrodynamics.api.registration.BulkDeferredHolder;
import electrodynamics.common.blockitem.types.BlockItemDescriptable;
import electrodynamics.common.item.ItemDescriptable;
import electrodynamics.common.item.ItemElectrodynamics;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DynamicElectricityItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, References.ID);

	public static final BulkDeferredHolder<Item, BlockItemDescriptable, SubtypeDynamicMachine> ITEMS_DYNAMICMACHINE = new BulkDeferredHolder<>(SubtypeDynamicMachine.values(), subtype -> ITEMS.register(subtype.tag(), () -> new BlockItemDescriptable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(subtype), new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN)));

	public static final DeferredHolder<Item, Item> ITEM_STATOR = ITEMS.register("stator", () -> new ItemElectrodynamics(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_COMMUTATOR = ITEMS.register("commutator", () -> new ItemElectrodynamics(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_ALTERNATOR = ITEMS.register("alternator", () -> new ItemElectrodynamics(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_SHAFTSTEEL = ITEMS.register("shaftsteel", () -> new ItemElectrodynamics(new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_SHAFTSTAINLESSSTEEL = ITEMS.register("shaftstainlesssteel", () -> new ItemElectrodynamics(new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_SHAFTHSLASTEEL = ITEMS.register("shafthslasteel", () -> new ItemElectrodynamics(new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_RINGIRON = ITEMS.register("ringiron", () -> new ItemElectrodynamics(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_RINGSTEEL = ITEMS.register("ringsteel", () -> new ItemElectrodynamics(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_CONDUCTORBRUSH = ITEMS.register("conductorbrush", () -> new ItemConductorBrush());

	public static final DeferredHolder<Item, Item> ITEM_DUSTCARBON = ITEMS.register("dustcarbon", () -> new ItemElectrodynamics(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final DeferredHolder<Item, Item> ITEM_DUSTPDSM = ITEMS.register("dustpdsm", () -> new ItemDescriptable(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN, UtilsText.tooltip("dustpdsm").withStyle(ChatFormatting.GRAY)));

	@EventBusSubscriber(value = Dist.CLIENT, modid = References.ID, bus = EventBusSubscriber.Bus.MOD)
	private static class DynamicCreativeRegistry {

		@SubscribeEvent
		public static void registerItems(BuildCreativeModeTabContentsEvent event) {

			ITEMS.getEntries().forEach(reg -> {

				CreativeTabSupplier supplier = (CreativeTabSupplier) reg.get();

				if (supplier.hasCreativeTab() && supplier.isAllowedInCreativeTab(event.getTab())) {
					List<ItemStack> toAdd = new ArrayList<>();
					supplier.addCreativeModeItems(event.getTab(), toAdd);
					event.acceptAll(toAdd);
				}

			});

		}

	}

}
