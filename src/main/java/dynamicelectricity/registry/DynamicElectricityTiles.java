package dynamicelectricity.registry;

import com.google.common.collect.Sets;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.common.tile.TileMotorAcHv;
import dynamicelectricity.common.tile.TileMotorAcLv;
import dynamicelectricity.common.tile.TileMotorAcMv;
import dynamicelectricity.common.tile.TileMotorDcHv;
import dynamicelectricity.common.tile.TileMotorDcLv;
import dynamicelectricity.common.tile.TileMotorDcMv;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DynamicElectricityTiles {

	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DynamicElectricity.ID);
	
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileMotorAcHv>> TILE_MOTORAC_HV = TILES.register("motorachv", () -> new BlockEntityType<>(TileMotorAcHv::new, Sets.newHashSet(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv)), null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileMotorAcMv>> TILE_MOTORAC_MV = TILES.register("motoracmc", () -> new BlockEntityType<>(TileMotorAcMv::new, Sets.newHashSet(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv)), null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileMotorAcLv>> TILE_MOTORAC_LV = TILES.register("motoraclv", () -> new BlockEntityType<>(TileMotorAcLv::new, Sets.newHashSet(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv)), null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileMotorDcHv>> TILE_MOTORDC_HV = TILES.register("motordchv", () -> new BlockEntityType<>(TileMotorDcHv::new, Sets.newHashSet(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv)), null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileMotorDcMv>> TILE_MOTORDC_MV = TILES.register("motordcmv", () -> new BlockEntityType<>(TileMotorDcMv::new, Sets.newHashSet(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv)), null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TileMotorDcLv>> TILE_MOTORDC_LV = TILES.register("motordclv", () -> new BlockEntityType<>(TileMotorDcLv::new, Sets.newHashSet(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv)), null));

	
}
