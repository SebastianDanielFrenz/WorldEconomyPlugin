package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemStoneAgeCampfire extends CustomItem {

	public ItemStoneAgeCampfire() {
		super("stone_age_campfire", Material.CAMPFIRE, Tier.TIER1, "Stone Age Campfire", ItemCategory.MACHINES, true);
	}

}
