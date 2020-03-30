package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemBronzeRod extends CustomItem {

	public ItemBronzeRod() {
		super("BRONZE_ROD", Material.STICK, Tier.TIER1, "Bronze Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
