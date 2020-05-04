package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemStick extends CustomItem {

	public ItemStick() {
		super("STICK", Material.STICK, Age.EARLY_STONE_AGE, "Stick", ItemCategory.PROCESSED_MATERIALS, true);
	}

}
