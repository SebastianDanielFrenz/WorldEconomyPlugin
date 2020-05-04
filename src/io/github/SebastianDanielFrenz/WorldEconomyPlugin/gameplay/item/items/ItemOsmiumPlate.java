package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemOsmiumPlate extends CustomItem {

	public ItemOsmiumPlate() {
		super("OSMIUM_PLATE", Material.PAPER, Age.UNDEFINED, "Osmium Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
