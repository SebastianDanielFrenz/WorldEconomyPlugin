package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemSand extends CustomPlaceableItem {

	public ItemSand() {
		super("SAND", Material.SAND, Age.NEW_STONE_AGE, "Sand", ItemCategory.RAW_MATERIALS, true);
	}

}
