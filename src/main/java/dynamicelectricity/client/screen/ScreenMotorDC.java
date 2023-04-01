package dynamicelectricity.client.screen;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import dynamicelectricity.References;
import dynamicelectricity.common.inventory.container.ContainerMotorDC;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import dynamicelectricity.compatability.industrialreborn.IndustrialRebornHandler;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.DisplayUnit;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.ScreenComponentFluid;
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

public class ScreenMotorDC extends GenericScreen<ContainerMotorDC> {

	public ScreenMotorDC(ContainerMotorDC screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);

		components.add(new ScreenComponentElectricInfo(this::getEnergyInformation, this, -AbstractScreenComponentInfo.SIZE + 1, 2));
		components.add(new ScreenComponentFluid(() -> {
			TileMotorDC motor = menu.getHostFromIntArray();
			if (motor != null) {
				return motor.<ComponentFluidHandlerSimple>getComponent(ComponentType.FluidHandler);
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
		}
	}

	private List<? extends Component> getMotorInfo() {
		ArrayList<Component> list = new ArrayList<>();

		TileMotorDC motor = menu.getHostFromIntArray();

		if (motor != null) {
			list.add(UtilsText.gui("motor.lubricant").withStyle(ChatFormatting.BLACK).append(Component.literal("" + motor.lubricantRemaining.get()).withStyle(ChatFormatting.DARK_GRAY)));
			list.add(UtilsText.gui("motor.generating").withStyle(motor.running.get() ? ChatFormatting.GREEN : ChatFormatting.RED));
		}

		return list;
	}

	private List<? extends FormattedCharSequence> getEnergyInformation() {
		ArrayList<FormattedCharSequence> list = new ArrayList<>();
		TileMotorDC box = menu.getHostFromIntArray();
		if (box != null) {
			ComponentElectrodynamic electro = box.getComponent(ComponentType.Electrodynamic);
			if (box.maxFeConsumed.get() <= 1000) {
				list.add(UtilsText.gui("motor.usage", Component.literal((box.maxFeConsumed.get() + 0.0) + " FE/t").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			} else {
				list.add(UtilsText.gui("motor.usage", Component.literal(box.maxFeConsumed.get() / 1000.0 + " kFE/t").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			}
			if (ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID) && Screen.hasShiftDown()) {
				IndustrialRebornHandler.addDCConversionTooltip(box, list);
			}
			list.add(UtilsText.gui("motor.voltage", Component.literal(ChatFormatter.getChatDisplayShort(electro.getVoltage(), DisplayUnit.VOLTAGE)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.output", Component.literal(ChatFormatter.getChatDisplayShort(box.joulesProduced.get(), DisplayUnit.JOULES) + "/t").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
			list.add(UtilsText.gui("motor.wattage", Component.literal(ChatFormatter.getChatDisplayShort(box.joulesProduced.get() * 20, DisplayUnit.WATT)).withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());

		}
		return list;
	}
}
