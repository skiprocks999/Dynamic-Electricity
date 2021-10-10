package dynamicelectricity.client;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.References;
import dynamicelectricity.client.render.tile.RenderMotorAC;
import dynamicelectricity.client.render.tile.RenderMotorDC;
import dynamicelectricity.client.screen.ScreenMotorAC;
import dynamicelectricity.client.screen.ScreenMotorDC;
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

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = References.ID, bus = Bus.MOD, value = { Dist.CLIENT })
public class ClientRegister {
	
	public static final String BLOCK_LOC = References.ID + ":block/";
	
	@SubscribeEvent
    public static void onModelEvent(ModelRegistryEvent event) {
		ModelLoader.addSpecialModel(MODEL_MOTORAC_HVSHAFT);
		ModelLoader.addSpecialModel(MODEL_MOTORAC_MVSHAFT);
		ModelLoader.addSpecialModel(MODEL_MOTORAC_LVSHAFT);
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
		//The render classes for the AC and DC are fairly similar right now, but I have them seperate on
		//purpose, as I might change up the models in the future
		ClientRegistry.bindTileEntityRenderer(DeferredRegisters.TILE_MOTORAC_HV.get(), RenderMotorAC::new);
		ClientRegistry.bindTileEntityRenderer(DeferredRegisters.TILE_MOTORAC_MV.get(), RenderMotorAC::new);
		ClientRegistry.bindTileEntityRenderer(DeferredRegisters.TILE_MOTORAC_LV.get(), RenderMotorAC::new);
		
		ClientRegistry.bindTileEntityRenderer(DeferredRegisters.TILE_MOTORDC_HV.get(), RenderMotorDC::new);
		ClientRegistry.bindTileEntityRenderer(DeferredRegisters.TILE_MOTORDC_MV.get(), RenderMotorDC::new);
		ClientRegistry.bindTileEntityRenderer(DeferredRegisters.TILE_MOTORDC_LV.get(), RenderMotorDC::new);
		
		ScreenManager.registerFactory(DeferredRegisters.CONTAINER_MOTORAC.get(), ScreenMotorAC::new);
		ScreenManager.registerFactory(DeferredRegisters.CONTAINER_MOTORDC.get(), ScreenMotorDC::new);
	}
	
    public static boolean shouldMultilayerRender(RenderType type) {
    	return type == RenderType.getTranslucent() || type == RenderType.getSolid();
    }

}
