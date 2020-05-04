package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemRawClayBucket extends CustomItem {

	public ItemRawClayBucket() {
		super("RAW_CLAY_BUCKET", Material.BUCKET, Age.COPPER_AGE, "Raw Clay Bucket", ItemCategory.OTHER);
	}

}
