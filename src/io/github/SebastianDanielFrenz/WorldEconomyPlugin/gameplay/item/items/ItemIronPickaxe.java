package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ToolItemDetail;

public class ItemIronPickaxe extends CustomItem {

	public ItemIronPickaxe() {
		super("iron_pickaxe", Material.IRON_PICKAXE, Tier.TIER1, "Iron Pickaxe", ItemCategory.EQUIPMENT,
				new ItemDetail[] { new ToolItemDetail(CustomToolType.PICKAXE, CustomMaterialLevel.IRON) }, true);
	}

}
