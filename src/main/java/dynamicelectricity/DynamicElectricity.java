package dynamicelectricity;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dynamicelectricity.client.ClientRegister;
import dynamicelectricity.common.block.DynamicElectricityVoxelShapes;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.UnifiedDynamicElectricityRegistry;

@Mod(DynamicElectricity.ID)
@EventBusSubscriber(modid = DynamicElectricity.ID, bus = Bus.MOD)
public class DynamicElectricity {

	public static final String ID = "dynamicelectricity";
	public static final String NAME = "Dynamic Electricity";

	public static final String INDUSTRIAL_REBORN_ID = "indreb";

	public static final Logger LOGGER = LogManager.getLogger();

	public DynamicElectricity() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	UnifiedDynamicElectricityRegistry.init(bus);
    }
    
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
    	DynamicElectricityTags.init();
    	DynamicElectricityVoxelShapes.init();
    }
    
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup(FMLClientSetupEvent event) {
    	event.enqueueWork(() -> {
    		ClientRegister.setup();
    	});
    }
    
    @SubscribeEvent
    public static void onLoadEvent(FMLLoadCompleteEvent event) {}
    
    public static final ResourceLocation rl(String path) {
        return new ResourceLocation(DynamicElectricity.ID, path);
    }

}
