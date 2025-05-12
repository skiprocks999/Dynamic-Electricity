package dynamicelectricity.client;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.client.guidebook.ModuleDynamicElectricity;
import dynamicelectricity.client.render.tile.RenderMotorAC;
import dynamicelectricity.client.render.tile.RenderMotorDC;
import dynamicelectricity.client.screen.ScreenMotorAC;
import dynamicelectricity.client.screen.ScreenMotorDC;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityTiles;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import voltaic.api.screen.ITexture;
import voltaic.client.guidebook.ScreenGuidebook;


@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = DynamicElectricity.ID, bus = Bus.MOD, value = { Dist.CLIENT })
public class DynamicElectricityClientRegister {
	
	public static final ResourceLocation MODEL_MOTORAC_HVSHAFT = DynamicElectricity.rl("block/motorachvshaft");
	public static final ResourceLocation MODEL_MOTORAC_MVSHAFT = DynamicElectricity.rl("block/motoracmvshaft");
	public static final ResourceLocation MODEL_MOTORAC_LVSHAFT = DynamicElectricity.rl("block/motoraclvshaft");
	
	public static void setup() {
		ScreenManager.register(DynamicElectricityContainers.CONTAINER_MOTORAC.get(), ScreenMotorAC::new);
		ScreenManager.register(DynamicElectricityContainers.CONTAINER_MOTORDC.get(), ScreenMotorDC::new);
		
		ClientRegistry.bindTileEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_HV.get(), RenderMotorAC::new);
		ClientRegistry.bindTileEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_MV.get(), RenderMotorAC::new);
		ClientRegistry.bindTileEntityRenderer(DynamicElectricityTiles.TILE_MOTORAC_LV.get(), RenderMotorAC::new);
		
		ClientRegistry.bindTileEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_HV.get(), RenderMotorDC::new);
		ClientRegistry.bindTileEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_MV.get(), RenderMotorDC::new);
		ClientRegistry.bindTileEntityRenderer(DynamicElectricityTiles.TILE_MOTORDC_LV.get(), RenderMotorDC::new);
		
		ScreenGuidebook.addGuidebookModule(new ModuleDynamicElectricity());
	}
	
	@SubscribeEvent
    public static void onModelEvent(ModelRegistryEvent event) {
		ModelLoader.addSpecialModel(MODEL_MOTORAC_HVSHAFT);
		ModelLoader.addSpecialModel(MODEL_MOTORAC_MVSHAFT);
		ModelLoader.addSpecialModel(MODEL_MOTORAC_LVSHAFT);
	}
	
    public static boolean shouldMultilayerRender(RenderType type) {
    	return type == RenderType.translucent() || type == RenderType.solid();
    }
    
    public static enum DynamicElectricityTextures implements ITexture {
    	
    	MOTOR(102, 70, 0, 0, 256, 256, new ResourceLocation(DynamicElectricity.ID + ":textures/screen/component/motor.png"));
    	
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
