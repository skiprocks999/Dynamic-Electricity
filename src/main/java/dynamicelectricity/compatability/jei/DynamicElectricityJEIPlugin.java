package dynamicelectricity.compatability.jei;

import dynamicelectricity.DeferredRegisters;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

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
			new TranslationTextComponent("info.jei.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcMv), VanillaTypes.ITEM,
			new TranslationTextComponent("info.jei.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcLv), VanillaTypes.ITEM,
			new TranslationTextComponent("info.jei.item.motorac"));
		
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcHv), VanillaTypes.ITEM,
			new TranslationTextComponent("info.jei.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcMv), VanillaTypes.ITEM,
			new TranslationTextComponent("info.jei.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcLv), VanillaTypes.ITEM,
			new TranslationTextComponent("info.jei.item.motordc"));
	}

}
