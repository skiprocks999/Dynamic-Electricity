package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.compatability.industrialreborn.IndustrialRebornHandler;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.ModList;
import voltaic.api.electricity.formatting.ChatFormatter;
import voltaic.api.electricity.formatting.DisplayUnits;
import voltaic.prefab.screen.component.ScreenComponentGeneric;
import voltaic.prefab.screen.component.types.ScreenComponentMultiLabel;
import voltaic.prefab.screen.component.types.ScreenComponentProgress.ProgressTextures;
import voltaic.prefab.screen.component.types.gauges.ScreenComponentFluidGauge;
import voltaic.prefab.screen.component.types.guitab.ScreenComponentElectricInfo;
import voltaic.prefab.screen.component.utils.AbstractScreenComponentInfo;
import voltaic.prefab.screen.types.GenericMaterialScreen;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.ComponentElectrodynamic;
import voltaic.prefab.tile.components.type.ComponentFluidHandlerSimple;
import voltaic.prefab.utilities.VoltaicTextUtils;

public class ScreenMotorAC extends GenericMaterialScreen<ContainerMotorAC> {

	public ScreenMotorAC(ContainerMotorAC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
		addComponent(new ScreenComponentElectricInfo(this::getEnergyInformation, -AbstractScreenComponentInfo.SIZE + 1, 2));
		addComponent(new ScreenComponentGeneric(ProgressTextures.ARROW_RIGHT_OFF, 123, 33));
		addComponent(new ScreenComponentFluidGauge(() -> {
			TileMotorAC motor = menu.getSafeHost();
			if (motor != null) {
				return motor.<ComponentFluidHandlerSimple>getComponent(IComponentType.FluidHandler);
			}
			return new FluidTank(1000);
		}, 150, 18));

		addComponent(new ScreenComponentMultiLabel(0, 0, stack -> {
			TileMotorAC motor = menu.getSafeHost();
			if (motor == null) {
				return;
			}
			font.draw(stack, UtilsText.gui("motor.lubricant").withStyle(ChatFormatting.BLACK).append(Component.literal("" + motor.lubricantRemaining.getValue()).withStyle(ChatFormatting.DARK_GRAY)), inventoryLabelX, 33, 0);
			font.draw(stack, UtilsText.gui("motor.generating").withStyle(motor.running.getValue() ? ChatFormatting.GREEN : ChatFormatting.RED), inventoryLabelX, 43, 0);
		}));
	}

	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorAC box = menu.getSafeHost();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(IComponentType.Electrodynamic);

			list.add(UtilsText.gui("motor.usage", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored() / 20.0, DisplayUnits.JOULES), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored(), DisplayUnits.WATT).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.voltage", ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnits.VOLTAGE).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

			list.add(UtilsText.gui("motor.output", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.feProduced.getValue(), DisplayUnits.FORGE_ENERGY_UNIT), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

			if (ModList.get().isLoaded(DynamicElectricity.INDUSTRIAL_REBORN_ID) && Screen.hasShiftDown()) {

				IndustrialRebornHandler.addACConversionTooltip(box, list);

			}

		}

		return list;
	}

}