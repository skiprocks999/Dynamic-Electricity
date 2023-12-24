package dynamicelectricity.registry;

import com.google.common.collect.Sets;

import dynamicelectricity.References;
import dynamicelectricity.common.tile.TileMotorAcHv;
import dynamicelectricity.common.tile.TileMotorAcLv;
import dynamicelectricity.common.tile.TileMotorAcMv;
import dynamicelectricity.common.tile.TileMotorDcHv;
import dynamicelectricity.common.tile.TileMotorDcLv;
import dynamicelectricity.common.tile.TileMotorDcMv;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricityTiles {

	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, References.ID);
	
	
	public static final RegistryObject<TileEntityType<TileMotorAcHv>> TILE_MOTORAC_HV = TILES.register("motorachv", () -> new TileEntityType<>(TileMotorAcHv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorAcHv), null));
	public static final RegistryObject<TileEntityType<TileMotorAcMv>> TILE_MOTORAC_MV = TILES.register("motoracmc", () -> new TileEntityType<>(TileMotorAcMv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorAcMv), null));
	public static final RegistryObject<TileEntityType<TileMotorAcLv>> TILE_MOTORAC_LV = TILES.register("motoraclv", () -> new TileEntityType<>(TileMotorAcLv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorAcLv), null));
	public static final RegistryObject<TileEntityType<TileMotorDcHv>> TILE_MOTORDC_HV = TILES.register("motordchv", () -> new TileEntityType<>(TileMotorDcHv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorDcHv), null));
	public static final RegistryObject<TileEntityType<TileMotorDcMv>> TILE_MOTORDC_MV = TILES.register("motordcmv", () -> new TileEntityType<>(TileMotorDcMv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorDcMv), null));
	public static final RegistryObject<TileEntityType<TileMotorDcLv>> TILE_MOTORDC_LV = TILES.register("motordclv", () -> new TileEntityType<>(TileMotorDcLv::new, Sets.newHashSet(DynamicElectricityBlocks.blockMotorDcLv), null));

	
}
