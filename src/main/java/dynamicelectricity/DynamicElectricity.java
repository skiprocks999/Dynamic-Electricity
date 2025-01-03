package dynamicelectricity;

import dynamicelectricity.client.ClientRegister;
import dynamicelectricity.common.block.DynamicElectricityVoxelShapes;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;


@Mod(References.ID)
@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD)
public class DynamicElectricity {

    public DynamicElectricity(IEventBus bus) {
        UnifiedDynamicElectricityRegistry.init(bus);
        DynamicElectricityTags.init();
        DynamicElectricityVoxelShapes.init();
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientRegister.setup();
        });
    }

    @SubscribeEvent
    public static void onLoadEvent(FMLLoadCompleteEvent event) {
    }

}
