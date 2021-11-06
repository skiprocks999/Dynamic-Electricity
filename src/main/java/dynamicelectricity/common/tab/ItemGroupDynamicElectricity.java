package dynamicelectricity.common.tab;

import dynamicelectricity.DeferredRegisters;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupDynamicElectricity extends CreativeModeTab {

	public ItemGroupDynamicElectricity(String label) {
		super(label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(DeferredRegisters.blockMotorAcHv);
	}

}
