package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemGoldCable extends CustomItem {

	public ItemGoldCable() {
		super("GOLD_CABLE", Material.GOLD_NUGGET, Age.UNDEFINED, "Gold Cable", ItemCategory.TECHNOLOGY);
	}

}
