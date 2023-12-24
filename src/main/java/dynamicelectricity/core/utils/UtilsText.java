package dynamicelectricity.core.utils;

import dynamicelectricity.References;
import electrodynamics.prefab.utilities.ElectroTextUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class UtilsText {

	public static IFormattableTextComponent tooltip(String key, Object... additional) {
		return translated(ElectroTextUtils.TOOLTIP_BASE, key, additional);
	}

	public static IFormattableTextComponent guidebook(String key, Object... additional) {
		return translated(ElectroTextUtils.GUIDEBOOK_BASE, key, additional);
	}

	public static IFormattableTextComponent gui(String key, Object... additional) {
		return translated(ElectroTextUtils.GUI_BASE, key, additional);
	}

	public static IFormattableTextComponent chatMessage(String key, Object... additional) {
		return translated(ElectroTextUtils.MESSAGE_BASE, key, additional);
	}

	public static IFormattableTextComponent jeiTranslated(String key, Object... additional) {
		return new TranslationTextComponent(ElectroTextUtils.JEI_BASE + "." + key, additional);
	}

	public static IFormattableTextComponent jeiItemTranslated(String key, Object... additional) {
		return jeiTranslated(ElectroTextUtils.JEI_INFO_ITEM + "." + key, additional);
	}

	public static IFormattableTextComponent jeiFluidTranslated(String key, Object... additional) {
		return jeiTranslated(ElectroTextUtils.JEI_INFO_FLUID + "." + key, additional);
	}

	public static IFormattableTextComponent block(String key, Object... additional) {
		return translated(ElectroTextUtils.BLOCK_BASE, key, additional);
	}

	public static IFormattableTextComponent creativeTab(String key, Object... additional) {
		return translated(ElectroTextUtils.CREATIVE_TAB, key, additional);
	}

	public static IFormattableTextComponent translated(String base, String key, Object... additional) {
		return new TranslationTextComponent(base + "." + References.ID + "." + key, additional);
	}

	public static boolean guiExists(String key) {
		return translationExists(ElectroTextUtils.GUI_BASE, key);
	}

	public static boolean tooltipExists(String key) {
		return translationExists(ElectroTextUtils.TOOLTIP_BASE, key);
	}

	public static boolean translationExists(String base, String key) {
		return I18n.exists(base + "." + References.ID + "." + key);
	}

}