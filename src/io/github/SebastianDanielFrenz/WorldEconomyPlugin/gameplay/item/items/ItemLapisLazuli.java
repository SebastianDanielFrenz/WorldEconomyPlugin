package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemLapisLazuli extends CustomItem {

	public ItemLapisLazuli() {
		super("LAPIS_LAZULI", Material.LAPIS_LAZULI, Age.UNDEFINED, "Lapis Lazuli", ItemCategory.RAW_MATERIALS, true);
	}

}
