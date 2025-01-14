package dynamicelectricity.registry;

import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.common.blockitem.types.BlockItemDescriptable;
import net.minecraft.ChatFormatting;
import net.neoforged.bus.api.IEventBus;

public class UnifiedDynamicElectricityRegistry {

    public static void init(IEventBus bus) {
        DynamicElectricityBlocks.BLOCKS.register(bus);
        DynamicElectricityContainers.CONTAINERS.register(bus);
        DynamicElectricityFluids.FLUIDS.register(bus);
        DynamicElectricityFluidTypes.FLUID_TYPES.register(bus);
        DynamicElectricityItems.ITEMS.register(bus);
        DynamicElectricityTiles.TILES.register(bus);
        DynamicElectricitySounds.SOUNDS.register(bus);
        DynamicElectricityCreativeTabs.CREATIVE_TABS.register(bus);

    }

    static {

        BlockItemDescriptable.addDescription(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getHolder(SubtypeDynamicMachine.motorachv), UtilsText.tooltip("motorachv.conversion").withStyle(ChatFormatting.DARK_GRAY));

        BlockItemDescriptable.addDescription(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getHolder(SubtypeDynamicMachine.motoracmv), UtilsText.tooltip("motoracmv.conversion").withStyle(ChatFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getHolder(SubtypeDynamicMachine.motoraclv), UtilsText.tooltip("motoraclv.conversion").withStyle(ChatFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getHolder(SubtypeDynamicMachine.motordchv), UtilsText.tooltip("motordchv.conversion").withStyle(ChatFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getHolder(SubtypeDynamicMachine.motordcmv), UtilsText.tooltip("motordcmv.conversion").withStyle(ChatFormatting.DARK_GRAY));
        BlockItemDescriptable.addDescription(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getHolder(SubtypeDynamicMachine.motordclv), UtilsText.tooltip("motordclv.conversion").withStyle(ChatFormatting.DARK_GRAY));

    }
}
