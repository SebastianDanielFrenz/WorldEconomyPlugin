package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemAluminumRod extends CustomItem {

	public ItemAluminumRod() {
		super("ALUMINUM_ROD", Material.STICK, Age.UNDEFINED, "Aluminum Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
