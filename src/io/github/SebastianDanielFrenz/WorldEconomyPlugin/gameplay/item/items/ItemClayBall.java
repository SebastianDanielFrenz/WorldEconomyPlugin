package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemClayBall extends CustomItem {

	public ItemClayBall() {
		super("CLAY_BALL", Material.CLAY_BALL, Age.COPPER_AGE, "Clay Ball", ItemCategory.RAW_MATERIALS, true);
	}

}
