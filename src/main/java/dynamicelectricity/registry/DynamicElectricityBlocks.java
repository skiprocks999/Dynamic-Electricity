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
import net.minecraft.block.Block;
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

		BLOCKS.register("motorachv", () -> blockMotorAcHv = new GenericMachineBlock(world -> new TileMotorAcHv()));
		BLOCKS.register("motoracmv", () -> blockMotorAcMv = new GenericMachineBlock(world -> new TileMotorAcMv()));
		BLOCKS.register("motoraclv", () -> blockMotorAcLv = new GenericMachineBlock(world -> new TileMotorAcLv()));
		BLOCKS.register("motordchv", () -> blockMotorDcHv = new GenericMachineBlock(world -> new TileMotorDcHv()));
		BLOCKS.register("motordcmv", () -> blockMotorDcMv = new GenericMachineBlock(world -> new TileMotorDcMv()));
		BLOCKS.register("motordclv", () -> blockMotorDcLv = new GenericMachineBlock(world -> new TileMotorDcLv()));

		BlockItemDescriptable.addDescription(() -> blockMotorAcHv, UtilsText.tooltip("motorachv.conversion"));
		
		BlockItemDescriptable.addDescription(() -> blockMotorAcMv, UtilsText.tooltip("motoracmv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorAcLv, UtilsText.tooltip("motoraclv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcHv, UtilsText.tooltip("motordchv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcMv, UtilsText.tooltip("motordcmv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcLv, UtilsText.tooltip("motordclv.conversion"));

	}

}
