package dynamicelectricity.core.utils;

import electrodynamics.api.electricity.formatting.IDisplayUnit;
import net.minecraft.network.chat.Component;

public enum DynamicDisplayUnits implements IDisplayUnit {

	INDUSTRIAL_ENERGY_UNIT(UtilsText.gui("displayunit.industrialenergyunit.name"), UtilsText.gui("displayunit.industrialenergyunit.nameplural"), UtilsText.gui("displayunit.industrialenergyunit.symbol"));

	private final Component symbol;
	private final Component name;
	private final Component namePlural;
	private final Component distanceFromValue;

	private DynamicDisplayUnits(Component name, Component namePlural, Component symbol) {
		this(name, namePlural, symbol, Component.literal(" "));
	}

	private DynamicDisplayUnits(Component name, Component namePlural, Component symbol, Component distanceFromValue) {
		this.name = name;
		this.namePlural = namePlural;
		this.symbol = symbol;
		this.distanceFromValue = distanceFromValue;
	}

	@Override
	public Component getSymbol() {
		return symbol;
	}

	@Override
	public Component getName() {
		return name;
	}

	@Override
	public Component getNamePlural() {
		return namePlural;
	}

	@Override
	public Component getDistanceFromValue() {
		return distanceFromValue;
	}

}
