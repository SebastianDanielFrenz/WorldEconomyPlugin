package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemBronzeRod extends CustomItem {

	public ItemBronzeRod() {
		super("BRONZE_ROD", Material.STICK, Age.UNDEFINED, "Bronze Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
