package dynamicelectricity;

import dynamicelectricity.client.ClientRegister;
import dynamicelectricity.common.block.DynamicElectricityVoxelShapes;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;


@Mod(References.ID)
@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD)
public class DynamicElectricity {

    public DynamicElectricity(IEventBus bus, ModContainer modContainer) {
        UnifiedDynamicElectricityRegistry.init(bus);
        DynamicElectricityTags.init();
        DynamicElectricityVoxelShapes.init();
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC);
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

    public static final ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(References.ID, path);
    }

}
