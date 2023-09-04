package dynamicelectricity.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;

import dynamicelectricity.client.ClientRegister;
import dynamicelectricity.common.tile.TileMotorDcHv;
import dynamicelectricity.common.tile.TileMotorDcLv;
import dynamicelectricity.common.tile.TileMotorDcMv;
import dynamicelectricity.common.tile.generic.TileMotorDC;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.utilities.RenderingUtils;
import electrodynamics.prefab.utilities.math.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;

public class RenderMotorDC implements BlockEntityRenderer<TileMotorDC> {

	public RenderMotorDC(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(TileMotorDC tile, float partialTicks, PoseStack matrix, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrix.pushPose();

		Direction facing = tile.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();

		double progress = Math.sin(0.05 * Math.PI * partialTicks);

		float progressDegrees = 0.0F;

		if (tile.running.get()) {
			progressDegrees = 360.0f * (float) progress;
		}

		BakedModel shaft = null;

		if (tile instanceof TileMotorDcLv) {

			shaft = Minecraft.getInstance().getModelManager().getModel(ClientRegister.MODEL_MOTORAC_LVSHAFT);

			switch (facing) {
			case EAST:
				matrix.translate(0.938, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XN));
				// matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case WEST:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XP));
				// matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case SOUTH:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(90F, MathUtils.YP));
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XP));
				// matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				// matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case NORTH:
				matrix.translate(0.5, 0.5, 0.062);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(90F, MathUtils.YP));
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XN));
				// matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				// matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			default:
			}
		} else if (tile instanceof TileMotorDcMv) {

			shaft = Minecraft.getInstance().getModelManager().getModel(ClientRegister.MODEL_MOTORAC_MVSHAFT);

			switch (facing) {
			case EAST:
				matrix.translate(0.938, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XN));
				// matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case WEST:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XP));
				// matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case SOUTH:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(90F, MathUtils.YP));
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XP));
				// matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				// matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case NORTH:
				matrix.translate(0.5, 0.5, 0.062);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(90F, MathUtils.YP));
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XN));
				// matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				// matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			default:
			}

		} else if (tile instanceof TileMotorDcHv) {

			shaft = Minecraft.getInstance().getModelManager().getModel(ClientRegister.MODEL_MOTORAC_HVSHAFT);

			switch (facing) {
			case EAST:
				matrix.translate(0.938, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XN));
				// matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case WEST:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XP));
				// matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case SOUTH:
				matrix.translate(0.5, 0.5, 0.5);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(90F, MathUtils.YP));
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XP));
				// matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				// matrix.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			case NORTH:
				matrix.translate(0.5, 0.5, 0.062);
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(90F, MathUtils.YP));
				matrix.mulPose(MathUtils.rotVectorQuaternionDeg(progressDegrees, MathUtils.XN));
				// matrix.mulPose(new Quaternion(new Vector3f(0, 1F, 0), 90, true));
				// matrix.mulPose(new Quaternion(new Vector3f(-1.0F, 0.0F, 0.0F), progressDegrees, true));
				break;
			default:
			}
		}

		RenderingUtils.renderModel(shaft, tile, RenderType.solid(), matrix, bufferIn, combinedLightIn, combinedOverlayIn);

		matrix.popPose();
	}

}
