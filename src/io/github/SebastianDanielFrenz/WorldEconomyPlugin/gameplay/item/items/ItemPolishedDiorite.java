package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemPolishedDiorite extends CustomItem {

	public ItemPolishedDiorite() {
		super("POLISHED_DIORITE", Material.STONE, 4, Age.UNDEFINED, "Polished Diorite", ItemCategory.PROCESSED_MATERIALS, true);
	}

}
