package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCopperPlate extends CustomItem {

	public ItemCopperPlate() {
		super("copper_plate", Material.PAPER, Age.UNDEFINED, "Copper Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
