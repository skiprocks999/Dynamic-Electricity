package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DynamicElectricityCreativeTabs {

public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, References.ID);
	
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = CREATIVE_TABS.register("main", () -> CreativeModeTab.builder().title(UtilsText.creativeTab("main")).icon(() -> new ItemStack(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv))).build());
	
}
