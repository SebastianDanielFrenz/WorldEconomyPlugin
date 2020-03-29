package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemIronHelmet extends CustomItem {

	public ItemIronHelmet() {
		super("iron_helmet", Material.IRON_HELMET, Tier.TIER1, "Iron Helmet", ItemCategory.EQUIPMENT, true);
	}

}
