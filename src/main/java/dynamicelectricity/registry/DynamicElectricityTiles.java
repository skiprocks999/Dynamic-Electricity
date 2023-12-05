package dynamicelectricity.registry;

import com.google.common.collect.Sets;

import dynamicelectricity.References;
import dynamicelectricity.common.tile.TileMotorAcHv;
import dynamicelectricity.common.tile.TileMotorAcLv;
import dynamicelectricity.common.tile.TileMotorAcMv;
import dynamicelectricity.common.tile.TileMotorDcHv;
import dynamicelectricity.common.tile.TileMotorDcLv;
import dynamicelectricity.common.tile.TileMotorDcMv;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicElectricityTiles {

	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, References.ID);
	
	
	public static final RegistryObject<BlockEntityType<TileMotorAcHv>> TILE_MOTORAC_HV = TILES.register("motorachv", () -> new BlockEntityType<>(TileMotorAcHv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorAcHv), null));
	public static final RegistryObject<BlockEntityType<TileMotorAcMv>> TILE_MOTORAC_MV = TILES.register("motoracmc", () -> new BlockEntityType<>(TileMotorAcMv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorAcMv), null));
	public static final RegistryObject<BlockEntityType<TileMotorAcLv>> TILE_MOTORAC_LV = TILES.register("motoraclv", () -> new BlockEntityType<>(TileMotorAcLv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorAcLv), null));
	public static final RegistryObject<BlockEntityType<TileMotorDcHv>> TILE_MOTORDC_HV = TILES.register("motordchv", () -> new BlockEntityType<>(TileMotorDcHv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorDcHv), null));
	public static final RegistryObject<BlockEntityType<TileMotorDcMv>> TILE_MOTORDC_MV = TILES.register("motordcmv", () -> new BlockEntityType<>(TileMotorDcMv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorDcMv), null));
	public static final RegistryObject<BlockEntityType<TileMotorDcLv>> TILE_MOTORDC_LV = TILES.register("motordclv", () -> new BlockEntityType<>(TileMotorDcLv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorDcLv), null));

	
}
