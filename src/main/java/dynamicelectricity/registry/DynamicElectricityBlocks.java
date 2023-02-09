package dynamicelectricity.registry;

import dynamicelectricity.References;
import dynamicelectricity.common.block.motor.ac.BlockMotorAcHv;
import dynamicelectricity.common.block.motor.ac.BlockMotorAcLv;
import dynamicelectricity.common.block.motor.ac.BlockMotorAcMv;
import dynamicelectricity.common.block.motor.dc.BlockHvDcMotor;
import dynamicelectricity.common.block.motor.dc.BlockLvDcMotor;
import dynamicelectricity.common.block.motor.dc.BlockMvDcMotor;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.common.blockitem.BlockItemDescriptable;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DynamicElectricityBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.ID);

	public static BlockMotorAcHv blockMotorAcHv;
	public static BlockMotorAcMv blockMotorAcMv;
	public static BlockMotorAcLv blockMotorAcLv;
	public static BlockHvDcMotor blockMotorDcHv;
	public static BlockMvDcMotor blockMotorDcMv;
	public static BlockLvDcMotor blockMotorDcLv;

	static {

		BLOCKS.register("motorachv", () -> blockMotorAcHv = new BlockMotorAcHv());
		BLOCKS.register("motoracmv", () -> blockMotorAcMv = new BlockMotorAcMv());
		BLOCKS.register("motoraclv", () -> blockMotorAcLv = new BlockMotorAcLv());
		BLOCKS.register("motordchv", () -> blockMotorDcHv = new BlockHvDcMotor());
		BLOCKS.register("motordcmv", () -> blockMotorDcMv = new BlockMvDcMotor());
		BLOCKS.register("motordclv", () -> blockMotorDcLv = new BlockLvDcMotor());

		BlockItemDescriptable.addDescription(() -> blockMotorAcHv, UtilsText.tooltip("motorachv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorAcMv, UtilsText.tooltip("motoracmv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorAcLv, UtilsText.tooltip("motoraclv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcHv, UtilsText.tooltip("motordchv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcMv, UtilsText.tooltip("motordcmv.conversion"));
		BlockItemDescriptable.addDescription(() -> blockMotorDcLv, UtilsText.tooltip("motordclv.conversion"));

	}

}
