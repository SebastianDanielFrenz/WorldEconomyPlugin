package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop;

import java.util.ArrayList;
import java.util.List;

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
	 * the CustomItemStack being null.<br>
	 * <b>Take note that this method will return the first drop it finds in the
	 * array that fits the tool type and level.</b>
	 * 
	 * @param tool
	 * @param tool_lvl
	 * @return
	 */
	public List<CustomItemStack> getDrops(CustomToolType tool, CustomMaterialLevel tool_lvl) {
		for (CustomBlockDrop drop : drops) {
			if ((drop.getToolType() == tool || drop.getToolType() == CustomToolType.ALL) && tool_lvl.lvl >= drop.getMinLvl().lvl) {
				return drop.getDrop();
			}
		}
		return new ArrayList<CustomItemStack>(0);
	}

}