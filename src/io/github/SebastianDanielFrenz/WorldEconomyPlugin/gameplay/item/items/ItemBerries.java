package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemBerries extends CustomItem {

	public ItemBerries() {
		super("BERRIES", Material.SWEET_BERRIES, Age.EARLY_STONE_AGE, "Berries", ItemCategory.FOOD, true);
	}

}
