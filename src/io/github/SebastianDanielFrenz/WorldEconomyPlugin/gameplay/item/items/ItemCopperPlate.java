package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCopperPlate extends CustomItem {

	public ItemCopperPlate() {
		super("copper_plate", Material.PAPER, Tier.TIER1, "Copper Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
