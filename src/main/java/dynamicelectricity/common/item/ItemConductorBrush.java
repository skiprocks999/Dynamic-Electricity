package dynamicelectricity.common.item;

import java.util.List;

import dynamicelectricity.References;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemConductorBrush extends Item {

	public static final int BRUSH_DURABILITY = 100000;
	
	public ItemConductorBrush() {
		super(new Item.Properties().tab(References.DYELECTAB).durability(BRUSH_DURABILITY));
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltips, TooltipFlag isAdvanced) {
		super.appendHoverText(stack, level, tooltips, isAdvanced);
		tooltips.add(UtilsText.tooltip("condudctorbrushdurability", stack.getMaxDamage() - stack.getDamageValue(), stack.getMaxDamage()).withStyle(ChatFormatting.GRAY));
	}

}
