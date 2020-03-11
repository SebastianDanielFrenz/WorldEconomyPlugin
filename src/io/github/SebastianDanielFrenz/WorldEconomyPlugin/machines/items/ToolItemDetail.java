package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.CustomToolType;

public class ToolItemDetail extends ItemDetail {

	public ToolItemDetail(CustomToolType tool_type, CustomMaterialLevel lvl) {
		super(ItemDetailType.TOOL, new Enum[] { tool_type, lvl });
	}

	public CustomToolType getToolType() {
		return (CustomToolType) ((Enum[]) data)[0];
	}

	public CustomMaterialLevel getToolLevel() {
		return (CustomMaterialLevel) ((Enum[]) data)[1];
	}

}
