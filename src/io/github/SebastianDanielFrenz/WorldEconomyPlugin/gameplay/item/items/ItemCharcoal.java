package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCharcoal extends CustomItem {

	public ItemCharcoal() {
		super("charcoal", Material.CHARCOAL, Tier.TIER1, "Charcoal", ItemCategory.RAW_MATERIALS, true);
	}

}
