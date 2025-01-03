package dynamicelectricity.datagen.client;

import dynamicelectricity.References;
import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.datagen.client.ElectrodynamicsItemModelsProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class DynamicElectricityItemModelsProvider extends ElectrodynamicsItemModelsProvider {


	public DynamicElectricityItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, existingFileHelper, References.ID);
	}

	@Override
	protected void registerModels() {

		simpleBlockItem(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoraclv), existingBlock(blockLoc("motoraclvitem")));
		simpleBlockItem(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motoracmv), existingBlock(blockLoc("motoracmvitem")));
		simpleBlockItem(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv), existingBlock(blockLoc("motorachvitem")));
		
		simpleBlockItem(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordclv), existingBlock(blockLoc("motordclvitem")));
		simpleBlockItem(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordcmv), existingBlock(blockLoc("motordcmvitem")));
		simpleBlockItem(DynamicElectricityBlocks.BLOCKS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv), existingBlock(blockLoc("motordchvitem")));

		layeredItem(DynamicElectricityItems.ITEM_ALTERNATOR, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_ALTERNATOR)));
		layeredItem(DynamicElectricityItems.ITEM_COMMUTATOR, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_COMMUTATOR)));
		layeredItem(DynamicElectricityItems.ITEM_CONDUCTORBRUSH, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_CONDUCTORBRUSH)));
		//layeredItem(DynamicElectricityItems.ITEM_OXIDE_TITANIUMCHLORIDE, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_OXIDE_TITANIUMCHLORIDE)));
		layeredItem(DynamicElectricityItems.ITEM_RINGIRON, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_RINGIRON)));
		layeredItem(DynamicElectricityItems.ITEM_RINGSTEEL, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_RINGSTEEL)));
		layeredItem(DynamicElectricityItems.ITEM_SHAFTSTEEL, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_SHAFTSTEEL)));
		layeredItem(DynamicElectricityItems.ITEM_SHAFTSTAINLESSSTEEL, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_SHAFTSTAINLESSSTEEL)));
		layeredItem(DynamicElectricityItems.ITEM_SHAFTHSLASTEEL, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_SHAFTHSLASTEEL)));
		layeredItem(DynamicElectricityItems.ITEM_STATOR, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_STATOR)));
		
		layeredItem(DynamicElectricityItems.ITEM_DUSTCARBON, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_DUSTCARBON)));
		layeredItem(DynamicElectricityItems.ITEM_DUSTPDSM, Parent.GENERATED, itemLoc(name(DynamicElectricityItems.ITEM_DUSTPDSM)));

	}



}
