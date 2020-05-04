package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemOsmiumIngot extends CustomItem {

	public ItemOsmiumIngot() {
		super("OSMIUM_INGOT", Material.IRON_INGOT, Age.UNDEFINED, "Osmium Ingot", ItemCategory.RAW_MATERIALS);
	}

}
