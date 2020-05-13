package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemStoneAgeCampfire extends CustomPlaceableItem {

	public ItemStoneAgeCampfire() {
		super("stone_age_campfire", Material.WOOD_STEP, Age.MID_STONE_AGE, "Stone Age Campfire", ItemCategory.MACHINES,
				true);
	}

}
