package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.client.ClientRegister.DynamicElectricityTextures;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.DisplayUnit;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.types.ScreenComponentGeneric;
import electrodynamics.prefab.screen.component.types.ScreenComponentMultiLabel;
import electrodynamics.prefab.screen.component.types.guitab.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.types.wrapper.InventoryIOWrapper;
import electrodynamics.prefab.screen.component.utils.AbstractScreenComponentInfo;
import electrodynamics.prefab.tile.components.IComponentType;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.utilities.ElectroTextUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ScreenMotorAC extends GenericScreen<ContainerMotorAC> {

	public ScreenMotorAC(ContainerMotorAC screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);

		addComponent(new ScreenComponentElectricInfo(this::getEnergyInformation, -AbstractScreenComponentInfo.SIZE + 1, 2));

		addComponent(new ScreenComponentGeneric(DynamicElectricityTextures.MOTOR, 69, 4));

		addComponent(new ScreenComponentMultiLabel(0, 0, stack -> {
			TileMotorAC motor = menu.getHostFromIntArray();
			if (motor == null) {
				return;
			}
			font.draw(stack, UtilsText.gui("motor.lubricant").withStyle(TextFormatting.BLACK), inventoryLabelX, 28, 0);
			font.draw(stack, new StringTextComponent("" + motor.lubricantRemaining.get()).withStyle(TextFormatting.DARK_GRAY), inventoryLabelX + 5, 38, 0);
			font.draw(stack, UtilsText.gui("motor.generating").withStyle(motor.running.get() ? TextFormatting.GREEN : TextFormatting.RED), inventoryLabelX, 48, 0);
		}));
		
		new InventoryIOWrapper(this, -AbstractScreenComponentInfo.SIZE + 1, AbstractScreenComponentInfo.SIZE + 2, 75, 82, 8, 72);
	}

	private List<? extends IReorderingProcessor> getEnergyInformation() {
		ArrayList<IReorderingProcessor> list = new ArrayList<>();
		TileMotorAC box = menu.getHostFromIntArray();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(IComponentType.Electrodynamic);

			list.add(UtilsText.gui("motor.usage", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored() / 20.0, DisplayUnit.JOULES), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored(), DisplayUnit.WATT).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.voltage", ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnit.VOLTAGE).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());

			list.add(UtilsText.gui("motor.output", ElectroTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.feProduced.get(), DisplayUnit.FORGE_ENERGY_UNIT), DisplayUnit.TIME_TICKS.getSymbol()).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());

		}

		return list;
	}

}
