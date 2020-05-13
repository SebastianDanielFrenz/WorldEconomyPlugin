package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemPolishedGranite extends CustomItem {

	public ItemPolishedGranite() {
		super("POLISHED_GRANITE", Material.STONE, 2, Age.UNDEFINED, "Polished Granite", ItemCategory.PROCESSED_MATERIALS, true);
	}

}
