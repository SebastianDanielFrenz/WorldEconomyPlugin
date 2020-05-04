package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemBronzeIngot extends CustomItem {

	public ItemBronzeIngot() {
		super("bronze_ingot", Material.IRON_INGOT, Age.UNDEFINED, "Bronze Ingot", ItemCategory.RAW_MATERIALS);
	}

}
