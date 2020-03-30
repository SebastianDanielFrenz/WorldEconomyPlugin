package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCobblestone extends CustomItem {

	public ItemCobblestone() {
		super("COBBLESTONE", Material.COBBLESTONE, Tier.TIER1, "Cobblestone", ItemCategory.OTHER, true);
	}

}
