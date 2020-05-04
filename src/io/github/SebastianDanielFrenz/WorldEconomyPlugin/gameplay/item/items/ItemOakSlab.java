package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemOakSlab extends CustomItem {

	public ItemOakSlab() {
		super("oak_slab", Material.OAK_SLAB, Age.EARLY_STONE_AGE, "Oak Slab", ItemCategory.BUILDING, true);
	}

}
