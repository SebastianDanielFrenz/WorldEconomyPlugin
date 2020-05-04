package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemProcessedCobblestone extends CustomItem {

	public ItemProcessedCobblestone() {
		super("PROCESSED_COBBLESTONE", Material.STONE, Age.COPPER_AGE, "Processed Cobblestone", ItemCategory.PROCESSED_MATERIALS);
	}

}
