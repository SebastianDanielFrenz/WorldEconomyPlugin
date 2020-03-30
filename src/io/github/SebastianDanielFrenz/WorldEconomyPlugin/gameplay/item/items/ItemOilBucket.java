package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOilBucket extends CustomItem {

	public ItemOilBucket() {
		super("OIL_BUCKET", Material.LAVA_BUCKET, Tier.TIER1, "Oil Bucket", ItemCategory.RAW_MATERIALS);
	}

}
