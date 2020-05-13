package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ToolItemDetail;

public class ItemHardenedCobblestoneShovel extends CustomItem {

	public ItemHardenedCobblestoneShovel() {
		super("HARDENED_COBBLESTONE_SHOVEL", Material.STONE_SPADE, Age.COPPER_AGE, "Hardened Cobblestone Shovel", ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.HARDENED_COBBLESTONE) });
	}

}
