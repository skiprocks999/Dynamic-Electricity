package dynamicelectricity.compatability.jei;

import dynamicelectricity.DeferredRegisters;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class DynamicElectricityJEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(dynamicelectricity.References.ID, "dynamelec_jei_plugin");
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		electrodynamics.Electrodynamics.LOGGER.info("registered recipes");
		
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcHv), VanillaTypes.ITEM,
			new TranslatableComponent("info.jei.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcMv), VanillaTypes.ITEM,
			new TranslatableComponent("info.jei.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcLv), VanillaTypes.ITEM,
			new TranslatableComponent("info.jei.item.motorac"));
		
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcHv), VanillaTypes.ITEM,
			new TranslatableComponent("info.jei.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcMv), VanillaTypes.ITEM,
			new TranslatableComponent("info.jei.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcLv), VanillaTypes.ITEM,
			new TranslatableComponent("info.jei.item.motordc"));
	}

}
