package dynamicelectricity.client;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.References;
import dynamicelectricity.client.guidebook.ModuleDynamicElectricity;
import dynamicelectricity.client.render.tile.RenderMotorAC;
import dynamicelectricity.client.render.tile.RenderMotorDC;
import dynamicelectricity.client.screen.ScreenMotorAC;
import dynamicelectricity.client.screen.ScreenMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityFluids;
import dynamicelectricity.registry.DynamicElectricityTiles;
import electrodynamics.api.screen.ITexture;
import electrodynamics.client.guidebook.ScreenGuidebook;
import electrodynamics.client.misc.SWBFClientExtensions;
import electrodynamics.common.fluid.SimpleWaterBasedFluidType;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT })
public class ClientRegister {

	public static final ModelResourceLocation MODEL_MOTORAC_HVSHAFT = ModelResourceLocation.standalone(DynamicElectricity.rl("block/motorachvshaft"));
	public static final ModelResourceLocation MODEL_MOTORAC_MVSHAFT = ModelResourceLocation.standalone(DynamicElectricity.rl("block/motoracmvshaft"));
	public static final ModelResourceLocation MODEL_MOTORAC_LVSHAFT = ModelResourceLocation.standalone(DynamicElectricity.rl("block/motoraclvshaft"));

	public static void setup() {
		ScreenGuidebook.addGuidebookModule(new ModuleDynamicElectricity());
	}

	@SubscribeEvent
	public static void onModelEvent(ModelEvent.RegisterAdditional event) {
		event.register(MODEL_MOTORAC_HVSHAFT);
		event.register(MODEL_MOTORAC_MVSHAFT);
		event.register(MODEL_MOTORAC_LVSHAFT);
	}

	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(DynamicElectricityContainers.CONTAINER_MOTORAC.get(), ScreenMotorAC::new);
		event.register(DynamicElectricityContainers.CONTAINER_MOTORDC.get(), ScreenMotorDC::new);
	}

	@SubscribeEvent
	public static void registerEntities(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), RenderMotorAC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_MV.get(), RenderMotorAC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_LV.get(), RenderMotorAC::new);

		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_HV.get(), RenderMotorDC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_MV.get(), RenderMotorDC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_LV.get(), RenderMotorDC::new);
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {

		DynamicElectricityFluids.FLUIDS.getEntries().forEach((fluid) -> {
			event.registerFluidType(new SWBFClientExtensions((SimpleWaterBasedFluidType) fluid.get().getFluidType()), fluid.get().getFluidType());
		});

	}

	public static enum DynamicElectricityTextures implements ITexture {

		MOTOR(102, 70, 0, 0, 256, 256, DynamicElectricity.rl("textures/screen/component/motor.png"));

		private final int textureWidth;
		private final int textureHeight;
		private final int textureU;
		private final int textureV;
		private final int imageWidth;
		private final int imageHeight;
		private final ResourceLocation loc;

		private DynamicElectricityTextures(int textureWidth, int textureHeight, int textureU, int textureV, int imageWidth, int imageHeight, ResourceLocation texture) {
			this.textureWidth = textureWidth;
			this.textureHeight = textureHeight;
			this.textureU = textureU;
			this.textureV = textureV;
			this.imageWidth = imageWidth;
			this.imageHeight = imageHeight;
			this.loc = texture;
		}

		@Override
		public ResourceLocation getLocation() {
			return loc;
		}

		@Override
		public int imageHeight() {
			return imageHeight;
		}

		@Override
		public int imageWidth() {
			return imageWidth;
		}

		@Override
		public int textureHeight() {
			return textureHeight;
		}

		@Override
		public int textureU() {
			return textureU;
		}

		@Override
		public int textureV() {
			return textureV;
		}

		@Override
		public int textureWidth() {
			return textureWidth;
		}

	}

}
