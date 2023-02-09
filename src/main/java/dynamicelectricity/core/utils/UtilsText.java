package dynamicelectricity.core.utils;

import java.text.DecimalFormat;

import dynamicelectricity.References;
import electrodynamics.prefab.utilities.TextUtils;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class UtilsText {

	public static final DecimalFormat FORMATTER = new DecimalFormat("0.0##");

	public static MutableComponent tooltip(String key, Object... additional) {
		return translated(TextUtils.TOOLTIP_BASE, key, additional);
	}

	public static MutableComponent guidebook(String key, Object... additional) {
		return translated(TextUtils.GUIDEBOOK_BASE, key, additional);
	}

	public static MutableComponent gui(String key, Object... additional) {
		return translated(TextUtils.GUI_BASE, key, additional);
	}

	public static MutableComponent chatMessage(String key, Object... additional) {
		return translated(TextUtils.MESSAGE_BASE, key, additional);
	}

	public static MutableComponent jeiTranslated(String key, Object... additional) {
		return Component.translatable(TextUtils.JEI_BASE + "." + key, additional);
	}

	public static MutableComponent jeiItemTranslated(String key, Object... additional) {
		return jeiTranslated(TextUtils.JEI_INFO_ITEM + "." + key, additional);
	}

	public static MutableComponent jeiFluidTranslated(String key, Object... additional) {
		return jeiTranslated(TextUtils.JEI_INFO_FLUID + "." + key, additional);
	}

	public static MutableComponent block(String key, Object... additional) {
		return translated(TextUtils.BLOCK_BASE, key, additional);
	}

	public static MutableComponent translated(String base, String key, Object... additional) {
		return Component.translatable(base + "." + References.ID + "." + key, additional);
	}

	public static boolean guiExists(String key) {
		return translationExists(TextUtils.GUI_BASE, key);
	}

	public static boolean tooltipExists(String key) {
		return translationExists(TextUtils.TOOLTIP_BASE, key);
	}

	public static boolean translationExists(String base, String key) {
		return I18n.exists(base + "." + References.ID + "." + key);
	}

	public static String formatFluidValue(int fluidLevel) {
		double fluidDouble = fluidLevel;
		if(fluidLevel >= 1000) {
			return FORMATTER.format(fluidDouble / 1000.0) + " B";
		} else if (fluidLevel >= 1000000) {
			return FORMATTER.format(fluidDouble / 1000000) + " kB";
		} else if (fluidLevel >= 1000000000) {
			return FORMATTER.format(fluidDouble / 1000000000) + " MB";
		}
		
		return fluidLevel + " mB";
	}

	public static String formatTicksToTimeValue(int ticks) {
		double time = ticks / 20;
		if (time > 0.1) {
			return FORMATTER.format(time) + " s";
		}

		time = time * 1000;
		return FORMATTER.format(time) + " ms";
	}
	
}
