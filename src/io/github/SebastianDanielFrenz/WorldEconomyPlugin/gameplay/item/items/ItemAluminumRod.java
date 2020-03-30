package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemAluminumRod extends CustomItem {

	public ItemAluminumRod() {
		super("ALUMINUM_ROD", Material.STICK, Tier.TIER1, "Aluminum Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
