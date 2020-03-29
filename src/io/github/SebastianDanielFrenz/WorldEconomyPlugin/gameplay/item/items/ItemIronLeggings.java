package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemIronLeggings extends CustomItem {

	public ItemIronLeggings() {
		super("iron_leggings", Material.IRON_LEGGINGS, Tier.TIER1, "Iron Leggings", ItemCategory.EQUIPMENT, true);
	}

}
