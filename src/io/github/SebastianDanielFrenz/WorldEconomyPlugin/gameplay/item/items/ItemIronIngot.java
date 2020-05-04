package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemIronIngot extends CustomItem {

	public ItemIronIngot() {
		super("iron_ingot", Material.IRON_INGOT, Age.IRON_AGE, "Iron Ingot", ItemCategory.RAW_MATERIALS, true);
	}

}
