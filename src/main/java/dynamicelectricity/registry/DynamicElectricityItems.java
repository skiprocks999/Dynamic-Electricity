package dynamicelectricity.registry;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.common.item.ItemConductorBrush;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import voltaic.api.creativetab.CreativeTabSupplier;
import voltaic.api.registration.BulkRegistryObject;
import voltaic.common.blockitem.BlockItemDescriptable;
import voltaic.common.item.ItemDescriptable;
import voltaic.common.item.ItemVoltaic;

public class DynamicElectricityItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DynamicElectricity.ID);

	public static final BulkRegistryObject<BlockItemDescriptable, SubtypeDynamicMachine> ITEMS_DYNAMICMACHINE = new BulkRegistryObject<>(SubtypeDynamicMachine.values(), subtype -> ITEMS.register(subtype.tag(), () -> new BlockItemDescriptable(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(subtype), new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN)));

	public static final RegistryObject<Item> ITEM_STATOR = ITEMS.register("stator", () -> new ItemVoltaic(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_COMMUTATOR = ITEMS.register("commutator", () -> new ItemVoltaic(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_ALTERNATOR = ITEMS.register("alternator", () -> new ItemVoltaic(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_SHAFTSTEEL = ITEMS.register("shaftsteel", () -> new ItemVoltaic(new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_SHAFTSTAINLESSSTEEL = ITEMS.register("shaftstainlesssteel", () -> new ItemVoltaic(new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_SHAFTHSLASTEEL = ITEMS.register("shafthslasteel", () -> new ItemVoltaic(new Item.Properties().stacksTo(1), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_RINGIRON = ITEMS.register("ringiron", () -> new ItemVoltaic(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_RINGSTEEL = ITEMS.register("ringsteel", () -> new ItemVoltaic(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_CONDUCTORBRUSH = ITEMS.register("conductorbrush", ItemConductorBrush::new);

	public static final RegistryObject<Item> ITEM_DUSTCARBON = ITEMS.register("dustcarbon", () -> new ItemVoltaic(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN));
	public static final RegistryObject<Item> ITEM_DUSTPDSM = ITEMS.register("dustpdsm", () -> new ItemDescriptable(new Item.Properties(), DynamicElectricityCreativeTabs.MAIN, UtilsText.tooltip("dustpdsm").withStyle(ChatFormatting.DARK_GRAY)));

	@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = DynamicElectricity.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class ElectroCreativeRegistry {

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
