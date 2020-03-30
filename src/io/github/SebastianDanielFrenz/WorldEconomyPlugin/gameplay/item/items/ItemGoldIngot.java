package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemGoldIngot extends CustomItem {

	public ItemGoldIngot() {
		super("GOLD_INGOT", Material.GOLD_INGOT, Tier.TIER2, "Gold Ingot", ItemCategory.RAW_MATERIALS, true);
	}

}
