package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCobblestone extends CustomItem {

	public ItemCobblestone() {
		super("COBBLESTONE", Material.COBBLESTONE, Age.MID_STONE_AGE, "Cobblestone", ItemCategory.OTHER, true);
	}

}
