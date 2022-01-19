package dynamicelectricity.client.screen.components;

import java.awt.Rectangle;

import com.mojang.blaze3d.vertex.PoseStack;

import dynamicelectricity.References;
import electrodynamics.api.screen.IScreenWrapper;
import electrodynamics.prefab.screen.component.ScreenComponent;
import electrodynamics.prefab.utilities.RenderingUtils;
import net.minecraft.resources.ResourceLocation;

public class ScreenComponentMotor extends ScreenComponent{

	public static final int WIDTHARROW = 102;
    private static final int HEIGHTARROW = 70;
    private static final int POSXARROW = 0;
    private static final int POSYARROW = 0;
	
	public ScreenComponentMotor(IScreenWrapper gui, int x, int y) {
		super(new ResourceLocation(References.ID + ":textures/screen/component/motor.png"), gui, x, y);
	}

	@Override
	public Rectangle getBounds(final int guiWidth, final int guiHeight) {
		return new Rectangle(guiWidth + xLocation, guiHeight + yLocation, WIDTHARROW, HEIGHTARROW);
	}
	
	@Override
    public void renderBackground(PoseStack stack, final int xAxis, final int yAxis, final int guiWidth, final int guiHeight) {
		RenderingUtils.bindTexture(resource);
	
		gui.drawTexturedRect(stack, guiWidth + xLocation, guiHeight + yLocation, POSXARROW, POSYARROW, WIDTHARROW, HEIGHTARROW);
	
	}
}
