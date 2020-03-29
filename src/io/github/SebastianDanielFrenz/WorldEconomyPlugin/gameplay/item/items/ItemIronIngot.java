package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemIronIngot extends CustomItem {

	public ItemIronIngot() {
		super("iron_ingot", Material.IRON_INGOT, Tier.TIER1, "Iron Ingot", ItemCategory.RAW_MATERIALS, true);
	}

}
