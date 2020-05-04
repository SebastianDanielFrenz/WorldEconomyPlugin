package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemSteelPlate extends CustomItem {

	public ItemSteelPlate() {
		super("steel_plate", Material.PAPER, Age.UNDEFINED, "Steel Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
