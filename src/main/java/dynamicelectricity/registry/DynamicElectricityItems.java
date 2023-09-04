package dynamicelectricity.registry;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.References;
import dynamicelectricity.common.item.ItemConductorBrush;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.creativetab.CreativeTabSupplier;
import electrodynamics.common.blockitem.types.BlockItemDescriptable;
import electrodynamics.common.item.ItemDescriptable;
import electrodynamics.common.item.ItemElectrodynamics;
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

public class DynamicElectricityItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.ID);

	static {

		ITEMS.register("motorachv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorAcHv, new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
		ITEMS.register("motoracmv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorAcMv, new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
		ITEMS.register("motoraclv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorAcLv, new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
		ITEMS.register("motordchv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorDcHv, new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
		ITEMS.register("motordcmv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorDcMv, new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
		ITEMS.register("motordclv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorDcLv, new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));

	}

	public static final RegistryObject<Item> ITEM_STATOR = ITEMS.register("stator", () -> new ItemElectrodynamics(new Item.Properties(), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_COMMUTATOR = ITEMS.register("commutator", () -> new ItemElectrodynamics(new Item.Properties(), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_ALTERNATOR = ITEMS.register("alternator", () -> new ItemElectrodynamics(new Item.Properties(), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_SHAFTSTEEL = ITEMS.register("shaftsteel", () -> new ItemElectrodynamics(new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_SHAFTSTAINLESSSTEEL = ITEMS.register("shaftstainlesssteel", () -> new ItemElectrodynamics(new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_SHAFTHSLASTEEL = ITEMS.register("shafthslasteel", () -> new ItemElectrodynamics(new Item.Properties().stacksTo(1), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_RINGIRON = ITEMS.register("ringiron", () -> new ItemElectrodynamics(new Item.Properties(), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_RINGSTEEL = ITEMS.register("ringsteel", () -> new ItemElectrodynamics(new Item.Properties(), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_CONDUCTORBRUSH = ITEMS.register("conductorbrush", () -> new ItemConductorBrush());

	public static final RegistryObject<Item> ITEM_DUSTCARBON = ITEMS.register("dustcarbon", () -> new ItemElectrodynamics(new Item.Properties(), () -> DynamicElectricityCreativeTabs.MAIN.get()));
	public static final RegistryObject<Item> ITEM_DUSTPDSM = ITEMS.register("dustpdsm", () -> new ItemDescriptable(new Item.Properties(), () -> DynamicElectricityCreativeTabs.MAIN.get(), UtilsText.tooltip("dustpdsm").withStyle(ChatFormatting.GRAY)));

	@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = References.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
