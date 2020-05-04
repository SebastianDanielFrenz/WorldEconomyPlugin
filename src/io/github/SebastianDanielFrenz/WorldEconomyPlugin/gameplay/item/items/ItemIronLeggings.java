package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemIronLeggings extends CustomItem {

	public ItemIronLeggings() {
		super("iron_leggings", Material.IRON_LEGGINGS, Age.IRON_AGE, "Iron Leggings", ItemCategory.EQUIPMENT, true);
	}

}
