package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemGoldRod extends CustomItem {

	public ItemGoldRod() {
		super("GOLD_ROD", Material.STICK, Age.UNDEFINED, "Gold Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
