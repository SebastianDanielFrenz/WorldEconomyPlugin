package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.items.admin_tools;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.SwordItemDetail;

public class ItemAdminSword extends CustomItem {

	public ItemAdminSword() {
		super("admin_sword", Material.DIAMOND_SWORD, Age.UNDEFINED, "Admin Sword", ItemCategory.EQUIPMENT,
				new ItemDetail[] { new SwordItemDetail(1000000) });
	}

}
