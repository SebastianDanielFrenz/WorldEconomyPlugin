package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemLeadPlate extends CustomItem {

	public ItemLeadPlate() {
		super("LEAD_PLATE", Material.PAPER, Tier.TIER1, "Lead Plate", ItemCategory.PROCESSED_MATERIALS);
	}

}
