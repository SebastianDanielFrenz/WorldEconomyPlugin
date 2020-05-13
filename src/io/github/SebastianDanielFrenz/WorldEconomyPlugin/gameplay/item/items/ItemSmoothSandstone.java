package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemSmoothSandstone extends CustomPlaceableItem {

	public ItemSmoothSandstone() {
		super("smooth_sandstone", Material.SANDSTONE, 2, Age.COPPER_AGE, "Smooth Sandstone", ItemCategory.BUILDING,
				true);
	}

}
