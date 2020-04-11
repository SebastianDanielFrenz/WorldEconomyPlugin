package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces.BasicBlastFurnaceStage1;

public class ItemBasicBlastFurnaceStage1 extends CustomItem {

	public ItemBasicBlastFurnaceStage1() {
		super("BASIC_BLAST_FURNACE_STAGE1", new BasicBlastFurnaceStage1(null).getCategory().display, Tier.TIER1,"Basic Blast Furnace Stage 1", ItemCategory.MACHINES);
	}

}
