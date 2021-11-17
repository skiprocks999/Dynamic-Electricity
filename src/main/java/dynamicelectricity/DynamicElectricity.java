package dynamicelectricity;

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

import dynamicelectricity.common.tags.DynamicElectricityTags;


@Mod(dynamicelectricity.References.ID)
@EventBusSubscriber(modid = dynamicelectricity.References.ID, bus = Bus.MOD)
public class DynamicElectricity
{
    public static final Logger LOGGER = LogManager.getLogger();

    public DynamicElectricity() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	dynamicelectricity.DeferredRegisters.BLOCKS.register(bus);
    	dynamicelectricity.DeferredRegisters.ITEMS.register(bus);
    	dynamicelectricity.DeferredRegisters.TILES.register(bus);
    	dynamicelectricity.DeferredRegisters.CONTAINERS.register(bus);
    	dynamicelectricity.DeferredRegisters.FLUIDS.register(bus);
    	dynamicelectricity.SoundRegister.SOUNDS.register(bus);
    }
    
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
    	DynamicElectricityTags.init();
    }
    
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup(FMLClientSetupEvent event) {
    	dynamicelectricity.client.ClientRegister.setup();
    }
    
    @SubscribeEvent
    public static void onLoadEvent(FMLLoadCompleteEvent event) {}

}
