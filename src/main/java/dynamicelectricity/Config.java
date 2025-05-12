package dynamicelectricity;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig.Loading;

@EventBusSubscriber(modid = DynamicElectricity.ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue USE_CONDUCTOR_BRUSH_DURABILITY = BUILDER.comment("Whether or not DC motors will deplete the durability of the conductor brush").define("useconductorbrushdurability", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean useConductorBrushDurability;

    @SubscribeEvent
    static void onLoad(Loading event) {
        useConductorBrushDurability = USE_CONDUCTOR_BRUSH_DURABILITY.get();
    }
}
