package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves.BasicSieveStage1;

public class ItemBasicSieveStage1 extends CustomItem {

	public ItemBasicSieveStage1() {
		super("BASIC_SIEVE_STAGE1", new BasicSieveStage1(null).getKategory().display, Tier.TIER1, "Basic Sieve Stage 1",ItemCategory.MACHINES);
	}

}
