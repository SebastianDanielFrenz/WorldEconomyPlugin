package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemIronPlate extends CustomItem {

	public ItemIronPlate() {
		super("iron_plate", Material.PAPER, Age.IRON_AGE, "Iron Plate", ItemCategory.PROCESSED_MATERIALS, true);
	}

}
