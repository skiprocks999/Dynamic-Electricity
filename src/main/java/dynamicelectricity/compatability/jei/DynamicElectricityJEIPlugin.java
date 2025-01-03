package dynamicelectricity.compatability.jei;

import dynamicelectricity.References;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class DynamicElectricityJEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.fromNamespaceAndPath(References.ID, "jei");
	}

}
