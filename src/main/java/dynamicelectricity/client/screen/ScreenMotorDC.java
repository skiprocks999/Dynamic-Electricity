package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.compatability.industrialreborn.IndustrialRebornHandler;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.DisplayUnit;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.types.ScreenComponentMultiLabel;
import electrodynamics.prefab.screen.component.types.gauges.ScreenComponentFluidGauge;
import electrodynamics.prefab.screen.component.types.guitab.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.types.wrapper.InventoryIOWrapper;
import electrodynamics.prefab.screen.component.utils.AbstractScreenComponentInfo;
import electrodynamics.prefab.tile.components.IComponentType;
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

public class ScreenMotorDC extends GenericScreen<ContainerMotorDC> {

	public ScreenMotorDC(ContainerMotorDC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);

		addComponent(new ScreenComponentElectricInfo(this::getEnergyInformation, -AbstractScreenComponentInfo.SIZE + 1, 2));
		addComponent(new ScreenComponentFluidGauge(() -> {
			TileMotorDC motor = menu.getHostFromIntArray();
			if (motor != null) {
				return motor.<ComponentFluidHandlerSimple>getComponent(IComponentType.FluidHandler);
			}
			return new FluidTank(1000);
		}, 150, 18));
		addComponent(new ScreenComponentMultiLabel(0, 0, stack -> {
			TileMotorDC motor = menu.getHostFromIntArray();

			if (motor == null) {
				return;
			}

			font.draw(stack, UtilsText.gui("motor.lubricant").withStyle(ChatFormatting.BLACK).append(Component.literal("" + motor.lubricantRemaining.get()).withStyle(ChatFormatting.DARK_GRAY)), inventoryLabelX, 33, 0);
			font.draw(stack, UtilsText.gui("motor.generating").withStyle(motor.running.get() ? ChatFormatting.GREEN : ChatFormatting.RED), inventoryLabelX, 43, 0);
		}));
		new InventoryIOWrapper(this, -AbstractScreenComponentInfo.SIZE + 1, AbstractScreenComponentInfo.SIZE + 2, 75, 82, 8, 72);

	}

	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorDC box = menu.getHostFromIntArray();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(IComponentType.Electrodynamic);

			list.add(UtilsText.gui("motor.usage", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.maxFeConsumed.get(), DisplayUnit.FORGE_ENERGY_UNIT), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

			if (ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID) && Screen.hasShiftDown()) {
				IndustrialRebornHandler.addDCConversionTooltip(box, list);
			}
			list.add(UtilsText.gui("motor.voltage", ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnit.VOLTAGE).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.output", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.joulesProduced.get(), DisplayUnit.JOULES), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", ChatFormatter.getChatDisplayShort(box.joulesProduced.get() * 20, DisplayUnit.WATT).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

		}
		return list;
	}
}
