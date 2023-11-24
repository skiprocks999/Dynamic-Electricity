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

			add("itemGroup.itemgroup" + References.ID, "Dynamic Electricity");

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

			addTooltip("indrebstandard", "Standard");
			addTooltip("indrebadvanced", "Advanced");
			addTooltip("indrebsuper", "Super");

			addSubtitle(DynamicElectricitySounds.SOUND_MOTORRUNNING, "Motor runs");

			addGuiLabel("motor.usage", "Usage: %s");
			addGuiLabel("motor.wattage", "Wattage: %s");
			addGuiLabel("motor.voltage", "Voltage: %s");
			addGuiLabel("motor.output", "Output: %s");
			addGuiLabel("motor.lubricant", "Lubricant: ");
			addGuiLabel("motor.generating", "Generating");
			addGuiLabel("motor.tier", "Tier: %s");

			addGuiLabel("displayunit.industrialenergyunit.name", "Industrial Energy Unit");
			addGuiLabel("displayunit.industrialenergyunit.nameplural", "Industrial Energy Units");
			addGuiLabel("displayunit.industrialenergyunit.symbol", "IE");

			addGuidebook(References.ID, "Dynamic Electricity");

			addGuidebook("chapter.dynamicelectricity", "Dynamic Electricity");
			addGuidebook("chapter.dynamicelectricity.l1",
					"Dynamic Electricity is geared towards large-scale energy conversion. Electrodynamics Battery Boxes can naturally convert FE to Joules. They are also able to power FE machines, but assume that the FE machine is rated for only 120V. However, Battery Boxes have a limited input and output rate. "
							+ "Dynamic Electricty offers tiers of motors that offer a much higher throughput value than the battery boxe is capable of. However, while they offer a higher throughput, a motor requires a bit more care to operate, including a supply of lubrication to keep it running. Lubricant is piped fortunately fairly "
							+ "cheap to make and can easily be piped into the bottom of the motor. Motors will also continuously convert energy regaurdless of whether or not there is a use for it. Fortunately however, they can be turned off and on by a redstone signal, meaning you can turn them off and on when needed.");

			addGuidebook("chapter.acmotors", "AC Motors");
			addGuidebook("chapter.acmotors.l1", "The AC Motor is capable of converting Electrodynamics Joules (J) into Forge Energy Units (FE). It is able to accomplish this with 100% efficiency. Three tiers of motors exist offering different throughput rates to suit your requirements.");

			addGuidebook("chapter.dcmotors", "DC Motors");
			addGuidebook("chapter.dcmotors.l1", "The DC motor is capable of converting Forge Energy Units (FE) into Electrodynamics Joules (J). It accomplishes this with 95% efficiency. Unlike the AC Motor, the DC Motor requires an additional component to run called the Conductor Brush. The brush has a limited durability, but can be easily replenished "
					+ "by hoppering more in from the top of the motor. Three tiers of motors exist offering different throughput rates to suit your requirements.");

			addGuidebook("chapter.industrialreborn", "Industrial Reborn");
			addGuidebook("chapter.industrialreborn.l1", "Motors are able to convert Electrodynamics Joules (and by extension FE) to Industrial Reborn IE. The conversion rate of Joules to IE is four to one, meaning that for every 4 Joules, you have 1 IE. The following motor tiers correspond to the following Energy Tiers:");
			addGuidebook("chapter.industrialreborn.lv", "LV : Standard");
			addGuidebook("chapter.industrialreborn.mv", "MV : Advanced");
			addGuidebook("chapter.industrialreborn.hv", "HV : Super");
			addGuidebook("chapter.industrialreborn.l2", "As with FE, AC motors have no conversion penalty. However, as too with FE, DC Motors have a 5% energy loss penalty to convert to Joules.");

		}

	}

}
