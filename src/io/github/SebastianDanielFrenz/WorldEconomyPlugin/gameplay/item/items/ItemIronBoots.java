package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemIronBoots extends CustomItem {

	public ItemIronBoots() {
		super("iron_boots", Material.IRON_BOOTS, Age.IRON_AGE, "Iron Boots", ItemCategory.EQUIPMENT, true);
	}

}
