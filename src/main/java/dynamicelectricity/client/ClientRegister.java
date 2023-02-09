package dynamicelectricity.client;

import dynamicelectricity.References;
import dynamicelectricity.client.render.tile.RenderMotorAC;
import dynamicelectricity.client.render.tile.RenderMotorDC;
import dynamicelectricity.client.screen.ScreenMotorAC;
import dynamicelectricity.client.screen.ScreenMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityTiles;
import electrodynamics.api.screen.ITexture;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent.RegisterAdditional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;


@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = References.ID, bus = Bus.MOD, value = { Dist.CLIENT })
public class ClientRegister {
	
	public static final String BLOCK_LOC = References.ID + ":block/";
	
	@SubscribeEvent
    public static void onModelEvent(RegisterAdditional event) {
		event.register(MODEL_MOTORAC_HVSHAFT);
		event.register(MODEL_MOTORAC_MVSHAFT);
		event.register(MODEL_MOTORAC_LVSHAFT);
	}
	
	public static final ResourceLocation MODEL_MOTORAC_HV = new ResourceLocation(BLOCK_LOC + "motorachv");
	public static final ResourceLocation MODEL_MOTORAC_HVSHAFT = new ResourceLocation(BLOCK_LOC + "motorachvshaft");
	public static final ResourceLocation MODEL_MOTORAC_MV = new ResourceLocation(BLOCK_LOC + "motoracmv");
	public static final ResourceLocation MODEL_MOTORAC_MVSHAFT = new ResourceLocation(BLOCK_LOC + "motoracmvshaft");
	public static final ResourceLocation MODEL_MOTORAC_LV = new ResourceLocation(BLOCK_LOC + "motoraclv");
	public static final ResourceLocation MODEL_MOTORAC_LVSHAFT = new ResourceLocation(BLOCK_LOC + "motoraclvshaft");
	
	public static final ResourceLocation MODEL_MOTORDC_HV = new ResourceLocation(BLOCK_LOC + "motordchv");
	public static final ResourceLocation MODEL_MOTORDC_MV = new ResourceLocation(BLOCK_LOC + "motordcmv");
	public static final ResourceLocation MODEL_MOTORDC_LV = new ResourceLocation(BLOCK_LOC + "motordclv");
	
	public static void setup() {
		MenuScreens.register(DynamicElectricityContainers.CONTAINER_MOTORAC.get(), ScreenMotorAC::new);
		MenuScreens.register(DynamicElectricityContainers.CONTAINER_MOTORDC.get(), ScreenMotorDC::new);
	}
	
	@SubscribeEvent
	public static void registerEntities(EntityRenderersEvent.RegisterRenderers event) {
		//The render classes for the AC and DC are fairly similar right now, but I have them seperate on
		//purpose, as I might change up the models in the future
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), RenderMotorAC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_MV.get(), RenderMotorAC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_LV.get(), RenderMotorAC::new);
		
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_HV.get(), RenderMotorDC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_MV.get(), RenderMotorDC::new);
		event.registerBlockEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_LV.get(), RenderMotorDC::new);
	}
	
    public static boolean shouldMultilayerRender(RenderType type) {
    	return type == RenderType.translucent() || type == RenderType.solid();
    }
    
    public static enum DynamicElectricityTextures implements ITexture {
    	
    	MOTOR(102, 70, 0, 0, 256, 256, new ResourceLocation(References.ID + ":textures/screen/component/motor.png"));
    	
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
