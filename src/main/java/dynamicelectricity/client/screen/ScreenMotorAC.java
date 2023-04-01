package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorAC;
import dynamicelectricity.common.tile.generic.TileMotorAC;
import dynamicelectricity.compatability.industrialreborn.IndustrialRebornHandler;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.DisplayUnit;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.ScreenComponentFluid;
import electrodynamics.prefab.screen.component.ScreenComponentGeneric;
import electrodynamics.prefab.screen.component.ScreenComponentProgress.ProgressTextures;
import electrodynamics.prefab.screen.component.utils.AbstractScreenComponentInfo;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentFluidHandlerSimple;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.fml.ModList;

public class ScreenMotorAC extends GenericScreen<ContainerMotorAC> {

	public ScreenMotorAC(ContainerMotorAC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
		components.add(new ScreenComponentElectricInfo(this::getEnergyInformation, this, -AbstractScreenComponentInfo.SIZE + 1, 2));
		components.add(new ScreenComponentGeneric(ProgressTextures.ARROW_RIGHT_OFF, this, 123, 33));
		components.add(new ScreenComponentFluid(() -> {
			TileMotorAC motor = menu.getHostFromIntArray();
			if (motor != null) {
				return motor.<ComponentFluidHandlerSimple>getComponent(ComponentType.FluidHandler);
			}
			return new FluidTank(1000);
		}, this, 150, 18));
	}

	@Override
	protected void renderLabels(PoseStack PoseStack, int mouseX, int mouseY) {
		super.renderLabels(PoseStack, mouseX, mouseY);

		List<? extends Component> screenOverlays = getMotorInfo();

		if (screenOverlays.size() > 0) {
			font.draw(PoseStack, screenOverlays.get(0), inventoryLabelX, 33f, 0);
			font.draw(PoseStack, screenOverlays.get(1), inventoryLabelX, 43f, 0);
		}

	}

	private List<? extends Component> getMotorInfo() {
		ArrayList<Component> list = new ArrayList<>();

		TileMotorAC motor = menu.getHostFromIntArray();

		if (motor != null) {
			list.add(UtilsText.gui("motor.lubricant").withStyle(ChatFormatting.BLACK).append(Component.literal("" + motor.lubricantRemaining.get()).withStyle(ChatFormatting.DARK_GRAY)));
			list.add(UtilsText.gui("motor.generating").withStyle(motor.running.get() ? ChatFormatting.GREEN : ChatFormatting.RED));
		}

		return list;
	}

	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorAC box = menu.getHostFromIntArray();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(ComponentType.Electrodynamic);
			list.add(UtilsText.gui("motor.usage", Component.literal(ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored() / 20.0, DisplayUnit.JOULES) + "/t").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", Component.literal(ChatFormatter.getChatDisplayShort(electro.getMaxJoulesStored(), DisplayUnit.WATT)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.voltage", Component.literal(ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnit.VOLTAGE)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			if (box.feProduced.get() <= 1000) {
				list.add(UtilsText.gui("motor.output", Component.literal((box.feProduced.get() + 0.0) + " FE/t").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			} else {
				list.add(UtilsText.gui("motor.output", Component.literal(box.feProduced.get() / 1000.0 + " kFE/t").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			}
			
			if(ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID) && Screen.hasShiftDown()) {
				
				IndustrialRebornHandler.addACConversionTooltip(box, list);
				
			}
			
			

		}

		return list;
	}

}
