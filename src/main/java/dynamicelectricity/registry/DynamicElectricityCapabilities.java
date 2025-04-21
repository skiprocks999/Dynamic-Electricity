package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.compatability.industrialreforged.IndustrialReforgedHandler;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import voltaic.prefab.tile.GenericTile;
import voltaic.registers.VoltaicCapabilities;

@EventBusSubscriber(modid = DynamicElectricity.ID, bus = EventBusSubscriber.Bus.MOD)
public class DynamicElectricityCapabilities {

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {

        DynamicElectricityTiles.TILES.getEntries().forEach(entry -> {
            event.registerBlockEntity(VoltaicCapabilities.CAPABILITY_ELECTRODYNAMIC_BLOCK, (BlockEntityType<? extends GenericTile>) entry.get(), (tile, context) -> tile.getElectrodynamicCapability(context));
            event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, (BlockEntityType<? extends GenericTile>) entry.get(), (tile, context) -> tile.getFluidHandlerCapability(context));
            event.registerBlockEntity(VoltaicCapabilities.CAPABILITY_GASHANDLER_BLOCK, (BlockEntityType<? extends GenericTile>) entry.get(), (tile, context) -> tile.getGasHandlerCapability(context));
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, (BlockEntityType<? extends GenericTile>) entry.get(), (tile, context) -> tile.getItemHandlerCapability(context));

        });


        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORAC_HV.get(), (tile, context) -> tile.getFECapability(context));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORAC_MV.get(), (tile, context) -> tile.getFECapability(context));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORAC_LV.get(), (tile, context) -> tile.getFECapability(context));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORDC_HV.get(), (tile, context) -> tile.getFECapability(context));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORDC_MV.get(), (tile, context) -> tile.getFECapability(context));
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORDC_LV.get(), (tile, context) -> tile.getFECapability(context));

        if(ModList.get().isLoaded(DynamicElectricity.INDUSTRIAL_REFORGED_ID)) {
            IndustrialReforgedHandler.registerCapabilities(event);
        }
    }
}
