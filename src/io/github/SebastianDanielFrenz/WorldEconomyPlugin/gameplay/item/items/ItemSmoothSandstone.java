package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemSmoothSandstone extends CustomPlaceableItem {

	public ItemSmoothSandstone() {
		super("smooth_sandstone", Material.SMOOTH_SANDSTONE, Tier.TIER1, "Smooth Sandstone", ItemCategory.BUILDING,
				true);
	}

}
