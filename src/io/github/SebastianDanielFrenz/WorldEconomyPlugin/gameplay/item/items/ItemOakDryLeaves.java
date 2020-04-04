package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOakDryLeaves extends CustomItem {

	public ItemOakDryLeaves() {
		super("oak_dry_leaves", Material.OAK_LEAVES, Tier.TIER1, "Dry Oak Leaves", ItemCategory.RAW_MATERIALS);
	}

}
