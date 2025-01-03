package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import electrodynamics.api.registration.BulkDeferredHolder;
import electrodynamics.common.block.BlockMachine;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DynamicElectricityBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, References.ID);

	public static final BulkDeferredHolder<Block, BlockMachine, SubtypeDynamicMachine> BLOCKS_DYNAMICMACHINE = new BulkDeferredHolder<>(SubtypeDynamicMachine.values(), subtype -> BLOCKS.register(subtype.tag(), () -> new BlockMachine(subtype)));

}
