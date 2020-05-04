package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemAluminumPlate extends CustomItem {

	public ItemAluminumPlate() {
		super("ALUMINUM_PLATE", Material.PAPER, Age.UNDEFINED, "Aluminum Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
