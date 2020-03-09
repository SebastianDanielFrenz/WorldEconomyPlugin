package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

public enum Ore {

	GOLD(CustomItem.GOLD_NUGGET);

	private Ore(CustomItem item) {
		this.item = item;
	}

	public final CustomItem item;

}
