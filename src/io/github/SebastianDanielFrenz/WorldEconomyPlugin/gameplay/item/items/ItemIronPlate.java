package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemIronPlate extends CustomItem {

	public ItemIronPlate() {
		super("iron_plate", Material.PAPER, Tier.TIER1, "Iron Plate", ItemCategory.PROCESSED_MATERIALS, true);
	}

}
