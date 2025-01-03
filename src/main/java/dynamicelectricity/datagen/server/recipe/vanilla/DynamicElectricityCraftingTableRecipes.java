package dynamicelectricity.datagen.server.recipe.vanilla;

import dynamicelectricity.References;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.common.block.subtype.SubtypeMachine;
import electrodynamics.common.block.subtype.SubtypeWire;
import electrodynamics.common.tags.ElectrodynamicsTags;
import electrodynamics.datagen.utils.recipe.AbstractRecipeGenerator;
import electrodynamics.datagen.utils.recipe.ShapedCraftingRecipeBuilder;
import electrodynamics.registers.ElectrodynamicsItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.neoforged.neoforge.common.Tags;

public class DynamicElectricityCraftingTableRecipes extends AbstractRecipeGenerator {

	@Override
	public void addRecipes(RecipeOutput output) {

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEM_ALTERNATOR.get(), 1)
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
				.complete(References.ID, "alternator", output);

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEM_COMMUTATOR.get(), 1)
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
				.complete(References.ID, "commutator", output);

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEM_CONDUCTORBRUSH.get(), 1)
				//
				.addPattern("WWW")
				//
				.addPattern("WWW")
				//
				.addKey('W', ElectrodynamicsItems.ITEMS_WIRE.getValue(SubtypeWire.copper))
				//
				.complete(References.ID, "conductorbrush", output);

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEM_STATOR.get(), 1)
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
				.complete(References.ID, "stator", output);

		addMotors(output);

	}

	private void addMotors(RecipeOutput output) {

		// AC Motors

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv), 1)
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
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(References.ID, "motor_aclv", output);

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv), 1)
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
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(References.ID, "motor_acmv", output);

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv), 1)
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
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(References.ID, "motor_achv", output);

		// DC Motors

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv), 1)
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
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(References.ID, "motor_dclv", output);

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv), 1)
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
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(References.ID, "motor_dcmv", output);

		ShapedCraftingRecipeBuilder.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv), 1)
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
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(References.ID, "motor_dchv", output);

	}

}
