package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemLeadRod extends CustomItem {

	public ItemLeadRod() {
		super("LEAD_ROD", Material.STICK, Age.UNDEFINED, "Lead Rod", ItemCategory.PROCESSED_MATERIALS);
	}

}
