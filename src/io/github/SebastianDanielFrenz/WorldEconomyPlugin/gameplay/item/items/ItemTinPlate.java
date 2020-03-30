package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemTinPlate extends CustomItem {

	public ItemTinPlate() {
		super("TIN_PLATE", Material.PAPER, Tier.TIER1, "Tin Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
