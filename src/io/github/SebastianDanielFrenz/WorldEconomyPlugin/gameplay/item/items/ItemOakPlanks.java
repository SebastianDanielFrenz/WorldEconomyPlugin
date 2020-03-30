package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOakPlanks extends CustomItem {

	public ItemOakPlanks() {
		super("OAK_PLANKS", Material.OAK_PLANKS, Tier.TIER1, "Oak Planks", ItemCategory.OTHER, true);
	}

}
