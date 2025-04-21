package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DynamicElectricitySounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, DynamicElectricity.ID);

	public static final DeferredHolder<SoundEvent, SoundEvent> SOUND_MOTORRUNNING = sound("motor_running");

	private static DeferredHolder<SoundEvent, SoundEvent> sound(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(DynamicElectricity.rl(name), 16.0F));
	}

}
