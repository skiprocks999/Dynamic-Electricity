package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import dynamicelectricity.client.screen.components.ScreenComponentMotor;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.ElectricUnit;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.ScreenComponentInfo;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;

public class ScreenMotorAC extends GenericScreen<ContainerMotorAC>{

	public ScreenMotorAC(ContainerMotorAC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
		components.add(new ScreenComponentElectricInfo(this::getEnergyInformation, this, -ScreenComponentInfo.SIZE + 1, 2));
		
		components.add(new ScreenComponentMotor(this, 69, 4));
	}
	
	@Override
    protected void renderLabels(PoseStack PoseStack, int mouseX, int mouseY) {
		super.renderLabels(PoseStack, mouseX, mouseY);
		
		List<? extends Component> screenOverlays = getMotorInfo();
		
		if (screenOverlays.size() > 0) {
		    font.draw(PoseStack, screenOverlays.get(0), inventoryLabelX, 28f, 0);
		    font.draw(PoseStack, screenOverlays.get(1), inventoryLabelX, 38f, 0);
		    font.draw(PoseStack, screenOverlays.get(2), inventoryLabelX, 48f, 0);
		} else {
		    onClose();
		}
	}
	
	private List<? extends Component> getMotorInfo() {
		ArrayList<Component> list = new ArrayList<>();
		
		TileMotorAC motor = menu.getHostFromIntArray();
		
		if(motor != null) {
			list.add(new TranslatableComponent("gui.motor.lubricant").withStyle(ChatFormatting.BLACK));
			list.add(new TextComponent("" + motor.CLIENT_LUBRICANT).withStyle(ChatFormatting.DARK_GRAY));
			list.add(new TranslatableComponent("gui.motor.generating").withStyle(motor.CLIENT_ISPOWERED ? ChatFormatting.GREEN : ChatFormatting.RED));
		}
		
		return list;
	}

	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorAC box = menu.getHostFromIntArray();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(ComponentType.Electrodynamic);
		    list.add(new TranslatableComponent("gui.motor.usage",
			    new TextComponent(ChatFormatter.getElectricDisplayShort(electro.getMaxJoulesStored() * 20, ElectricUnit.WATT))
				    .withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		    list.add(new TranslatableComponent("gui.motor.voltage",
			    new TextComponent(ChatFormatter.getElectricDisplayShort(electro.getVoltage(), ElectricUnit.VOLTAGE))
				    .withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		}
		
		return list;
	}

}
