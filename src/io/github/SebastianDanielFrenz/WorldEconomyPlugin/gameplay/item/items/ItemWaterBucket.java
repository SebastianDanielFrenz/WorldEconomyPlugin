package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemWaterBucket extends CustomItem {

	public ItemWaterBucket() {
		super("WATER_BUCKET", Material.WATER_BUCKET, Age.UNDEFINED, "Water Bucket", ItemCategory.OTHER, true);
	}

}
