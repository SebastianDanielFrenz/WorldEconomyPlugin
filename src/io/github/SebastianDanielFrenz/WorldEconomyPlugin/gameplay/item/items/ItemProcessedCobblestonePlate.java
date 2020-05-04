package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemProcessedCobblestonePlate extends CustomItem {

	public ItemProcessedCobblestonePlate() {
		super("PROCESSED_COBBLESTONE_PLATE", Material.PAPER, Age.COPPER_AGE, "Processed Cobblestone Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
