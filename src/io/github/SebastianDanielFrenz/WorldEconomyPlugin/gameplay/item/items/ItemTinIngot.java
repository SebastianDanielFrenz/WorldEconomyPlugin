package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemTinIngot extends CustomItem {

	public ItemTinIngot() {
		super("TIN_INGOT", Material.IRON_INGOT, Tier.TIER1, "Tin Ingot", ItemCategory.RAW_MATERIALS);
	}

}
