package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.power_grid;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemPowerCable extends CustomPlaceableItem {

	public ItemPowerCable() {
		super("power_cable", Material.IRON_FENCE, 0, Age.UNDEFINED, "Power Cable", ItemCategory.TECHNOLOGY);
	}

}
