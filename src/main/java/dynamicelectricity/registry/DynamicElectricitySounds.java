package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricitySounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DynamicElectricity.ID);

	public static final RegistryObject<SoundEvent> SOUND_MOTORRUNNING = sound("motor_running");

	private static RegistryObject<SoundEvent> sound(String name) {
		return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(DynamicElectricity.ID + ":" + name)));
	}

}
