package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOsmiumPlate extends CustomItem {

	public ItemOsmiumPlate() {
		super("OSMIUM_PLATE", Material.PAPER, Tier.TIER1, "Osmium Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
