package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemIronOre extends CustomItem {

	public ItemIronOre() {
		super("iron_ore", Material.IRON_ORE, Tier.TIER1, "Iron Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
