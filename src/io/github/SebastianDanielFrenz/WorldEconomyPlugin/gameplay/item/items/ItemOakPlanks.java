package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemOakPlanks extends CustomItem {

	public ItemOakPlanks() {
		super("OAK_PLANKS", Material.WOOD, Age.EARLY_STONE_AGE, "Oak Planks", ItemCategory.OTHER, true);
	}

}
