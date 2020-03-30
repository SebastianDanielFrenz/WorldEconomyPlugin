package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemClayBall extends CustomItem {

	public ItemClayBall() {
		super("CLAY_BALL", Material.CLAY_BALL, Tier.TIER1, "Clay Ball", ItemCategory.RAW_MATERIALS, true);
	}

}
