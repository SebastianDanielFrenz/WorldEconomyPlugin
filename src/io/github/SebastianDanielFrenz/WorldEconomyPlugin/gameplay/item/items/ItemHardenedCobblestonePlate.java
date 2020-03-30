package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemHardenedCobblestonePlate extends CustomItem {

	public ItemHardenedCobblestonePlate() {
		super("HARDENED_COBBLESTONE_PLATE", Material.PAPER, Tier.TIER1, "Hardened Cobblestone Plate",ItemCategory.PROCESSED_MATERIALS);
	}

}
