package dynamicelectricity.common.item;

import java.util.List;

import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityCreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import voltaic.common.item.ItemVoltaic;

public class ItemConductorBrush extends ItemVoltaic {

	public static final int BRUSH_DURABILITY = 100000;
	
	public ItemConductorBrush() {
		super(new Item.Properties().durability(BRUSH_DURABILITY), () -> DynamicElectricityCreativeTabs.MAIN);
	}

	@Override
	public void appendHoverText(ItemStack stack, World context, List<ITextComponent> tooltip, ITooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
		tooltip.add(UtilsText.tooltip("condudctorbrushdurability", stack.getMaxDamage() - stack.getDamageValue(), stack.getMaxDamage()).withStyle(TextFormatting.DARK_GRAY));
	}

}
