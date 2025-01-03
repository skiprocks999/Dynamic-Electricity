package dynamicelectricity.client;

import dynamicelectricity.References;
import dynamicelectricity.client.guidebook.ModuleDynamicElectricity;
import dynamicelectricity.client.render.tile.RenderMotorAC;
import dynamicelectricity.client.render.tile.RenderMotorDC;
import dynamicelectricity.client.screen.ScreenMotorAC;
import dynamicelectricity.client.screen.ScreenMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityTiles;
import electrodynamics.api.screen.ITexture;
import electrodynamics.client.guidebook.ScreenGuidebook;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT })
public class ClientRegister {

	public static final String BLOCK_LOC = References.ID + ":block/";

	public static final ModelResourceLocation MODEL_MOTORAC_HV = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motorachv"));
	public static final ModelResourceLocation MODEL_MOTORAC_HVSHAFT = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motorachvshaft"));
	public static final ModelResourceLocation MODEL_MOTORAC_MV = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motoracmv"));
	public static final ModelResourceLocation MODEL_MOTORAC_MVSHAFT = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motoracmvshaft"));
	public static final ModelResourceLocation MODEL_MOTORAC_LV = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motoraclv"));
	public static final ModelResourceLocation MODEL_MOTORAC_LVSHAFT = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motoraclvshaft"));

	public static final ModelResourceLocation MODEL_MOTORDC_HV = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motordchv"));
	public static final ModelResourceLocation MODEL_MOTORDC_MV = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motordcmv"));
	public static final ModelResourceLocation MODEL_MOTORDC_LV = ModelResourceLocation.standalone(ResourceLocation.parse(BLOCK_LOC + "motordclv"));

	public static void setup() {
		ScreenGuidebook.addGuidebookModule(new ModuleDynamicElectricity());
	}

	@SubscribeEvent
	public static void onModelEvent(ModelEvent.RegisterAdditional event) {
		event.register(MODEL_MOTORAC_HVSHAFT);
		event.register(MODEL_MOTORAC_MVSHAFT);
		event.register(MODEL_MOTORAC_LVSHAFT);
	}
	
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(DynamicElectricityContainers.CONTAINER_MOTORAC.get(), ScreenMotorAC::new);
		event.register(DynamicElectricityContainers.CONTAINER_MOTORDC.get(), ScreenMotorDC::new);
	}

	@SubscribeEvent
	public static void registerEntities(EntityRenderersEvent.RegisterRenderers event) {
		// The render classes for the AC and DC are fairly similar right now, but I have them seperate on
		// purpose, as I might change up the models in the future
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), RenderMotorAC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_MV.get(), RenderMotorAC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_LV.get(), RenderMotorAC::new);

		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_HV.get(), RenderMotorDC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_MV.get(), RenderMotorDC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_LV.get(), RenderMotorDC::new);
	}

	public static enum DynamicElectricityTextures implements ITexture {

		MOTOR(102, 70, 0, 0, 256, 256, ModelResourceLocation.standalone(ResourceLocation.parse(References.ID + ":textures/screen/component/motor.png"));

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
