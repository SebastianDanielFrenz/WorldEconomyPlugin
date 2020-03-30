package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemGoldCable extends CustomItem {

	public ItemGoldCable() {
		super("GOLD_CABLE", Material.GOLD_NUGGET, Tier.TIER2, "Gold Cable", ItemCategory.TECHNOLOGY);
	}

}
