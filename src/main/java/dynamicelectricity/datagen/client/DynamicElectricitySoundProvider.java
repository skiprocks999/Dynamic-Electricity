package dynamicelectricity.datagen.client;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.registry.DynamicElectricitySounds;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import voltaic.datagen.utils.client.BaseSoundProvider;

public class DynamicElectricitySoundProvider extends BaseSoundProvider {

	public DynamicElectricitySoundProvider(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, helper, DynamicElectricity.ID);
	}

	@Override
	public void registerSounds() {
		
		add(DynamicElectricitySounds.SOUND_MOTORRUNNING);
		
	}

}
