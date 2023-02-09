package dynamicelectricity.compatability.jei;

import dynamicelectricity.References;
import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class DynamicElectricityJEIPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(References.ID, "jei");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(new ItemStack(DynamicElectricityBlocks.blockMotorAcHv), VanillaTypes.ITEM_STACK, UtilsText.jeiTranslated("info.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DynamicElectricityBlocks.blockMotorAcMv), VanillaTypes.ITEM_STACK, UtilsText.jeiTranslated("info.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DynamicElectricityBlocks.blockMotorAcLv), VanillaTypes.ITEM_STACK, UtilsText.jeiTranslated("info.item.motorac"));

		registration.addIngredientInfo(new ItemStack(DynamicElectricityBlocks.blockMotorDcHv), VanillaTypes.ITEM_STACK, UtilsText.jeiTranslated("info.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DynamicElectricityBlocks.blockMotorDcMv), VanillaTypes.ITEM_STACK, UtilsText.jeiTranslated("info.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DynamicElectricityBlocks.blockMotorDcLv), VanillaTypes.ITEM_STACK, UtilsText.jeiTranslated("info.item.motordc"));
	}

}
