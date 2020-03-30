package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOsmiumRod extends CustomItem {

	public ItemOsmiumRod() {
		super("OSMIUM_ROD", Material.STICK, Tier.TIER1, "Osmium Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
