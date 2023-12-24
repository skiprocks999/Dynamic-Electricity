package dynamicelectricity.common.item;

import java.util.List;

import dynamicelectricity.References;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemConductorBrush extends Item {

	public static final int BRUSH_DURABILITY = 100000;
	
	public ItemConductorBrush() {
		super(new Item.Properties().tab(References.DYELECTAB).durability(BRUSH_DURABILITY));
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World level, List<ITextComponent> tooltips, ITooltipFlag isAdvanced) {
		super.appendHoverText(stack, level, tooltips, isAdvanced);
		tooltips.add(UtilsText.tooltip("condudctorbrushdurability", stack.getMaxDamage() - stack.getDamageValue(), stack.getMaxDamage()).withStyle(TextFormatting.GRAY));
	}

}