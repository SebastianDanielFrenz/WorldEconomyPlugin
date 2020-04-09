package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.Tier;

public class ItemEgyptianCampfireStage1 extends CustomPlaceableItem {

	public ItemEgyptianCampfireStage1() {
		super("egyptian_campfire_stage1", Material.CAMPFIRE, Tier.TIER1, "Egyptian Campfire Stage 1",
				ItemCategory.MACHINES);
	}

}
