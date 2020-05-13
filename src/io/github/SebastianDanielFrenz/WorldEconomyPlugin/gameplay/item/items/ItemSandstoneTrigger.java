package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemSandstoneTrigger extends CustomPlaceableItem {

	public ItemSandstoneTrigger() {
		super("sandstone_trigger", Material.SANDSTONE, 1, Age.UNDEFINED, "Sandstone Button", ItemCategory.BUILDING);
	}

}
