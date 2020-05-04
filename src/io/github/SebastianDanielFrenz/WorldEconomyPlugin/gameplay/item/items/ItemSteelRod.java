package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemSteelRod extends CustomItem {

	public ItemSteelRod() {
		super("steel_rod", Material.STICK, Age.UNDEFINED, "Steel Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
