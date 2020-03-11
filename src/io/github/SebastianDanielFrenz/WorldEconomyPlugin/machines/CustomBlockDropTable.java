package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItemStack;

public class CustomBlockDropTable {

	public CustomBlockDropTable(final CustomBlockDrop[] drops) {
		this.drops = drops;
	}

	public final CustomBlockDrop[] drops;

	public CustomItemStack[] getDrops(CustomToolType tool, CustomMaterialLevel tool_lvl) {
		for (CustomBlockDrop drop : drops) {
			if ((drop.tool == tool || drop.tool == CustomToolType.ALL) && tool_lvl.lvl >= drop.min_lvl.lvl) {
				return drop.drop;
			}
		}
		return new CustomItemStack[] {};
	}

}