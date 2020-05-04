package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCopperRod extends CustomItem {

	public ItemCopperRod() {
		super("copper_rod", Material.GOLD_INGOT, Age.UNDEFINED, "Copper Rod", ItemCategory.RAW_MATERIALS);
	}

}
