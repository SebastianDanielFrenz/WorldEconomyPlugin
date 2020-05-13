package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ToolItemDetail;

public class ItemWoodenShovel extends CustomItem {

	public ItemWoodenShovel() {
		super("WOODEN_SHOVEL", Material.WOOD_SPADE, Age.NEW_STONE_AGE, "Wooden Shovel", ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.WOOD) });
	}

}
