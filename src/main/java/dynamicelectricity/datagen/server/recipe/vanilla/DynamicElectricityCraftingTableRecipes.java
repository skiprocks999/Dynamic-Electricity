package dynamicelectricity.datagen.server.recipe.vanilla;

import java.util.function.Consumer;

import dynamicelectricity.References;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.common.block.subtype.SubtypeMachine;
import electrodynamics.common.block.subtype.SubtypeWire;
import electrodynamics.common.tags.ElectrodynamicsTags;
import electrodynamics.datagen.utils.recipe.AbstractRecipeGenerator;
import electrodynamics.datagen.utils.recipe.ElectrodynamicsShapedCraftingRecipe;
import electrodynamics.registers.ElectrodynamicsItems;
import net.minecraft.data.IFinishedRecipe;
import net.minecraftforge.common.Tags;

public class DynamicElectricityCraftingTableRecipes extends AbstractRecipeGenerator {

	@Override
	public void addRecipes(Consumer<IFinishedRecipe> consumer) {

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_ALTERNATOR.get(), 1)
				//
				.addPattern("DCD")
				//
				.addPattern("CRC")
				//
				.addPattern("DCD")
				//
				.addKey('D', Tags.Items.DUSTS_REDSTONE)
				//
				.addKey('C', ElectrodynamicsItems.ITEM_COIL.get())
				//
				.addKey('R', DynamicElectricityTags.Items.RING_IRON)
				//
				.complete(References.ID, "alternator", consumer);

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_COMMUTATOR.get(), 1)
				//
				.addPattern("CCC")
				//
				.addPattern("RRR")
				//
				.addPattern("CCC")
				//
				.addKey('C', ElectrodynamicsItems.ITEM_COIL.get())
				//
				.addKey('R', DynamicElectricityTags.Items.RING_IRON)
				//
				.complete(References.ID, "commutator", consumer);

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_CONDUCTORBRUSH.get(), 1)
				//
				.addPattern("WWW")
				//
				.addPattern("WWW")
				//
				.addKey('W', WIRES[SubtypeWire.copper.ordinal()])
				//
				.complete(References.ID, "conductorbrush", consumer);

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_STATOR.get(), 1)
				//
				.addPattern("CCC")
				//
				.addPattern("CRC")
				//
				.addPattern("CCC")
				//
				.addKey('C', ElectrodynamicsItems.ITEM_COIL.get())
				//
				.addKey('R', DynamicElectricityTags.Items.RING_STEEL)
				//
				.complete(References.ID, "stator", consumer);

		addMotors(consumer);

	}

	private void addMotors(Consumer<IFinishedRecipe> consumer) {

		// AC Motors

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityBlocks.blockMotorAcLv.asItem(), 1)
				//
				.addPattern("PPI")
				//
				.addPattern("SRC")
				//
				.addPattern("PTI")
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('I', ElectrodynamicsItems.ITEM_INSULATION.get())
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTSTEEL.get())
				//
				.addKey('C', DynamicElectricityItems.ITEM_COMMUTATOR.get())
				//
				.addKey('T', MACHINES[SubtypeMachine.tanksteel.ordinal()])
				//
				.complete(References.ID, "motor_aclv", consumer);

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityBlocks.blockMotorAcMv.asItem(), 1)
				//
				.addPattern("PPC")
				//
				.addPattern("SRI")
				//
				.addPattern("PTC")
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STAINLESSSTEEL)
				//
				.addKey('I', ElectrodynamicsItems.ITEM_INSULATION.get())
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTSTAINLESSSTEEL.get())
				//
				.addKey('C', DynamicElectricityItems.ITEM_COMMUTATOR.get())
				//
				.addKey('T', MACHINES[SubtypeMachine.tanksteel.ordinal()])
				//
				.complete(References.ID, "motor_acmv", consumer);

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityBlocks.blockMotorAcHv.asItem(), 1)
				//
				.addPattern("PPC")
				//
				.addPattern("SRC")
				//
				.addPattern("PTC")
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_HSLASTEEL)
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTHSLASTEEL.get())
				//
				.addKey('C', DynamicElectricityItems.ITEM_COMMUTATOR.get())
				//
				.addKey('T', MACHINES[SubtypeMachine.tanksteel.ordinal()])
				//
				.complete(References.ID, "motor_achv", consumer);

		// DC Motors

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityBlocks.blockMotorDcLv.asItem(), 1)
				//
				.addPattern("PPI")
				//
				.addPattern("SRA")
				//
				.addPattern("PTI")
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('I', ElectrodynamicsItems.ITEM_INSULATION.get())
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTSTEEL.get())
				//
				.addKey('A', DynamicElectricityItems.ITEM_ALTERNATOR.get())
				//
				.addKey('T', MACHINES[SubtypeMachine.tanksteel.ordinal()])
				//
				.complete(References.ID, "motor_dclv", consumer);

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityBlocks.blockMotorDcMv.asItem(), 1)
				//
				.addPattern("PPA")
				//
				.addPattern("SRI")
				//
				.addPattern("PTA")
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STAINLESSSTEEL)
				//
				.addKey('I', ElectrodynamicsItems.ITEM_INSULATION.get())
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTSTAINLESSSTEEL.get())
				//
				.addKey('A', DynamicElectricityItems.ITEM_ALTERNATOR.get())
				//
				.addKey('T', MACHINES[SubtypeMachine.tanksteel.ordinal()])
				//
				.complete(References.ID, "motor_dcmv", consumer);

		ElectrodynamicsShapedCraftingRecipe.start(DynamicElectricityBlocks.blockMotorDcHv.asItem(), 1)
				//
				.addPattern("PPA")
				//
				.addPattern("SRA")
				//
				.addPattern("PTA")
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTSTEEL.get())
				//
				.addKey('A', DynamicElectricityItems.ITEM_ALTERNATOR.get())
				//
				.addKey('T', MACHINES[SubtypeMachine.tanksteel.ordinal()])
				//
				.complete(References.ID, "motor_dchv", consumer);

	}

}
