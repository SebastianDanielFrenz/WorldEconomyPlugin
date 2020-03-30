package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemBronzePlate extends CustomItem {

	public ItemBronzePlate() {
		super("bronze_plate", Material.PAPER, Tier.TIER1, "Bronze Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
