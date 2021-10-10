package dynamicelectricity.common.tab;

import dynamicelectricity.DeferredRegisters;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupDynamicElectricity extends ItemGroup{

	public ItemGroupDynamicElectricity(String label) {
		super(label);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(DeferredRegisters.blockMotorAcHv);
	}

}
