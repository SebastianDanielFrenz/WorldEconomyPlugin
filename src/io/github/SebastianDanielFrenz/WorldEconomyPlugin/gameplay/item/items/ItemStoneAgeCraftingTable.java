package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemStoneAgeCraftingTable extends CustomPlaceableItem {

	public ItemStoneAgeCraftingTable() {
		super("stone_age_crafting_table", Material.CRAFTING_TABLE, Age.EARLY_STONE_AGE, "Stone Age Crafting Table", ItemCategory.MACHINES, true);
	}
}