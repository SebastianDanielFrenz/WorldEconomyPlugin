package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemBerries extends CustomItem {

	public ItemBerries() {
		super("BERRIES", Material.SWEET_BERRIES, Tier.TIER1, "Berries", ItemCategory.FOOD, true);
	}

}
