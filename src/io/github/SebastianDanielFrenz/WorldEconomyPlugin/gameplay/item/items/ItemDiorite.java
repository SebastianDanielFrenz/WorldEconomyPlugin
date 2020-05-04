package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemDiorite extends CustomPlaceableItem {

	public ItemDiorite() {
		super("DIORITE", Material.DIORITE, Age.COPPER_AGE, "Diorite", ItemCategory.RAW_MATERIALS, true);
	}

}
