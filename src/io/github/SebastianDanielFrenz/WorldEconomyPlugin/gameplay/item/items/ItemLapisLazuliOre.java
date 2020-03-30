package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemLapisLazuliOre extends CustomItem {

	public ItemLapisLazuliOre() {
		super("LAPIS_LAZULI_ORE", Material.LAPIS_ORE, Tier.TIER1, "Lapis Lazuli Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
