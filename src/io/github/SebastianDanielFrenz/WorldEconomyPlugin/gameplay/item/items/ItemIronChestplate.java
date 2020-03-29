package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemIronChestplate extends CustomItem {

	public ItemIronChestplate() {
		super("iron_chestplate", Material.IRON_CHESTPLATE, Tier.TIER1, "Iron Chestplate", ItemCategory.EQUIPMENT, true);
	}

}
