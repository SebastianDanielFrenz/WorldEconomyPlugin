package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemBronzeIngot extends CustomItem {

	public ItemBronzeIngot() {
		super("bronze_ingot", Material.IRON_INGOT, Tier.TIER1, "Bronze Ingot", ItemCategory.RAW_MATERIALS);
	}

}
