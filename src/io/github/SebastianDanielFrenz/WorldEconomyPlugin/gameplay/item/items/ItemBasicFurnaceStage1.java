package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage1;

public class ItemBasicFurnaceStage1 extends CustomItem {

	public ItemBasicFurnaceStage1() {
		super("BASIC_FURNACE_STAGE1", new BasicFurnaceStage1(null).getCategory().display, Tier.TIER1, "Basic Furnace Stage 1",ItemCategory.MACHINES);
	}

}
