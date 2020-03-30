package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemOakLog extends CustomItem {

	public ItemOakLog() {
		super("OAK_LOG", Material.OAK_LOG, Tier.TIER1, "Oak Log", ItemCategory.OTHER, true);
	}

}
