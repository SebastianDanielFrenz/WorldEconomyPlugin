package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemGoldOre extends CustomItem {

	public ItemGoldOre() {
		super("GOLD_ORE", Material.GOLD_ORE, Age.UNDEFINED, "Gold Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
