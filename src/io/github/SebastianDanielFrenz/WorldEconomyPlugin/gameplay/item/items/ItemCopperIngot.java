package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCopperIngot extends CustomItem {

	public ItemCopperIngot() {
		super("copper_ingot", Material.GOLD_INGOT, Age.COPPER_AGE, "Copper Ingot", ItemCategory.RAW_MATERIALS);
	}

}
