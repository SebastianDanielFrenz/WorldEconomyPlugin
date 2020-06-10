package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.SwordItemDetail;

public class ItemSharpStick extends CustomItem {

	public ItemSharpStick() {
		super("sharp_stick", Material.STICK, Age.EARLY_STONE_AGE, "Sharp Stick", ItemCategory.EQUIPMENT, new ItemDetail[] { new SwordItemDetail(2) });
	}

}
