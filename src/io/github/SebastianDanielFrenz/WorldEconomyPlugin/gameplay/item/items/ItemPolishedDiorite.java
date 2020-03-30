package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemPolishedDiorite extends CustomItem {

	public ItemPolishedDiorite() {
		super("POLISHED_DIORITE", Material.POLISHED_DIORITE, Tier.TIER1, "Polished Diorite", ItemCategory.PROCESSED_MATERIALS, true);
	}

}
