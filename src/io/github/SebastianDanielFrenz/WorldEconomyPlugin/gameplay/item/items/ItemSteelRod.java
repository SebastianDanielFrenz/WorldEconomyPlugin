package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemSteelRod extends CustomItem {

	public ItemSteelRod() {
		super("steel_rod", Material.STICK, Tier.TIER3, "Steel Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
