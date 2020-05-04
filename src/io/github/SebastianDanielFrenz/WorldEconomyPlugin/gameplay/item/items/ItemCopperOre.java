package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCopperOre extends CustomPlaceableItem {

	public ItemCopperOre() {
		super("copper_ore", Material.GOLD_ORE, Age.COPPER_AGE, "Copper Ore", ItemCategory.RAW_MATERIALS);
	}

}
