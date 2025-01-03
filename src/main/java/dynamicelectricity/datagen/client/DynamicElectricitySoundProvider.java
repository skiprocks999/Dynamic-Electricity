package dynamicelectricity.datagen.client;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricitySounds;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DynamicElectricitySoundProvider extends SoundDefinitionsProvider {

	public DynamicElectricitySoundProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, References.ID, helper);
	}

	@Override
	public void registerSounds() {
		
		add(DynamicElectricitySounds.SOUND_MOTORRUNNING);
		
	}

	private void add(DeferredHolder<SoundEvent, SoundEvent> sound) {
		add(sound.get(), SoundDefinition.definition().subtitle("subtitles." + References.ID + "." + sound.getId().getPath()).with(SoundDefinition.Sound.sound(sound.getId(), SoundDefinition.SoundType.SOUND)));
	}

}
