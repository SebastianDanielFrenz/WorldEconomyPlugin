package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemGoldNugget extends CustomItem {

	public ItemGoldNugget() {
		super("GOLD_NUGGET", Material.GOLD_NUGGET, Age.UNDEFINED, "Gold Nugget", ItemCategory.RAW_MATERIALS, true);
	}

}
