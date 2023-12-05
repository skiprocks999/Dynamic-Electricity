package dynamicelectricity.compatability.industrialreborn;

import java.util.List;

import com.maciej916.indreb.common.energy.interfaces.IEnergy;
import com.maciej916.indreb.common.enums.EnergyTier;
import com.maciej916.indreb.common.registries.ModCapabilities;

import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.core.utils.DynamicDisplayUnits;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.DisplayUnit;
import electrodynamics.prefab.utilities.ElectroTextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

//Buffer class to prevent stuff loading that shouldn't
public class IndustrialRebornHandler {

	public static final int ENERGY_PER_JOULE = 4;

	public static boolean isCapability(Capability<?> cap) {
		return cap == ModCapabilities.ENERGY;
	}

	// Extract energy only
	public static LazyOptional<IEnergy> getACMotorCap(TileMotorAC motor, int tier) {
		return LazyOptional.of(() -> new OutputCap(motor, getTier(tier)));
	}

	// Receive energy only
	public static LazyOptional<IEnergy> getDCMotorCap(TileMotorDC motor, int tier) {
		return LazyOptional.of(() -> new InputCap(motor, getTier(tier)));
	}

	public static void handleEnergyOutput(TileMotorAC motor, BlockEntity tile, Direction motorFacing) {

		if (tile == null) {
			return;
		}

		LazyOptional<IEnergy> lazy = tile.getCapability(ModCapabilities.ENERGY);

		if (!lazy.isPresent()) {
			return;
		}

		int amtAccepted = tile.getCapability(ModCapabilities.ENERGY, motorFacing.getOpposite()).map(m -> {
			return m.receiveEnergy(motor.feStored.get(), true);
		}).orElse(0);

		if (amtAccepted > 0) {
			tile.getCapability(ModCapabilities.ENERGY, motorFacing.getOpposite()).ifPresent(h -> {
				h.receiveEnergy(amtAccepted, false);
			});
			motor.feStored.set(motor.feStored.get() - amtAccepted);
		}

	}

	// Standard starts at 2 so
	private static EnergyTier getTier(int tier) {
		return switch (tier) {
		case 2 -> EnergyTier.SUPER;
		case 1 -> EnergyTier.ADVANCED;
		default -> EnergyTier.STANDARD;
		};
	}

	public static void addACConversionTooltip(TileMotorAC motor, List<FormattedCharSequence> list) {

		list.add(UtilsText.gui("motor.output", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(motor.feProduced.get() / ENERGY_PER_JOULE, DynamicDisplayUnits.INDUSTRIAL_ENERGY_UNIT), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		list.add(UtilsText.gui("motor.tier", getTranslatedTier(getTier(motor.energyTier)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

	}

	public static void addDCConversionTooltip(TileMotorDC motor, List<FormattedCharSequence> list) {

		list.add(UtilsText.gui("motor.usage", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(motor.maxFeConsumed.get() / ENERGY_PER_JOULE, DynamicDisplayUnits.INDUSTRIAL_ENERGY_UNIT), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		list.add(UtilsText.gui("motor.tier", getTranslatedTier(getTier(motor.energyTier)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

	}
	
	private static MutableComponent getTranslatedTier(EnergyTier tier) {
		return switch(tier) {
		case SUPER -> UtilsText.tooltip("indrebsuper");
		case ADVANCED -> UtilsText.tooltip("indrebadvanced");
		default -> UtilsText.tooltip("indrebstandard");
		};
	}

}
