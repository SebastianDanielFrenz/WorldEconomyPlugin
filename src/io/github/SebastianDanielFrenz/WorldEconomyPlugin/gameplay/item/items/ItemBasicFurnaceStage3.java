package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage3;

public class ItemBasicFurnaceStage3 extends CustomItem {

	public ItemBasicFurnaceStage3() {
		super("BASIC_FURNACE_STAGE3", new BasicFurnaceStage3(null).getKategory().display, Tier.TIER1, "Basic Furnace Stage 3",ItemCategory.MACHINES);
	}

}
