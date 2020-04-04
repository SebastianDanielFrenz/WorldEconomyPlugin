package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOakLeaves extends CustomItem {

	public ItemOakLeaves() {
		super("oak_leaves", Material.OAK_LEAVES, Tier.TIER1, "Oak Leaves", ItemCategory.RAW_MATERIALS, true);
	}

}
