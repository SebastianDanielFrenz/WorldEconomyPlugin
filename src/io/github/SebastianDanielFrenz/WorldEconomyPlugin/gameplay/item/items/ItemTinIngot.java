package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemTinIngot extends CustomItem {

	public ItemTinIngot() {
		super("TIN_INGOT", Material.IRON_INGOT, Age.UNDEFINED, "Tin Ingot", ItemCategory.RAW_MATERIALS);
	}

}
