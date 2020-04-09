package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCampfire extends CustomPlaceableItem {

	public ItemCampfire() {
		super("campfire", Material.CAMPFIRE, Tier.TIER1, "Campfire", ItemCategory.MACHINES, true);
	}

}
