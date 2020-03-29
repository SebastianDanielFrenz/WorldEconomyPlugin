package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemRedstone extends CustomItem {

	public ItemRedstone() {
		super("redstone", Material.REDSTONE, Tier.TIER1, "Redstone", ItemCategory.TECHNOLOGY, true);
	}

}
