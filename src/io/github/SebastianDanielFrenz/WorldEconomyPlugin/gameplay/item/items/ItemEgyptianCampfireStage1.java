package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomPlaceableItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemEgyptianCampfireStage1 extends CustomPlaceableItem {

	public ItemEgyptianCampfireStage1() {
		super("egyptian_campfire_stage1", Material.WOOD_STEP, Age.COPPER_AGE, "Egyptian Campfire Stage 1",
				ItemCategory.MACHINES);
	}

}
