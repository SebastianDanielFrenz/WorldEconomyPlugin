package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemOilBucket extends CustomItem {

	public ItemOilBucket() {
		super("OIL_BUCKET", Material.LAVA_BUCKET, Age.UNDEFINED, "Oil Bucket", ItemCategory.RAW_MATERIALS);
	}

}
