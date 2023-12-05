package dynamicelectricity.core.utils;

import electrodynamics.api.electricity.formatting.IDisplayUnit;
import electrodynamics.prefab.utilities.ElectroTextUtils;
import net.minecraft.network.chat.MutableComponent;

public enum DynamicDisplayUnits implements IDisplayUnit {

	INDUSTRIAL_ENERGY_UNIT(UtilsText.gui("displayunit.industrialenergyunit.name"), UtilsText.gui("displayunit.industrialenergyunit.nameplural"), UtilsText.gui("displayunit.industrialenergyunit.symbol"));

	private final MutableComponent symbol;
	private final MutableComponent name;
	private final MutableComponent namePlural;
	private final MutableComponent distanceFromValue;

	private DynamicDisplayUnits(MutableComponent name, MutableComponent namePlural, MutableComponent symbol) {
		this(name, namePlural, symbol, ElectroTextUtils.empty());
	}

	private DynamicDisplayUnits(MutableComponent name, MutableComponent namePlural, MutableComponent symbol, MutableComponent distanceFromValue) {
		this.name = name;
		this.namePlural = namePlural;
		this.symbol = symbol;
		this.distanceFromValue = distanceFromValue;
	}

	@Override
	public MutableComponent getSymbol() {
		return symbol;
	}

	@Override
	public MutableComponent getName() {
		return name;
	}

	@Override
	public MutableComponent getNamePlural() {
		return namePlural;
	}

	@Override
	public MutableComponent getDistanceFromValue() {
		return distanceFromValue;
	}

}