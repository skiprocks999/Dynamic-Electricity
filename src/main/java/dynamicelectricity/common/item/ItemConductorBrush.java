package dynamicelectricity.common.item;

import java.util.List;

import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityCreativeTabs;
import electrodynamics.common.item.ItemElectrodynamics;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemConductorBrush extends ItemElectrodynamics {

	public static final int BRUSH_DURABILITY = 100000;
	
	public ItemConductorBrush() {
		super(new Item.Properties().durability(BRUSH_DURABILITY), () -> DynamicElectricityCreativeTabs.MAIN.get());
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltips, TooltipFlag isAdvanced) {
		super.appendHoverText(stack, level, tooltips, isAdvanced);
		tooltips.add(UtilsText.tooltip("condudctorbrushdurability", stack.getMaxDamage() - stack.getDamageValue(), stack.getMaxDamage()).withStyle(ChatFormatting.GRAY));
	}

}
