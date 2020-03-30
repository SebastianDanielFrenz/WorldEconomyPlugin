package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemAluminumPlate extends CustomItem {

	public ItemAluminumPlate() {
		super("ALUMINUM_PLATE", Material.PAPER, Tier.TIER1, "Aluminum Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
