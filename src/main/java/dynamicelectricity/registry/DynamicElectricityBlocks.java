package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.tile.TileMotorAcHv;
import dynamicelectricity.common.tile.TileMotorAcLv;
import dynamicelectricity.common.tile.TileMotorAcMv;
import dynamicelectricity.common.tile.TileMotorDcHv;
import dynamicelectricity.common.tile.TileMotorDcLv;
import dynamicelectricity.common.tile.TileMotorDcMv;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.common.blockitem.BlockItemDescriptable;
import electrodynamics.prefab.block.GenericMachineBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricityBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.ID);

	public static GenericMachineBlock blockMotorAcHv;
	public static GenericMachineBlock blockMotorAcMv;
	public static GenericMachineBlock blockMotorAcLv;
	public static GenericMachineBlock blockMotorDcHv;
	public static GenericMachineBlock blockMotorDcMv;
	public static GenericMachineBlock blockMotorDcLv;

	static {

		BLOCKS.register("motorachv", () -> blockMotorAcHv = new GenericMachineBlock(TileMotorAcHv::new));
		BLOCKS.register("motoracmv", () -> blockMotorAcMv = new GenericMachineBlock(TileMotorAcMv::new));
		BLOCKS.register("motoraclv", () -> blockMotorAcLv = new GenericMachineBlock(TileMotorAcLv::new));
		BLOCKS.register("motordchv", () -> blockMotorDcHv = new GenericMachineBlock(TileMotorDcHv::new));
		BLOCKS.register("motordcmv", () -> blockMotorDcMv = new GenericMachineBlock(TileMotorDcMv::new));
		BLOCKS.register("motordclv", () -> blockMotorDcLv = new GenericMachineBlock(TileMotorDcLv::new));

		BlockItemDescriptable.addDescription(() -> blockMotorAcHv, UtilsText.tooltip("motorachv.conversion"));
		
		BlockItemDescriptable.addDescription(() -> blockMotorAcMv, UtilsText.tooltip("motoracmv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorAcLv, UtilsText.tooltip("motoraclv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcHv, UtilsText.tooltip("motordchv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcMv, UtilsText.tooltip("motordcmv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcLv, UtilsText.tooltip("motordclv.conversion"));

	}

}
