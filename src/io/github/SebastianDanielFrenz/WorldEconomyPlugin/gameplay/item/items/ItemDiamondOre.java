package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemDiamondOre extends CustomItem {

	public ItemDiamondOre() {
		super("DIAMOND_ORE", Material.DIAMOND_ORE, Age.UNDEFINED, "Diamond Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
