package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;

public class CustomBlockDropTable {

	public CustomBlockDropTable(final CustomBlockDrop[] drops) {
		this.drops = drops;
	}

	public CustomBlockDropTable(final CustomBlockDrop drop) {
		drops = new CustomBlockDrop[] { drop };
	}

	public final CustomBlockDrop[] drops;

	/**
	 * If no block drop is found for the tool, it will not break. In order to
	 * have a block without drops, you need to register a CustomBlockDrop with
	 * the CustomItemStack being null.
	 * 
	 * @param tool
	 * @param tool_lvl
	 * @return
	 */
	public CustomItemStack[] getDrops(CustomToolType tool, CustomMaterialLevel tool_lvl) {
		for (CustomBlockDrop drop : drops) {
			if ((drop.tool == tool || drop.tool == CustomToolType.ALL) && tool_lvl.lvl >= drop.min_lvl.lvl) {
				return drop.drop;
			}
		}
		return new CustomItemStack[] {};
	}

}