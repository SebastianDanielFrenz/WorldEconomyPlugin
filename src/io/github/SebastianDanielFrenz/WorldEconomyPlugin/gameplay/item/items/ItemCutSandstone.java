package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemCutSandstone extends CustomPlaceableItem {

	public ItemCutSandstone() {
		super("cut_sandstone", Material.CUT_SANDSTONE, Tier.TIER1, "Cut Sandstone", ItemCategory.BUILDING, true);
	}

}
