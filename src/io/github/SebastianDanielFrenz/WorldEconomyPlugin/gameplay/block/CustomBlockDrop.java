package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public class CustomBlockDrop {

	public CustomBlockDrop(final CustomToolType tool, final CustomMaterialLevel min_lvl, final CustomItemStack[] drop) {
		this.tool = tool;
		this.min_lvl = min_lvl;
		this.drop = drop;
	}

	public CustomBlockDrop(final CustomToolType tool, final CustomMaterialLevel min_lvl, final CustomItemStack drop) {
		this.tool = tool;
		this.min_lvl = min_lvl;
		this.drop = new CustomItemStack[] { drop };
	}

	public final CustomToolType tool;
	public final CustomMaterialLevel min_lvl;
	public final CustomItemStack[] drop;

}
