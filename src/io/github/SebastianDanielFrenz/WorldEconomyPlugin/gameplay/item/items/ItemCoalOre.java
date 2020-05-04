package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCoalOre extends CustomItem {

	public ItemCoalOre() {
		super("coal_ore", Material.COAL_ORE, Age.COPPER_AGE, "Coal Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
