package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemPolishedAndesite extends CustomItem {

	public ItemPolishedAndesite() {
		super("POLISHED_ANDESITE", Material.POLISHED_ANDESITE, Tier.TIER1, "Polished Andesite", ItemCategory.PROCESSED_MATERIALS,true);
	}

}
