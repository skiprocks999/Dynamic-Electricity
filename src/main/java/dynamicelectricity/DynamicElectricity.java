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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import dynamicelectricity.client.ClientRegister;
import dynamicelectricity.common.block.DynamicElectricityVoxelShapeRegistry;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityContainers;
import dynamicelectricity.registry.DynamicElectricityFluids;
import dynamicelectricity.registry.DynamicElectricityItems;
import dynamicelectricity.registry.DynamicElectricitySounds;
import dynamicelectricity.registry.DynamicElectricityTiles;

@Mod(dynamicelectricity.References.ID)
@EventBusSubscriber(modid = dynamicelectricity.References.ID, bus = Bus.MOD)
public class DynamicElectricity {

	public DynamicElectricity() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		DynamicElectricityBlocks.BLOCKS.register(bus);
    	DynamicElectricityContainers.CONTAINERS.register(bus);
    	DynamicElectricityFluids.FLUIDS.register(bus);
    	DynamicElectricityItems.ITEMS.register(bus);
    	DynamicElectricityTiles.TILES.register(bus);
    	DynamicElectricitySounds.SOUNDS.register(bus);
	}

	@SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
    	DynamicElectricityTags.init();
    	DynamicElectricityVoxelShapeRegistry.init();
    }
    
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientSetup(FMLClientSetupEvent event) {
    	event.enqueueWork(() -> {
    		ClientRegister.setup();
    	});
    }

}
