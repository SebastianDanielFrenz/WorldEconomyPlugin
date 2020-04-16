package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemAndesite extends CustomPlaceableItem {

	public ItemAndesite() {
		super("ANDESITE", Material.ANDESITE, Tier.TIER1, "Andesite", ItemCategory.RAW_MATERIALS, true);
	}

}