package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCharcoal extends CustomItem {

	public ItemCharcoal() {
		super("charcoal", Material.CHARCOAL, Age.UNDEFINED, "Charcoal", ItemCategory.RAW_MATERIALS, true);
	}

}
