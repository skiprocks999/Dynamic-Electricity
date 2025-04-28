package dynamicelectricity.registry;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import voltaic.api.registration.BulkRegistryObject;
import voltaic.common.block.BlockMachine;

public class DynamicElectricityBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DynamicElectricity.ID);

	public static final BulkRegistryObject<BlockMachine, SubtypeDynamicMachine> BLOCKS_DYNAMICMACHINE = new BulkRegistryObject<>(SubtypeDynamicMachine.values(), subtype -> BLOCKS.register(subtype.tag(), () -> new BlockMachine(subtype)));

}
