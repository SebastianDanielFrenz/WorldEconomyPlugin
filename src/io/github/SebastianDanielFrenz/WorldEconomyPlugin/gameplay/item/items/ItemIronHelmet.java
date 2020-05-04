package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemIronHelmet extends CustomItem {

	public ItemIronHelmet() {
		super("iron_helmet", Material.IRON_HELMET, Age.IRON_AGE, "Iron Helmet", ItemCategory.EQUIPMENT, true);
	}

}
