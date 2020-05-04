package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemTinRod extends CustomItem {

	public ItemTinRod() {
		super("TIN_ROD", Material.STICK, Age.UNDEFINED, "Tin Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
