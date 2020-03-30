package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemLapisLazuli extends CustomItem {

	public ItemLapisLazuli() {
		super("LAPIS_LAZULI", Material.LAPIS_LAZULI, Tier.TIER1, "Lapis Lazuli", ItemCategory.RAW_MATERIALS, true);
	}

}
