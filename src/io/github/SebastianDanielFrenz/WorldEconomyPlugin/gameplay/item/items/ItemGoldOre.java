package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemGoldOre extends CustomItem {

	public ItemGoldOre() {
		super("GOLD_ORE", Material.GOLD_ORE, Tier.TIER2, "Gold Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
