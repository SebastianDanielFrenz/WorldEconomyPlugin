package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemIronBoots extends CustomItem {

	public ItemIronBoots() {
		super("iron_boots", Material.IRON_BOOTS, Tier.TIER1, "Iron Boots", ItemCategory.EQUIPMENT, true);
	}

}
