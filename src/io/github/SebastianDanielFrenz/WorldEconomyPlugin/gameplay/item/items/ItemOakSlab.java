package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOakSlab extends CustomItem {

	public ItemOakSlab() {
		super("oak_slab", Material.OAK_SLAB, Tier.TIER1, "Oak Slab", ItemCategory.BUILDING, true);
	}

}
