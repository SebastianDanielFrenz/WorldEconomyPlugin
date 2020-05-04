package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemSteelIngot extends CustomItem {

	public ItemSteelIngot() {
		super("steel_ingot", Material.IRON_INGOT, Age.UNDEFINED, "Steel Ingot", ItemCategory.RAW_MATERIALS);
	}

}
