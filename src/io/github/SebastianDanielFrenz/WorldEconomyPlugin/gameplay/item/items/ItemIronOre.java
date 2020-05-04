package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemIronOre extends CustomItem {

	public ItemIronOre() {
		super("iron_ore", Material.IRON_ORE, Age.IRON_AGE, "Iron Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
