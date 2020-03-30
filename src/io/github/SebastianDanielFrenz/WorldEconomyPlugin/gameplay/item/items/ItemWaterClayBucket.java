package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemWaterClayBucket extends CustomItem {

	public ItemWaterClayBucket() {
		super("WATER_CLAY_BUCKET", Material.WATER_BUCKET, Tier.TIER1, "Water Clay Bucket", ItemCategory.OTHER);
	}

}
