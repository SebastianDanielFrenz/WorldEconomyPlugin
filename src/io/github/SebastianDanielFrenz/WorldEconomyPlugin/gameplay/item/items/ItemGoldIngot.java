package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemGoldIngot extends CustomItem {

	public ItemGoldIngot() {
		super("GOLD_INGOT", Material.GOLD_INGOT, Age.UNDEFINED, "Gold Ingot", ItemCategory.RAW_MATERIALS, true);
	}

}
