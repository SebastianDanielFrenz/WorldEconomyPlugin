package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemSteelPlate extends CustomItem {

	public ItemSteelPlate() {
		super("steel_plate", Material.PAPER, Tier.TIER3, "Steel Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
