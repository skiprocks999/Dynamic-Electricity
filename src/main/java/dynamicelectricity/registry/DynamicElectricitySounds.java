package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DynamicElectricitySounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DynamicElectricity.ID);

	public static final RegistryObject<SoundEvent> SOUND_MOTORRUNNING = sound("motor_running");

	private static RegistryObject<SoundEvent> sound(String name) {
		return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(DynamicElectricity.ID + ":" + name)));
	}

}
