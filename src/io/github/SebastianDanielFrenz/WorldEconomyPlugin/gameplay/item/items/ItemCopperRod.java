package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCopperRod extends CustomItem {

	public ItemCopperRod() {
		super("copper_rod", Material.GOLD_INGOT, Tier.TIER1, "Copper Rod", ItemCategory.RAW_MATERIALS);
	}

}
