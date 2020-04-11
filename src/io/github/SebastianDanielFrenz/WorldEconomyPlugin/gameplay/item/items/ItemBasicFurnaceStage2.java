package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage2;

public class ItemBasicFurnaceStage2 extends CustomItem {

	public ItemBasicFurnaceStage2() {
		super("BASIC_FURNACE_STAGE2", new BasicFurnaceStage2(null).getCategory().display, Tier.TIER1, "Basic Furnace Stage 2",ItemCategory.MACHINES);
	}

}
