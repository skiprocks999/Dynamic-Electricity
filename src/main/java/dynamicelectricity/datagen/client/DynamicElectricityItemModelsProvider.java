package dynamicelectricity.datagen.client;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.datagen.client.ElectrodynamicsItemModelsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DynamicElectricityItemModelsProvider extends ElectrodynamicsItemModelsProvider {


	public DynamicElectricityItemModelsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, existingFileHelper, References.ID);
	}

	@Override
	protected void registerModels() {

		simpleBlockItem(DynamicElectricityBlocks.blockMotorAcLv, existingBlock(blockLoc("motoraclvitem")));
		simpleBlockItem(DynamicElectricityBlocks.blockMotorAcMv, existingBlock(blockLoc("motoracmvitem")));
		simpleBlockItem(DynamicElectricityBlocks.blockMotorAcHv, existingBlock(blockLoc("motorachvitem")));
		
		simpleBlockItem(DynamicElectricityBlocks.blockMotorDcLv, existingBlock(blockLoc("motordclvitem")));
		simpleBlockItem(DynamicElectricityBlocks.blockMotorDcMv, existingBlock(blockLoc("motordcmvitem")));
		simpleBlockItem(DynamicElectricityBlocks.blockMotorDcHv, existingBlock(blockLoc("motordchvitem")));

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
