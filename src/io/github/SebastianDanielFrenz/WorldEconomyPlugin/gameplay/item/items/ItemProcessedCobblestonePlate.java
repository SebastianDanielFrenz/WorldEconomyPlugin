package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemProcessedCobblestonePlate extends CustomItem {

	public ItemProcessedCobblestonePlate() {
		super("PROCESSED_COBBLESTONE_PLATE", Material.PAPER, Tier.TIER1, "Processed Cobblestone Plate",ItemCategory.PROCESSED_MATERIALS);
	}

}
