package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemAndesite extends CustomPlaceableItem {

	public ItemAndesite() {
		super("ANDESITE", Material.STONE, 5, Age.COPPER_AGE, "Andesite", ItemCategory.RAW_MATERIALS, true);
	}

}
