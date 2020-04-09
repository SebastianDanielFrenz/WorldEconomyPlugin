package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCoarseDirt extends CustomPlaceableItem {

	public ItemCoarseDirt() {
		super("COARSE_DIRT", Material.COARSE_DIRT, Tier.TIER1, "Coarse Dirt", ItemCategory.OTHER, true);
	}

}
