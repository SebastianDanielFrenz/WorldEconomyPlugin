package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemRawClayBucket extends CustomItem {

	public ItemRawClayBucket() {
		super("RAW_CLAY_BUCKET", Material.BUCKET, Tier.TIER1, "Raw Clay Bucket", ItemCategory.OTHER);
	}

}
