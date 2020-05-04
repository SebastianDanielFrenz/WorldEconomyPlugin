package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCoarseDirt extends CustomPlaceableItem {

	public ItemCoarseDirt() {
		super("COARSE_DIRT", Material.COARSE_DIRT, Age.MID_STONE_AGE, "Coarse Dirt", ItemCategory.OTHER, true);
	}

}
