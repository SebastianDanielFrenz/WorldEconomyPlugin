package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemLeadIngot extends CustomItem {

	public ItemLeadIngot() {
		super("LEAD_INGOT", Material.IRON_INGOT, Tier.TIER1, "Lead Ingot", ItemCategory.RAW_MATERIALS);
	}

}
