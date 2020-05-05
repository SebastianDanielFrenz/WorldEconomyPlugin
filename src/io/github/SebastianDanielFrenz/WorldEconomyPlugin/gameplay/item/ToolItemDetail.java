package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;

public class ToolItemDetail extends ItemDetail {

	public final CustomToolType tool;
	public final CustomMaterialLevel lvl;

	public ToolItemDetail(CustomToolType tool, CustomMaterialLevel lvl) {
		this.tool = tool;
		this.lvl = lvl;
	}

	public CustomToolType getToolType() {
		return tool;
	}

	public CustomMaterialLevel getToolLevel() {
		return lvl;
	}

}
