package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemCutSandstone extends CustomPlaceableItem {

	public ItemCutSandstone() {
		super("cut_sandstone", Material.CUT_SANDSTONE, Age.COPPER_AGE, "Cut Sandstone", ItemCategory.BUILDING, true);
	}

}
