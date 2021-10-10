package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import dynamicelectricity.client.screen.components.ScreenComponentMotor;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.ElectricUnit;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.ScreenComponentInfo;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class ScreenMotorDC extends GenericScreen<ContainerMotorDC>{

	public ScreenMotorDC(ContainerMotorDC screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		
		components.add(new ScreenComponentElectricInfo(this::getEnergyInformation, this, -ScreenComponentInfo.SIZE + 1, 2));
		
		components.add(new ScreenComponentMotor(this, 69, 4));
	
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
		
		List<? extends ITextComponent> screenOverlays = getMotorInfo();
		
		if (screenOverlays.size() > 0) {
		    font.func_243248_b(matrixStack, screenOverlays.get(0), playerInventoryTitleX, 28f, 0);
		    font.func_243248_b(matrixStack, screenOverlays.get(1), playerInventoryTitleX, 38f, 0);
		    font.func_243248_b(matrixStack, screenOverlays.get(2), playerInventoryTitleX, 48f, 0);
		} else {
		    closeScreen();
		}
	}
	
	private List<? extends ITextComponent> getMotorInfo() {
		ArrayList<ITextComponent> list = new ArrayList<>();
	
		TileMotorDC motor = container.getHostFromIntArray();
		
		int lubeCount = motor.CLIENT_LUBRICANT;
		
		list.add(new TranslationTextComponent("gui.motor.lubricant").mergeStyle(TextFormatting.BLACK));
		list.add(new StringTextComponent("" + lubeCount).mergeStyle(TextFormatting.DARK_GRAY));
		list.add(new TranslationTextComponent("gui.motor.generating").mergeStyle(motor.CLIENT_ISPOWERED ? TextFormatting.GREEN : TextFormatting.RED));
		
		return list;
	}
	
	private List<? extends ITextProperties> getEnergyInformation() {
		ArrayList<ITextProperties> list = new ArrayList<>();
		TileMotorDC box = container.getHostFromIntArray();
		if (box != null) {
		    ComponentElectrodynamic electro = box.getComponent(ComponentType.Electrodynamic);

		    list.add(new TranslationTextComponent("gui.motor.usage",
			    new StringTextComponent(ChatFormatter.getElectricDisplayShort(box.getMaxJoules() * 20, ElectricUnit.WATT))
				    .mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_GRAY));
		    list.add(new TranslationTextComponent("gui.motor.voltage",
			    new StringTextComponent(ChatFormatter.getElectricDisplayShort(electro.getVoltage(), ElectricUnit.VOLTAGE))
				    .mergeStyle(TextFormatting.GRAY)).mergeStyle(TextFormatting.DARK_GRAY));
		}
		return list;
	}
}
