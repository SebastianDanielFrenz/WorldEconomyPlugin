package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemClayBucket extends CustomItem {

	public ItemClayBucket() {
		super("CLAY_BUCKET", Material.BUCKET, Age.COPPER_AGE, "Clay Bucket", ItemCategory.OTHER);
	}

}
