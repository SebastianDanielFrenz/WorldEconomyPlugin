package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemProcessedCobblestone extends CustomItem {

	public ItemProcessedCobblestone() {
		super("PROCESSED_COBBLESTONE", Material.STONE, Tier.TIER1, "Processed Cobblestone", ItemCategory.PROCESSED_MATERIALS);
	}

}
