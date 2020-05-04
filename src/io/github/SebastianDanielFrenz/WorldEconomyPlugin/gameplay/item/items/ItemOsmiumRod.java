package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemOsmiumRod extends CustomItem {

	public ItemOsmiumRod() {
		super("OSMIUM_ROD", Material.STICK, Age.UNDEFINED, "Osmium Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
