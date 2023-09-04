package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.compatability.industrialreborn.IndustrialRebornHandler;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.DisplayUnit;
import electrodynamics.prefab.screen.component.types.ScreenComponentGeneric;
import electrodynamics.prefab.screen.component.types.ScreenComponentMultiLabel;
import electrodynamics.prefab.screen.component.types.ScreenComponentProgress.ProgressTextures;
import electrodynamics.prefab.screen.component.types.gauges.ScreenComponentFluidGauge;
import electrodynamics.prefab.screen.component.types.guitab.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.utils.AbstractScreenComponentInfo;
import electrodynamics.prefab.screen.types.GenericMaterialScreen;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentFluidHandlerSimple;
import electrodynamics.prefab.utilities.ElectroTextUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.ModList;

public class ScreenMotorAC extends GenericMaterialScreen<ContainerMotorAC> {

	public ScreenMotorAC(ContainerMotorAC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
		addComponent(new ScreenComponentElectricInfo(this::getEnergyInformation, -AbstractScreenComponentInfo.SIZE + 1, 2));
		addComponent(new ScreenComponentGeneric(ProgressTextures.ARROW_RIGHT_OFF, 123, 33));
		addComponent(new ScreenComponentFluidGauge(() -> {
			TileMotorAC motor = menu.getHostFromIntArray();
			if (motor != null) {
				return motor.<ComponentFluidHandlerSimple>getComponent(ComponentType.FluidHandler);
			}
			return new FluidTank(1000);
		}, 150, 18));

		addComponent(new ScreenComponentMultiLabel(0, 0, graphics -> {
			TileMotorAC motor = menu.getHostFromIntArray();
			if (motor == null) {
				return;
			}
			graphics.drawString(font, UtilsText.gui("motor.lubricant").withStyle(ChatFormatting.BLACK).append(Component.literal("" + motor.lubricantRemaining.get()).withStyle(ChatFormatting.DARK_GRAY)), inventoryLabelX, 33, 0, false);
			graphics.drawString(font, UtilsText.gui("motor.generating").withStyle(motor.running.get() ? ChatFormatting.GREEN : ChatFormatting.RED), inventoryLabelX, 43, 0, false);
		}));
	}

	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorAC box = menu.getHostFromIntArray();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(ComponentType.Electrodynamic);

			list.add(UtilsText.gui("motor.usage", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored() / 20.0, DisplayUnit.JOULES), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored(), DisplayUnit.WATT).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.voltage", ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnit.VOLTAGE).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

			list.add(UtilsText.gui("motor.output", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.feProduced.get(), DisplayUnit.FORGE_ENERGY_UNIT), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

			if (ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID) && Screen.hasShiftDown()) {

				IndustrialRebornHandler.addACConversionTooltip(box, list);

			}

		}

		return list;
	}

}
