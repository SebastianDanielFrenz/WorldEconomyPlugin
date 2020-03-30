package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemGoldRod extends CustomItem {

	public ItemGoldRod() {
		super("GOLD_ROD", Material.STICK, Tier.TIER2, "Gold Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
