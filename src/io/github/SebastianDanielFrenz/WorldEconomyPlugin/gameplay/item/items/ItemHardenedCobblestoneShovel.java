package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ToolItemDetail;

public class ItemHardenedCobblestoneShovel extends CustomItem {

	public ItemHardenedCobblestoneShovel() {
		super("HARDENED_COBBLESTONE_SHOVEL", Material.STONE_SHOVEL, Tier.TIER1, "Hardened Cobblestone Shovel",
				ItemCategory.EQUIPMENT, new ItemDetail[] {
						new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.HARDENED_COBBLESTONE) });
	}

}
