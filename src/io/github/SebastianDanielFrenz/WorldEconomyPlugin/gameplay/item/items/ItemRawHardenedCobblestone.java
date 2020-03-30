package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemRawHardenedCobblestone extends CustomItem {

	public ItemRawHardenedCobblestone() {
		super("RAW_HARDENED_COBBLESTONE", Material.COBBLESTONE, Tier.TIER1, "Raw Hardened Cobblestone",ItemCategory.PROCESSED_MATERIALS);
	}

}
