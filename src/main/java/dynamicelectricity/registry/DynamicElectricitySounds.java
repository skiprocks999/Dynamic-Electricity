package dynamicelectricity.registry;

import dynamicelectricity.References;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicElectricitySounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, References.ID);

	public static final RegistryObject<SoundEvent> SOUND_MOTORRUNNING = sound("motor_running");

	private static RegistryObject<SoundEvent> sound(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(new ResourceLocation(References.ID + ":" + name), 16.0F));
	}

}
