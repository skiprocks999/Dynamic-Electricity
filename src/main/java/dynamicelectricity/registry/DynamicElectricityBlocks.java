package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import voltaic.api.registration.BulkDeferredHolder;
import voltaic.common.block.BlockMachine;

public class DynamicElectricityBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, DynamicElectricity.ID);

	public static final BulkDeferredHolder<Block, BlockMachine, SubtypeDynamicMachine> BLOCKS_DYNAMICMACHINE = new BulkDeferredHolder<>(SubtypeDynamicMachine.values(), subtype -> BLOCKS.register(subtype.tag(), () -> new BlockMachine(subtype)));

}
