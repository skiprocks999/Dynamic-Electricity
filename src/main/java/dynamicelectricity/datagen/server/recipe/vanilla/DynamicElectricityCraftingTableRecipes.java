package dynamicelectricity.datagen.server.recipe.vanilla;

import java.util.function.Consumer;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.common.tags.DynamicElectricityTags;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.common.block.subtype.SubtypeMachine;
import electrodynamics.common.block.subtype.SubtypeWire;
import electrodynamics.registers.ElectrodynamicsItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.common.Tags;
import voltaic.common.tags.VoltaicTags;
import voltaic.datagen.utils.server.recipe.AbstractRecipeGenerator;
import voltaic.datagen.utils.server.recipe.CustomShapedCraftingRecipe;

public class DynamicElectricityCraftingTableRecipes extends AbstractRecipeGenerator {

	@Override
	public void addRecipes(Consumer<FinishedRecipe> consumer) {

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_ALTERNATOR.get(), 1)
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
				.complete(DynamicElectricity.ID, "alternator", consumer);

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_COMMUTATOR.get(), 1)
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
				.complete(DynamicElectricity.ID, "commutator", consumer);

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_CONDUCTORBRUSH.get(), 1)
				//
				.addPattern("WWW")
				//
				.addPattern("WWW")
				//
				.addKey('W', ElectrodynamicsItems.ITEMS_WIRE.getValue(SubtypeWire.copper))
				//
				.complete(DynamicElectricity.ID, "conductorbrush", consumer);

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEM_STATOR.get(), 1)
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
				.complete(DynamicElectricity.ID, "stator", consumer);

		addMotors(consumer);

	}

	private void addMotors(Consumer<FinishedRecipe> consumer) {

// AC Motors

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv), 1)
				//
				.addPattern("PPI")
				//
				.addPattern("SRC")
				//
				.addPattern("PTI")
				//
				.addKey('P', VoltaicTags.Items.PLATE_STEEL)
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
				.complete(DynamicElectricity.ID, "motor_aclv", consumer);

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv), 1)
				//
				.addPattern("PPC")
				//
				.addPattern("SRI")
				//
				.addPattern("PTC")
				//
				.addKey('P', VoltaicTags.Items.PLATE_STAINLESSSTEEL)
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
				.complete(DynamicElectricity.ID, "motor_acmv", consumer);

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv), 1)
				//
				.addPattern("PPC")
				//
				.addPattern("SRC")
				//
				.addPattern("PTC")
				//
				.addKey('P', VoltaicTags.Items.PLATE_HSLASTEEL)
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTHSLASTEEL.get())
				//
				.addKey('C', DynamicElectricityItems.ITEM_COMMUTATOR.get())
				//
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(DynamicElectricity.ID, "motor_achv", consumer);

// DC Motors

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv), 1)
				//
				.addPattern("PPI")
				//
				.addPattern("SRA")
				//
				.addPattern("PTI")
				//
				.addKey('P', VoltaicTags.Items.PLATE_STEEL)
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
				.complete(DynamicElectricity.ID, "motor_dclv", consumer);

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv), 1)
				//
				.addPattern("PPA")
				//
				.addPattern("SRI")
				//
				.addPattern("PTA")
				//
				.addKey('P', VoltaicTags.Items.PLATE_STAINLESSSTEEL)
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
				.complete(DynamicElectricity.ID, "motor_dcmv", consumer);

		CustomShapedCraftingRecipe.start(DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv), 1)
				//
				.addPattern("PPA")
				//
				.addPattern("SRA")
				//
				.addPattern("PTA")
				//
				.addKey('P', VoltaicTags.Items.PLATE_STEEL)
				//
				.addKey('S', DynamicElectricityItems.ITEM_STATOR.get())
				//
				.addKey('R', DynamicElectricityItems.ITEM_SHAFTSTEEL.get())
				//
				.addKey('A', DynamicElectricityItems.ITEM_ALTERNATOR.get())
				//
				.addKey('T', ElectrodynamicsItems.ITEMS_MACHINE.getValue(SubtypeMachine.tanksteel))
				//
				.complete(DynamicElectricity.ID, "motor_dchv", consumer);

	}

}
