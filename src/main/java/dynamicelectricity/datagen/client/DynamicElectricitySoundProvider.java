package dynamicelectricity.datagen.client;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricitySounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinition.Sound;
import net.minecraftforge.common.data.SoundDefinition.SoundType;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.registries.RegistryObject;

public class DynamicElectricitySoundProvider extends SoundDefinitionsProvider {

	public DynamicElectricitySoundProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, References.ID, helper);
	}

	@Override
	public void registerSounds() {
		
		add(DynamicElectricitySounds.SOUND_MOTORRUNNING);
		
	}

	private void add(RegistryObject<SoundEvent> sound) {
		add(sound.get(), SoundDefinition.definition().subtitle("subtitles." + References.ID + "." + sound.getId().getPath()).with(Sound.sound(sound.getId(), SoundType.SOUND)));
	}

}
