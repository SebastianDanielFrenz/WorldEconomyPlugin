package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemStoneAgeCraftingTable extends CustomItem {

	public ItemStoneAgeCraftingTable(String ID, Material base, Tier tier, String name, ItemCategory category, boolean vanilla) {
		super("stone_age_crafting_table", Material.CRAFTING_TABLE, Age.EARLY_STONE_AGE, "Stone Age Crafting Table", ItemCategory.MACHINES, true);
	}
}