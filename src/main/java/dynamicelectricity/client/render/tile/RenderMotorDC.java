package dynamicelectricity.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;

import dynamicelectricity.client.ClientRegister;
import dynamicelectricity.common.tile.TileMotorDcHv;
import dynamicelectricity.common.tile.TileMotorDcLv;
import dynamicelectricity.common.tile.TileMotorDcMv;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import electrodynamics.client.render.tile.AbstractTileRenderer;
import electrodynamics.prefab.utilities.RenderingUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class RenderMotorDC extends AbstractTileRenderer<TileMotorDC> {

	public RenderMotorDC(TileEntityRendererDispatcher context) {
		super(context);
	}

	@Override
	public void render(TileMotorDC tile, float partialTicks, MatrixStack matrix, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrix.pushPose();

		Direction facing = tile.getFacing();

		double progress = Math.sin(0.05 * Math.PI * partialTicks);

		float progressDegrees = 0.0F;

		if (tile.running.get()) {
			progressDegrees = 360.0f * (float) progress;
		}

		IBakedModel shaft = null;

		if (tile instanceof TileMotorDcLv) {

			shaft = Minecraft.getInstance().getModelManager().getModel(ClientRegister.MODEL_MOTORAC_LVSHAFT);

			switch (facing) {
			case EAST:
				matrix.translate(0.938, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case WEST:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case SOUTH:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case NORTH:
				matrix.translate(0.5, 0.5, 0.062);
				matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			default:
			}
		} else if (tile instanceof TileMotorDcMv) {

			shaft = Minecraft.getInstance().getModelManager().getModel(ClientRegister.MODEL_MOTORAC_MVSHAFT);

			switch (facing) {
			case EAST:
				matrix.translate(0.938, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case WEST:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case SOUTH:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case NORTH:
				matrix.translate(0.5, 0.5, 0.062);
				matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			default:
			}

		} else if (tile instanceof TileMotorDcHv) {

			shaft = Minecraft.getInstance().getModelManager().getModel(ClientRegister.MODEL_MOTORAC_HVSHAFT);

			switch (facing) {
			case EAST:
				matrix.translate(0.938, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case WEST:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case SOUTH:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case NORTH:
				matrix.translate(0.5, 0.5, 0.062);
				matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			default:
			}
		}

		RenderingUtils.renderModel(shaft, tile, RenderType.solid(), matrix, bufferIn, combinedLightIn, combinedOverlayIn);

		matrix.popPose();
	}

}