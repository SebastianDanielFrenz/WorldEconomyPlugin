package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemPolishedGranite extends CustomItem {

	public ItemPolishedGranite() {
		super("POLISHED_GRANITE", Material.POLISHED_GRANITE, Tier.TIER1, "Polished Granite", ItemCategory.PROCESSED_MATERIALS, true);
	}

}
