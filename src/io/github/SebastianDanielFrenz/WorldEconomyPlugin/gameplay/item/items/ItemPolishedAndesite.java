package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemPolishedAndesite extends CustomItem {

	public ItemPolishedAndesite() {
		super("POLISHED_ANDESITE", Material.STONE, 6, Age.UNDEFINED, "Polished Andesite", ItemCategory.PROCESSED_MATERIALS,true);
	}

}
