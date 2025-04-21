package dynamicelectricity.datagen.client;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.registry.DynamicElectricitySounds;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import voltaic.datagen.utils.client.BaseSoundProvider;

public class DynamicElectricitySoundProvider extends BaseSoundProvider {

	public DynamicElectricitySoundProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, helper, DynamicElectricity.ID);
	}

	@Override
	public void registerSounds() {
		
		add(DynamicElectricitySounds.SOUND_MOTORRUNNING);
		
	}

}
