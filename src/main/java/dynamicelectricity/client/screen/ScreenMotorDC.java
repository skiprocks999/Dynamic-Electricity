package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.DisplayUnit;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.ScreenComponentFluid;
import electrodynamics.prefab.screen.component.ScreenComponentInfo;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentFluidHandlerMulti;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class ScreenMotorDC extends GenericScreen<ContainerMotorDC>{

	public ScreenMotorDC(ContainerMotorDC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
		
		components.add(new ScreenComponentElectricInfo(this::getEnergyInformation, this, -ScreenComponentInfo.SIZE + 1, 2));
		components.add(new ScreenComponentFluid(() -> {
			TileMotorDC motor = menu.getHostFromIntArray();
			if(motor != null) {
				ComponentFluidHandlerMulti handler = motor.getComponent(ComponentType.FluidHandler);
				if(handler.getInputTanks().length > 0) {
					return handler.getInputTanks()[0];
				}
			}
			return new FluidTank(1000);
		}, this, 150, 18));
	}
	
	@Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
		super.renderLabels(matrixStack, mouseX, mouseY);
		
		List<? extends Component> screenOverlays = getMotorInfo();
		
		if (screenOverlays.size() > 0) {
		    font.draw(matrixStack, screenOverlays.get(0), inventoryLabelX, 33f, 0);
		    font.draw(matrixStack, screenOverlays.get(1), inventoryLabelX, 43f, 0);
		} else {
		    onClose();
		}
	}
	
	private List<? extends Component> getMotorInfo() {
		ArrayList<Component> list = new ArrayList<>();
	
		TileMotorDC motor = menu.getHostFromIntArray();
		
		if(motor != null) {
			list.add(new TranslatableComponent("gui.motor.lubricant").withStyle(ChatFormatting.BLACK).append(new TextComponent("" + motor.CLIENT_LUBRICANT)).withStyle(ChatFormatting.DARK_GRAY));
			list.add(new TranslatableComponent("gui.motor.generating").withStyle(motor.CLIENT_ISPOWERED ? ChatFormatting.GREEN : ChatFormatting.RED));
		}
		
		return list;
	}
	
	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorDC box = menu.getHostFromIntArray();
		if (box != null) {
		    ComponentElectrodynamic electro = box.getComponent(ComponentType.Electrodynamic);
		    if(box.CLIENT_FEUSE <= 1000) {
		    	list.add(new TranslatableComponent("gui.motor.usage", 
				    	new TextComponent((box.CLIENT_FEUSE + 0.0) + " FE/t").withStyle(ChatFormatting.GRAY))
				    		.withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		    } else {
		    	list.add(new TranslatableComponent("gui.motor.usage", 
				    	new TextComponent(box.CLIENT_FEUSE / 1000.0 + " kFE/t").withStyle(ChatFormatting.GRAY))
				    		.withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		    }
		    list.add(new TranslatableComponent("gui.motor.voltage",
			    new TextComponent(ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnit.VOLTAGE))
				    .withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		    list.add(new TranslatableComponent("gui.motor.output",  
		    	new TextComponent(ChatFormatter.getChatDisplayShort(box.CLIENT_JOULESPRODUCED, DisplayUnit.JOULES) + "/t")
				    .withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		    list.add(new TranslatableComponent("gui.motor.wattage",
				    new TextComponent(ChatFormatter.getChatDisplayShort(box.CLIENT_JOULESPRODUCED * 20 , DisplayUnit.WATT))
					    .withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
		}
		return list;
	}
}
