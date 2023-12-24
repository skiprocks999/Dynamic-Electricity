package dynamicelectricity.common.tab;

import dynamicelectricity.registry.DynamicElectricityBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupDynamicElectricity extends ItemGroup{

	public ItemGroupDynamicElectricity(String label) {
		super(label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(DynamicElectricityBlocks.blockMotorAcHv);
	}

}
