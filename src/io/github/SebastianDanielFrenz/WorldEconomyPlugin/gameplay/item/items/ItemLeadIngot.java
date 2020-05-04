package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;

public class ItemLeadIngot extends CustomItem {

	public ItemLeadIngot() {
		super("LEAD_INGOT", Material.IRON_INGOT, Age.UNDEFINED, "Lead Ingot", ItemCategory.RAW_MATERIALS);
	}

}
