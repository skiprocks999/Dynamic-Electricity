package dynamicelectricity.compatability.jei;

import dynamicelectricity.DynamicElectricity;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class DynamicElectricityJEIPlugin implements IModPlugin {

	public static final ResourceLocation ID = DynamicElectricity.rl("jei");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

}
