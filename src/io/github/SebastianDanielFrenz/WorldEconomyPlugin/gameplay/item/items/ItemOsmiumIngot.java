package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOsmiumIngot extends CustomItem {

	public ItemOsmiumIngot() {
		super("OSMIUM_INGOT", Material.IRON_INGOT, Tier.TIER1, "Osmium Ingot", ItemCategory.RAW_MATERIALS);
	}

}
