package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.items.CustomItemStack;

public class CustomBlockDrop {

	public CustomBlockDrop(final CustomToolType tool, final CustomMaterialLevel min_lvl, final CustomItemStack[] drop) {
		this.tool = tool;
		this.min_lvl = min_lvl;
		this.drop = drop;
	}

	public final CustomToolType tool;
	public final CustomMaterialLevel min_lvl;
	public final CustomItemStack[] drop;

}
