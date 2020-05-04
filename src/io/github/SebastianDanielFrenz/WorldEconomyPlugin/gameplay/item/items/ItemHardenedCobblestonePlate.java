package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemHardenedCobblestonePlate extends CustomItem {

	public ItemHardenedCobblestonePlate() {
		super("HARDENED_COBBLESTONE_PLATE", Material.PAPER, Age.COPPER_AGE, "Hardened Cobblestone Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
