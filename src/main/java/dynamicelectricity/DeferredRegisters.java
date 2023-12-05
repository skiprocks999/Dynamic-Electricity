package dynamicelectricity;

//import com.google.common.base.Supplier;
//import com.google.common.collect.Sets;
//
//import dynamicelectricity.common.block.motor.ac.BlockMotorAcHv;
//import dynamicelectricity.common.block.motor.ac.BlockMotorAcLv;
//import dynamicelectricity.common.block.motor.ac.BlockMotorAcMv;
//import dynamicelectricity.common.block.motor.dc.BlockHvDcMotor;
//import dynamicelectricity.common.block.motor.dc.BlockLvDcMotor;
//import dynamicelectricity.common.block.motor.dc.BlockMvDcMotor;
//import dynamicelectricity.common.fluid.FluidLubricant;
//import dynamicelectricity.common.inventory.container.ContainerMotorAC;
//import dynamicelectricity.common.inventory.container.ContainerMotorDC;
//import dynamicelectricity.common.tile.TileMotorAcHv;
//import dynamicelectricity.common.tile.TileMotorAcLv;
//import dynamicelectricity.common.tile.TileMotorAcMv;
//import dynamicelectricity.common.tile.TileMotorDcHv;
//import dynamicelectricity.common.tile.TileMotorDcLv;
//import dynamicelectricity.common.tile.TileMotorDcMv;
//import electrodynamics.common.blockitem.BlockItemDescriptable;
//import electrodynamics.common.item.ItemDescriptable;
//import net.minecraft.world.inventory.MenuType;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.material.Fluid;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.ForgeRegistryEntry;
//import net.minecraftforge.registries.RegistryObject;

public class DeferredRegisters {

//	public static final int BRUSH_DURABILITY = 100000;
//
//	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.ID);
//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.ID);
//    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, References.ID);
//    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, References.ID);
//    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, References.ID);
//    
//    public static BlockMotorAcHv blockMotorAcHv;
//    public static BlockMotorAcMv blockMotorAcMv;
//    public static BlockMotorAcLv blockMotorAcLv;
//    public static BlockHvDcMotor blockMotorDcHv;
//    public static BlockMvDcMotor blockMotorDcMv;
//    public static BlockLvDcMotor blockMotorDcLv;
//    
//    public static FluidLubricant fluidLubricant;
//    
//    static {
//    	BLOCKS.register("motorachv", supplier(() -> blockMotorAcHv = new BlockMotorAcHv()));
//    	BLOCKS.register("motoracmv", supplier(() -> blockMotorAcMv = new BlockMotorAcMv()));
//    	BLOCKS.register("motoraclv", supplier(() -> blockMotorAcLv = new BlockMotorAcLv()));
//    	BLOCKS.register("motordchv", supplier(() -> blockMotorDcHv = new BlockHvDcMotor()));
//    	BLOCKS.register("motordcmv", supplier(() -> blockMotorDcMv = new BlockMvDcMotor()));
//    	BLOCKS.register("motordclv", supplier(() -> blockMotorDcLv = new BlockLvDcMotor()));
//    	
//    	BlockItemDescriptable.addDescription(() -> blockMotorAcHv, "|translate|tooltip.motorachv.conversion");
//    	BlockItemDescriptable.addDescription(() -> blockMotorAcMv, "|translate|tooltip.motoracmv.conversion");
//    	BlockItemDescriptable.addDescription(() -> blockMotorAcLv, "|translate|tooltip.motoraclv.conversion");
//    	BlockItemDescriptable.addDescription(() -> blockMotorDcHv, "|translate|tooltip.motordchv.conversion");
//    	BlockItemDescriptable.addDescription(() -> blockMotorDcMv, "|translate|tooltip.motordcmv.conversion");
//    	BlockItemDescriptable.addDescription(() -> blockMotorDcLv, "|translate|tooltip.motordclv.conversion");
//    	
//    	ITEMS.register("motorachv", 
//    		supplier(() -> new BlockItemDescriptable(() -> blockMotorAcHv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    	ITEMS.register("motoracmv", 
//        	supplier(() -> new BlockItemDescriptable(() -> blockMotorAcMv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    	ITEMS.register("motoraclv", 
//        	supplier(() -> new BlockItemDescriptable(() -> blockMotorAcLv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    	ITEMS.register("motordchv", 
//        	supplier(() -> new BlockItemDescriptable(() -> blockMotorDcHv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    	ITEMS.register("motordcmv", 
//        	supplier(() -> new BlockItemDescriptable(() -> blockMotorDcMv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    	ITEMS.register("motordclv", 
//        	supplier(() -> new BlockItemDescriptable(() -> blockMotorDcLv, new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    
//    	ITEMS.register("gellubricant", supplier(() -> new ItemDescriptable(new Item.Properties().stacksTo(64).tab(References.DYELECTAB), "tooltip.info.depricated")));
//    	
//    	FLUIDS.register("fluidlubricant", supplier(() -> fluidLubricant = new FluidLubricant()));
//    }
//    
//    public static final RegistryObject<Item> ITEM_STATOR = ITEMS.register("stator",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB))));
//    public static final RegistryObject<Item> ITEM_COMMUTATOR = ITEMS.register("commutator",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB))));
//    public static final RegistryObject<Item> ITEM_ALTERNATOR = ITEMS.register("alternator",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB))));
//    public static final RegistryObject<Item> ITEM_SHAFTSTEEL = ITEMS.register("shaftsteel",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    public static final RegistryObject<Item> ITEM_SHAFTSTAINLESSSTEEL = ITEMS.register("shaftstainlesssteel",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    public static final RegistryObject<Item> ITEM_SHAFTHSLASTEEL = ITEMS.register("shafthslasteel",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB).stacksTo(1))));
//    public static final RegistryObject<Item> ITEM_RINGIRON = ITEMS.register("ringiron",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB))));
//    public static final RegistryObject<Item> ITEM_RINGSTEEL = ITEMS.register("ringsteel",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB))));
//    public static final RegistryObject<Item> ITEM_OXIDE_TITANIUMCHLORIDE = ITEMS.register("oxidetitaniumchloride",
//        supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB))));
//    public static final RegistryObject<Item> ITEM_CONDUCTORBRUSH = ITEMS.register("conductorbrush",
//    	supplier(() -> new Item(new Item.Properties().tab(References.DYELECTAB).durability(BRUSH_DURABILITY))));
//    
//    public static final RegistryObject<BlockEntityType<TileMotorAcHv>> TILE_MOTORAC_HV = TILES.register("motorachv",
//    	() -> new BlockEntityType<>(TileMotorAcHv::new ,Sets.newHashSet(blockMotorAcHv),null));
//    public static final RegistryObject<BlockEntityType<TileMotorAcMv>> TILE_MOTORAC_MV = TILES.register("motoracmc",
//    	() -> new BlockEntityType<>(TileMotorAcMv::new, Sets.newHashSet(blockMotorAcMv),null));
//    public static final RegistryObject<BlockEntityType<TileMotorAcLv>> TILE_MOTORAC_LV = TILES.register("motoraclv",
//    	() -> new BlockEntityType<>(TileMotorAcLv::new,Sets.newHashSet(blockMotorAcLv),null));
//    public static final RegistryObject<BlockEntityType<TileMotorDcHv>> TILE_MOTORDC_HV = TILES.register("motordchv",
//    	() -> new BlockEntityType<>(TileMotorDcHv::new,Sets.newHashSet(blockMotorDcHv),null));
//    public static final RegistryObject<BlockEntityType<TileMotorDcMv>> TILE_MOTORDC_MV = TILES.register("motordcmv",
//    	() -> new BlockEntityType<>(TileMotorDcMv::new, Sets.newHashSet(blockMotorDcMv),null));
//    public static final RegistryObject<BlockEntityType<TileMotorDcLv>> TILE_MOTORDC_LV = TILES.register("motordclv",
//    	() -> new BlockEntityType<>(TileMotorDcLv::new, Sets.newHashSet(blockMotorDcLv),null));
//    
//    public static final RegistryObject<MenuType<ContainerMotorAC>> CONTAINER_MOTORAC = CONTAINERS.register
//    	("motorac", () -> new MenuType<>(ContainerMotorAC::new));
//    public static final RegistryObject<MenuType<ContainerMotorDC>> CONTAINER_MOTORDC = CONTAINERS.register
//    	("motordc", () -> new MenuType<>(ContainerMotorDC::new));
//    
//    private static <T extends ForgeRegistryEntry<T>> Supplier<? extends T> supplier(Supplier<? extends T> entry) {
//		return entry;
//	}
    
}
