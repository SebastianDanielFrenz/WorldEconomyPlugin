package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemAndesite extends CustomItem {

	public ItemAndesite() {
		super("ANDESITE", Material.ANDESITE, Tier.TIER1, "Andesite", ItemCategory.RAW_MATERIALS, true);
	}

}
