package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemWaterClayBucket extends CustomItem {

	public ItemWaterClayBucket() {
		super("WATER_CLAY_BUCKET", Material.WATER_BUCKET, Age.COPPER_AGE, "Water Clay Bucket", ItemCategory.OTHER);
	}

}
