package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemLeadRod extends CustomItem {

	public ItemLeadRod() {
		super("LEAD_ROD", Material.STICK, Tier.TIER1, "Lead Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
