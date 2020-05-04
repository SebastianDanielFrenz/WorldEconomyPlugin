package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemLapisLazuliOre extends CustomItem {

	public ItemLapisLazuliOre() {
		super("LAPIS_LAZULI_ORE", Material.LAPIS_ORE, Age.UNDEFINED, "Lapis Lazuli Ore", ItemCategory.RAW_MATERIALS, true);
	}

}
