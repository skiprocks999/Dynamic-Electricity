package dynamicelectricity.datagen.client;

import dynamicelectricity.References;
import dynamicelectricity.registry.DynamicElectricityBlocks;
import dynamicelectricity.registry.DynamicElectricityFluids;
import dynamicelectricity.registry.DynamicElectricityItems;
import dynamicelectricity.registry.DynamicElectricitySounds;
import electrodynamics.datagen.client.ElectrodynamicsLangKeyProvider;
import net.minecraft.data.DataGenerator;

public class DynamicElectricityLangKeyProvider extends ElectrodynamicsLangKeyProvider {

	public DynamicElectricityLangKeyProvider(DataGenerator gen, Locale locale) {
		super(gen, locale, References.ID);
	}

	@Override
	protected void addTranslations() {

		switch (locale) {
		case EN_US:
		default:

			add("itemGroup.itemgroupdynamicelectricity", "Dynamic Electricity");

			addBlock(DynamicElectricityBlocks.blockMotorAcHv, "480V AC Motor");
			addBlock(DynamicElectricityBlocks.blockMotorAcMv, "240V AC Motor");
			addBlock(DynamicElectricityBlocks.blockMotorAcLv, "120V AC Motor");
			addBlock(DynamicElectricityBlocks.blockMotorDcHv, "480V DC Motor");
			addBlock(DynamicElectricityBlocks.blockMotorDcMv, "240V DC Motor");
			addBlock(DynamicElectricityBlocks.blockMotorDcLv, "120V DC Motor");

			addItem(DynamicElectricityItems.ITEM_STATOR, "Industrial Stator");
			addItem(DynamicElectricityItems.ITEM_COMMUTATOR, "Commutator");
			addItem(DynamicElectricityItems.ITEM_ALTERNATOR, "Alternator");
			addItem(DynamicElectricityItems.ITEM_SHAFTSTEEL, "Steel Rotor Shaft");
			addItem(DynamicElectricityItems.ITEM_SHAFTSTAINLESSSTEEL, "Stainless Rotor Shaft");
			addItem(DynamicElectricityItems.ITEM_SHAFTHSLASTEEL, "HSLA Rotor Shaft");
			addItem(DynamicElectricityItems.ITEM_RINGIRON, "Iron Ring");
			addItem(DynamicElectricityItems.ITEM_RINGSTEEL, "Steel Ring");
			//addItem(DynamicElectricityItems.ITEM_OXIDE_TITANIUMCHLORIDE, "Titanium Tetrachloride");
			addItem(DynamicElectricityItems.ITEM_CONDUCTORBRUSH, "Conductor Brush");
			
			addItem(DynamicElectricityItems.ITEM_DUSTCARBON, "Carbon Dust");
			addItem(DynamicElectricityItems.ITEM_DUSTPDSM, "PDSM Dust");

			addContainer("motorachv", "480V AC Motor");
			addContainer("motoracmv", "240V AC Motor");
			addContainer("motoraclv", "120V AC Motor");
			addContainer("motordchv", "480V DC Motor");
			addContainer("motordcmv", "240V DC Motor");
			addContainer("motordclv", "120V DC Motor");

			addFluid(DynamicElectricityFluids.fluidLubricant, "Industrial Lubricant");

			addTooltip("motorachv.conversion", "100 kJ @ 480V -> 100 kFE");
			addTooltip("motoracmv.conversion", "10 kJ @ 240V -> 10 kFE");
			addTooltip("motoraclv.conversion", "1 kJ @ 120V -> 1000 FE");
			addTooltip("motordchv.conversion", "100 kFE -> 95 kJ @ 480V");
			addTooltip("motordcmv.conversion", "10 kFE -> 9.5 kJ @ 240V");
			addTooltip("motordclv.conversion", "1 kFE -> 950 J @ 120V");
			
			addTooltip("dustpdsm", "Polydimethylsiloxane");
			addTooltip("condudctorbrushdurability", "%1$s / %2$s");
			
			addSubtitle(DynamicElectricitySounds.SOUND_MOTORRUNNING, "Motor runs");

			addGuiLabel("motor.usage", "Usage, %s");
			addGuiLabel("motor.wattage", "Wattage, %s");
			addGuiLabel("motor.voltage", "Voltage, %s");
			addGuiLabel("motor.output", "Output, %s");
			addGuiLabel("motor.lubricant", "Lubricant, ");
			addGuiLabel("motor.generating", "Generating");

			addJei("info.item.motorac", "The AC Motor is capable of converting Joules into FE. The motor needs to be lubricated to run. This can be accomplished by piping lubricant into the bottom of the motor. The efficiency of the motor is 100%.");
			addJei("info.item.motordc", "The DC Motor is capable of converting FE into Joules. The motor needs to be lubricated to along with a Conductor Brush to run. The brush is added to it's inventory while the lubricant is piped into the bottom. The requirement of a brush makes it less efficiant than it's AC counterpart); with an efficiency of 95%.");

		}

	}

}
