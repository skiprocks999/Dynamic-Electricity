package dynamicelectricity.common.item;

import java.util.List;

import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityCreativeTabs;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import voltaic.common.item.ItemVoltaic;

public class ItemConductorBrush extends ItemVoltaic {

	public static final int BRUSH_DURABILITY = 100000;
	
	public ItemConductorBrush() {
		super(new Item.Properties().durability(BRUSH_DURABILITY), () -> DynamicElectricityCreativeTabs.MAIN);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
		tooltip.add(UtilsText.tooltip("condudctorbrushdurability", stack.getMaxDamage() - stack.getDamageValue(), stack.getMaxDamage()).withStyle(ChatFormatting.DARK_GRAY));
	}

}
