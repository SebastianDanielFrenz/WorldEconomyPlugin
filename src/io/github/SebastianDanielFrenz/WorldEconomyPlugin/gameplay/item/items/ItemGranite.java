package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemGranite extends CustomPlaceableItem {

	public ItemGranite() {
		super("GRANITE", Material.GRANITE, Age.COPPER_AGE, "Granite", ItemCategory.RAW_MATERIALS, true);
	}

}
