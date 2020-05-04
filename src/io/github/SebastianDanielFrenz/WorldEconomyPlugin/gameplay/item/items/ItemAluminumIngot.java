package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemAluminumIngot extends CustomItem {

	public ItemAluminumIngot() {
		super("ALUMINUM_INGOT", Material.IRON_INGOT, Age.UNDEFINED, "Aluminum Ingot", ItemCategory.RAW_MATERIALS);
	}

}
