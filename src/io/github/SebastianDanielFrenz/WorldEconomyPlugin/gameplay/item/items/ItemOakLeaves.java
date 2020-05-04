package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemOakLeaves extends CustomPlaceableItem {

	public ItemOakLeaves() {
		super("oak_leaves", Material.OAK_LEAVES, Age.EARLY_STONE_AGE, "Oak Leaves", ItemCategory.RAW_MATERIALS, true);
	}

}
