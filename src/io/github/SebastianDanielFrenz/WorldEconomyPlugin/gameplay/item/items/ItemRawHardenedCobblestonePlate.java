package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemRawHardenedCobblestonePlate extends CustomItem {

	public ItemRawHardenedCobblestonePlate() {
		super("RAW_HARDENED_COBBLESTONE_PLATE", Material.PAPER, Tier.TIER1, "Raw Hardened Cobblestone Plate",ItemCategory.PROCESSED_MATERIALS);
	}

}
