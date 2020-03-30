package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemDiorite extends CustomItem {

	public ItemDiorite() {
		super("DIORITE", Material.DIORITE, Tier.TIER1, "Diorite", ItemCategory.RAW_MATERIALS, true);
	}

}
