package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCopperIngot extends CustomItem {

	public ItemCopperIngot() {
		super("copper_ingot", Material.GOLD_INGOT, Tier.TIER1, "Copper Ingot", ItemCategory.RAW_MATERIALS);
	}

}
