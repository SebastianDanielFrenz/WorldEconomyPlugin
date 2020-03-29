package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCoalOre extends CustomItem {

	public ItemCoalOre() {
		super("coal_ore", Material.COAL_ORE, Tier.TIER1, "Coal Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
