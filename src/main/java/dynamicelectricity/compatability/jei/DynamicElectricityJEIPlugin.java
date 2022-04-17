package dynamicelectricity.compatability.jei;

import dynamicelectricity.DeferredRegisters;
import dynamicelectricity.References;
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
		return new ResourceLocation(References.ID, "jei");
	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcHv), VanillaTypes.ITEM_STACK,
			new TranslatableComponent("info.jei.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcMv), VanillaTypes.ITEM_STACK,
			new TranslatableComponent("info.jei.item.motorac"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorAcLv), VanillaTypes.ITEM_STACK,
			new TranslatableComponent("info.jei.item.motorac"));
		
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcHv), VanillaTypes.ITEM_STACK,
			new TranslatableComponent("info.jei.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcMv), VanillaTypes.ITEM_STACK,
			new TranslatableComponent("info.jei.item.motordc"));
		registration.addIngredientInfo(new ItemStack(DeferredRegisters.blockMotorDcLv), VanillaTypes.ITEM_STACK,
			new TranslatableComponent("info.jei.item.motordc"));
	}

}
