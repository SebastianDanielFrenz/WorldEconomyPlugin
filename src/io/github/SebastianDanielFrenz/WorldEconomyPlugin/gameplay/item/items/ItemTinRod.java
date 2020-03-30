package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemTinRod extends CustomItem {

	public ItemTinRod() {
		super("TIN_ROD", Material.STICK, Tier.TIER1, "Tin Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
