package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemIronChestplate extends CustomItem {

	public ItemIronChestplate() {
		super("iron_chestplate", Material.IRON_CHESTPLATE, Age.IRON_AGE, "Iron Chestplate", ItemCategory.EQUIPMENT, true);
	}

}