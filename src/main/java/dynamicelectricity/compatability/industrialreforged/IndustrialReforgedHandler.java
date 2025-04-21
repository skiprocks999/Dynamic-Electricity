package dynamicelectricity.compatability.industrialreforged;

import java.util.List;

import com.indref.industrial_reforged.api.capabilities.IRCapabilities;
import com.indref.industrial_reforged.api.capabilities.energy.IEnergyStorage;
import com.indref.industrial_reforged.api.tiers.EnergyTier;
import com.indref.industrial_reforged.registries.IRBlocks;
import com.indref.industrial_reforged.tiers.EnergyTiers;
import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.core.utils.DynamicDisplayUnits;
import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import voltaic.api.electricity.formatting.ChatFormatter;
import voltaic.api.electricity.formatting.DisplayUnits;
import voltaic.prefab.utilities.VoltaicTextUtils;

//Buffer class to prevent stuff loading that shouldn't
public class IndustrialReforgedHandler {

    public static final int ENERGY_PER_JOULE = 4;
    
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(IRCapabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORAC_HV.get(), (tile, context) -> {
            Direction facing = tile.getFacing();
            if(context == facing.getOpposite()) {
                return new OutputCap(tile, getTier(tile.energyTier));
            } else {
                return null;
            }

        });
        event.registerBlockEntity(IRCapabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORAC_MV.get(), (tile, context) -> {
            Direction facing = tile.getFacing();
            if(context == facing.getOpposite()) {
                return new OutputCap(tile, getTier(tile.energyTier));
            } else {
                return null;
            }

        });
        event.registerBlockEntity(IRCapabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORAC_LV.get(), (tile, context) -> {
            Direction facing = tile.getFacing();
            if(context == facing.getOpposite()) {
                return new OutputCap(tile, getTier(tile.energyTier));
            } else {
                return null;
            }

        });
        event.registerBlockEntity(IRCapabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORDC_HV.get(), (tile, context) -> {
            Direction facing = tile.getFacing();
            if(context == facing) {
                return new InputCap(tile, getTier(tile.energyTier));
            } else {
                return null;
            }

        });
        event.registerBlockEntity(IRCapabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORDC_MV.get(), (tile, context) -> {
            Direction facing = tile.getFacing();
            if(context == facing) {
                return new InputCap(tile, getTier(tile.energyTier));
            } else {
                return null;
            }

        });
        event.registerBlockEntity(IRCapabilities.EnergyStorage.BLOCK, DynamicElectricityTiles.TILE_MOTORDC_LV.get(), (tile, context) -> {
            Direction facing = tile.getFacing();
            if(context == facing) {
                return new InputCap(tile, getTier(tile.energyTier));
            } else {
                return null;
            }

        });
    }

    public static void handleEnergyOutput(TileMotorAC motor, BlockEntity tile) {

        if (tile == null) {
            return;
        }

        IEnergyStorage modCap = motor.getLevel().getCapability(IRCapabilities.EnergyStorage.BLOCK, tile.getBlockPos(), tile.getBlockState(), tile, motor.getFacing().getOpposite());

        if (modCap == null) {
            return;
        }

        int amtAccepted = modCap.tryFillEnergy(motor.feStored.getValue(), true);

        if (amtAccepted <= 0) {
            return;
        }
        modCap.tryFillEnergy(amtAccepted, false);
        motor.feStored.setValue(motor.feStored.getValue() - amtAccepted);

    }

    // Standard starts at 2 so
    private static Holder<EnergyTier> getTier(int tier) {
        return switch (tier) {
            case 2 -> EnergyTiers.HIGH;
            case 1 -> EnergyTiers.MEDIUM;
            default -> EnergyTiers.LOW;
        };
    }

    public static void addACConversionTooltip(TileMotorAC motor, List<FormattedCharSequence> list) {

        list.add(UtilsText.gui("motor.output", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(motor.feProduced.getValue() / ENERGY_PER_JOULE, DynamicDisplayUnits.INDUSTRIAL_ENERGY_UNIT), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
        list.add(UtilsText.gui("motor.tier", getTranslatedTier(getTier(motor.energyTier)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

    }

    public static void addDCConversionTooltip(TileMotorDC motor, List<FormattedCharSequence> list) {

        list.add(UtilsText.gui("motor.usage", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(motor.maxFeConsumed.getValue() / ENERGY_PER_JOULE, DynamicDisplayUnits.INDUSTRIAL_ENERGY_UNIT), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
        list.add(UtilsText.gui("motor.tier", getTranslatedTier(getTier(motor.energyTier)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

    }


    private static MutableComponent getTranslatedTier(Holder<EnergyTier> tier) {

        if (tier == EnergyTiers.MEDIUM) {
            return UtilsText.tooltip("indreforgedmedium");
        } else if (tier == EnergyTiers.HIGH) {
            return UtilsText.tooltip("indreforgedhigh");
        } else {
            return UtilsText.tooltip("indreforgedlow");
        }
    }


    public static Item getGuidebookLogo() {

        if (ModList.get().isLoaded(DynamicElectricity.INDUSTRIAL_REFORGED_ID)) {
            return IRBlocks.BASIC_GENERATOR.get().asItem();
        } else {
            return Items.AIR;
        }

    }
}
