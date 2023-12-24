package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.item.ItemConductorBrush;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.common.blockitem.BlockItemDescriptable;
import electrodynamics.common.item.ItemDescriptable;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricityItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.ID);

	static {

		ITEMS.register("motorachv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorAcHv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
		ITEMS.register("motoracmv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorAcMv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
		ITEMS.register("motoraclv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorAcLv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
		ITEMS.register("motordchv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorDcHv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
		ITEMS.register("motordcmv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorDcMv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
		ITEMS.register("motordclv", () -> new BlockItemDescriptable(() -> DynamicElectricityBlocks.blockMotorDcLv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));

	}
	
	public static final RegistryObject<Item> ITEM_LUBRICANTGEL = ITEMS.register("gellubricant", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));

	public static final RegistryObject<Item> ITEM_STATOR = ITEMS.register("stator", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));
	public static final RegistryObject<Item> ITEM_COMMUTATOR = ITEMS.register("commutator", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));
	public static final RegistryObject<Item> ITEM_ALTERNATOR = ITEMS.register("alternator", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));
	public static final RegistryObject<Item> ITEM_SHAFTSTEEL = ITEMS.register("shaftsteel", () -> new Item(new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
	public static final RegistryObject<Item> ITEM_SHAFTSTAINLESSSTEEL = ITEMS.register("shaftstainlesssteel", () -> new Item(new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
	public static final RegistryObject<Item> ITEM_SHAFTHSLASTEEL = ITEMS.register("shafthslasteel", () -> new Item(new Item.Properties().tab(References.DYELECTAB).stacksTo(1)));
	public static final RegistryObject<Item> ITEM_RINGIRON = ITEMS.register("ringiron", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));
	public static final RegistryObject<Item> ITEM_RINGSTEEL = ITEMS.register("ringsteel", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));
	public static final RegistryObject<Item> ITEM_CONDUCTORBRUSH = ITEMS.register("conductorbrush", () -> new ItemConductorBrush());
	
	public static final RegistryObject<Item> ITEM_DUSTCARBON = ITEMS.register("dustcarbon", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));
	public static final RegistryObject<Item> ITEM_DUSTPDSM = ITEMS.register("dustpdsm", () -> new ItemDescriptable(new Item.Properties().tab(References.DYELECTAB), UtilsText.tooltip("dustpdsm").withStyle(TextFormatting.GRAY)));
	public static final RegistryObject<Item> ITEM_OXIDE_TITANIUMCHLORIDE = ITEMS.register("oxidetitaniumchloride", () -> new Item(new Item.Properties().tab(References.DYELECTAB)));


}
