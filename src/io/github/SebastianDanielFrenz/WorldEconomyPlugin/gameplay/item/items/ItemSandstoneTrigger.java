package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemSandstoneTrigger extends CustomPlaceableItem {

	public ItemSandstoneTrigger() {
		super("sandstone_trigger", Material.CHISELED_SANDSTONE, Tier.TIER1, "Sandstone Button", ItemCategory.BUILDING);
	}

}
