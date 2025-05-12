package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import dynamicelectricity.client.DynamicElectricityClientRegister.DynamicElectricityTextures;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import voltaic.api.electricity.formatting.ChatFormatter;
import voltaic.api.electricity.formatting.DisplayUnits;
import voltaic.prefab.screen.GenericScreen;
import voltaic.prefab.screen.component.ScreenComponentGeneric;
import voltaic.prefab.screen.component.types.ScreenComponentMultiLabel;
import voltaic.prefab.screen.component.types.guitab.ScreenComponentElectricInfo;
import voltaic.prefab.screen.component.types.wrapper.WrapperInventoryIO;
import voltaic.prefab.screen.component.utils.AbstractScreenComponentInfo;
import voltaic.prefab.tile.components.IComponentType;
import voltaic.prefab.tile.components.type.ComponentElectrodynamic;
import voltaic.prefab.utilities.VoltaicTextUtils;

public class ScreenMotorDC extends GenericScreen<ContainerMotorDC>{

	public ScreenMotorDC(ContainerMotorDC screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		
		addComponent(new ScreenComponentElectricInfo(this::getEnergyInformation, -AbstractScreenComponentInfo.SIZE + 1, 2));
		
		addComponent(new ScreenComponentGeneric(DynamicElectricityTextures.MOTOR, 69, 4));
		
		addComponent(new ScreenComponentMultiLabel(0, 0, stack -> {
			TileMotorDC motor = menu.getSafeHost();

			if (motor == null) {
				return;
			}

			font.draw(stack, UtilsText.gui("motor.lubricant").withStyle(TextFormatting.BLACK), inventoryLabelX, 28, 0);
			font.draw(stack, new StringTextComponent("" + motor.lubricantRemaining.getValue()).withStyle(TextFormatting.DARK_GRAY), inventoryLabelX + 5, 38, 0);
			font.draw(stack, UtilsText.gui("motor.generating").withStyle(motor.running.getValue() ? TextFormatting.GREEN : TextFormatting.RED), inventoryLabelX, 48, 0);
		}));
		new WrapperInventoryIO(this, -AbstractScreenComponentInfo.SIZE + 1, AbstractScreenComponentInfo.SIZE + 2, 75, 82, 8, 72);
	
	}
	
	private List<? extends IReorderingProcessor> getEnergyInformation() {
		ArrayList<IReorderingProcessor> list = new ArrayList<>();
		TileMotorDC box = menu.getSafeHost();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(IComponentType.Electrodynamic);

			list.add(UtilsText.gui("motor.usage", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.maxFeConsumed.getValue(), DisplayUnits.FORGE_ENERGY_UNIT), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());

			list.add(UtilsText.gui("motor.voltage", ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnits.VOLTAGE).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.output", VoltaicTextUtils.ratio(ChatFormatter.getChatDisplayShort(box.joulesProduced.getValue(), DisplayUnits.JOULES), DisplayUnits.TIME_TICKS.getSymbol()).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", ChatFormatter.getChatDisplayShort(box.joulesProduced.getValue() * 20, DisplayUnits.WATT).withStyle(TextFormatting.GRAY)).withStyle(TextFormatting.DARK_GRAY).getVisualOrderText());

		}
		return list;
	}
}
