package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemHardenedCobblestone extends CustomItem {

	public ItemHardenedCobblestone() {
		super("HARDENED_COBBLESTONE", Material.COBBLESTONE, Age.COPPER_AGE, "Hardened Cobblestone", ItemCategory.PROCESSED_MATERIALS);
	}

}
