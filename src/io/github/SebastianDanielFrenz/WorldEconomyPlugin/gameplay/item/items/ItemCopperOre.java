package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCopperOre extends CustomItem {

	public ItemCopperOre() {
		super("copper_ore", Material.GOLD_ORE, Tier.TIER1, "Copper Ore", ItemCategory.RAW_MATERIALS);
	}

}
