package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemGrassBlock extends CustomItem {

	public ItemGrassBlock() {
		super("GRASS_BLOCK", Material.GRASS_BLOCK, Tier.TIER1, "Grass Block", ItemCategory.OTHER, true);
	}

}
