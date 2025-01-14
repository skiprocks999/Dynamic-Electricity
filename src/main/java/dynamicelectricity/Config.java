package dynamicelectricity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue USE_CONDUCTOR_BRUSH_DURABILITY = BUILDER.comment("Whether or not DC motors will deplete the durability of the conductor brush").define("useconductorbrushdurability", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean useConductorBrushDurability;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent.Loading event) {
        useConductorBrushDurability = USE_CONDUCTOR_BRUSH_DURABILITY.get();
    }
}
