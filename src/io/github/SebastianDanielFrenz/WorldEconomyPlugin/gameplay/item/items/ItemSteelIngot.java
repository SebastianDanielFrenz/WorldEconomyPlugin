package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemSteelIngot extends CustomItem {

	public ItemSteelIngot() {
		super("steel_ingot", Material.IRON_INGOT, Tier.TIER3, "Steel Ingot", ItemCategory.RAW_MATERIALS);
	}

}
