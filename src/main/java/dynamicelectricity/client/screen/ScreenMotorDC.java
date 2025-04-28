package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import voltaic.api.electricity.formatting.ChatFormatter;
import voltaic.api.electricity.formatting.DisplayUnits;
import voltaic.prefab.screen.GenericScreen;
import voltaic.prefab.screen.component.types.ScreenComponentMultiLabel;
import voltaic.prefab.screen.component.types.gauges.ScreenComponentFluidGauge;
import voltaic.prefab.screen.component.types.guitab.ScreenComponentElectricInfo;
import voltaic.prefab.screen.component.types.wrapper.WrapperInventoryIO;
import voltaic.prefab.screen.component.utils.AbstractScreenComponentInfo;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.ComponentElectrodynamic;
import voltaic.prefab.tile.components.type.ComponentFluidHandlerSimple;
import voltaic.prefab.utilities.VoltaicTextUtils;

public class ScreenMotorDC extends GenericScreen<ContainerMotorDC> {

	public ScreenMotorDC(ContainerMotorDC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);

		addComponent(new ScreenComponentElectricInfo(this::getEnergyInformation, -AbstractScreenComponentInfo.SIZE + 1, 2));
		addComponent(new ScreenComponentFluidGauge(() -> {
			TileMotorDC motor = menu.getSafeHost();
			if (motor != null) {
				return motor.<ComponentFluidHandlerSimple>getComponent(IComponentType.FluidHandler);
			}
			return new FluidTank(1000);
		}, 150, 18));
		addComponent(new ScreenComponentMultiLabel(0, 0, graphics -> {
			TileMotorDC motor = menu.getSafeHost();

			if (motor == null) {
				return;
			}

			graphics.drawString(font, UtilsText.gui("motor.lubricant").withStyle(ChatFormatting.BLACK).append(Component.literal("" + motor.lubricantRemaining.getValue()).withStyle(ChatFormatting.DARK_GRAY)), inventoryLabelX, 33, 0, false);
			graphics.drawString(font, UtilsText.gui("motor.generating").withStyle(motor.running.getValue() ? ChatFormatting.GREEN : ChatFormatting.RED), inventoryLabelX, 43, 0, false);
		}));
		new WrapperInventoryIO(this, -AbstractScreenComponentInfo.SIZE + 1, AbstractScreenComponentInfo.SIZE + 2, 75, 82, 8, 72);

	}

	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorDC box = menu.getSafeHost();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(IComponentType.Electrodynamic);

			list.add(UtilsText.gui("motor.usage", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.maxFeConsumed.getValue(), DisplayUnits.FORGE_ENERGY_UNIT), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			
			list.add(UtilsText.gui("motor.voltage", ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnits.VOLTAGE).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.output", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.joulesProduced.getValue(), DisplayUnits.JOULES), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", ChatFormatter.getChatDisplayShort(box.joulesProduced.getValue() * 20, DisplayUnits.WATT).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

		}
		return list;
	}
}
