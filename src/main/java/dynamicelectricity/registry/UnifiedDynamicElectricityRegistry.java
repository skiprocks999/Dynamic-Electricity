package dynamicelectricity.registry;

import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.eventbus.api.IEventBus;
import voltaic.common.blockitem.BlockItemDescriptable;

public class UnifiedDynamicElectricityRegistry {

    public static void init(IEventBus bus) {
        DynamicElectricityBlocks.BLOCKS.register(bus);
        DynamicElectricityContainers.CONTAINERS.register(bus);
        DynamicElectricityFluids.FLUIDS.register(bus);
        DynamicElectricityItems.ITEMS.register(bus);
        DynamicElectricityTiles.TILES.register(bus);
        DynamicElectricitySounds.SOUNDS.register(bus);
    }

    static {

        BlockItemDescriptable.addDescription(() -> DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv), UtilsText.tooltip("motorachv.conversion").withStyle(TextFormatting.DARK_GRAY));

        BlockItemDescriptable.addDescription(() -> DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv), UtilsText.tooltip("motoracmv.conversion").withStyle(TextFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(() -> DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv), UtilsText.tooltip("motoraclv.conversion").withStyle(TextFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(() -> DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv), UtilsText.tooltip("motordchv.conversion").withStyle(TextFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(() -> DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv), UtilsText.tooltip("motordcmv.conversion").withStyle(TextFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(() -> DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv), UtilsText.tooltip("motordclv.conversion").withStyle(TextFormatting.DARK_GRAY));

    }
}
