package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemTinPlate extends CustomItem {

	public ItemTinPlate() {
		super("TIN_PLATE", Material.PAPER, Age.UNDEFINED, "Tin Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
