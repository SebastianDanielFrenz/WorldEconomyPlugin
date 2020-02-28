package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomItem {

	public CustomItem(Material base, String name) {
		base_material = base;
		item_name = name;
	}

	public CustomItem(Material base) {
		base_material = base;
		item_name = null;
	}

	public final Material base_material;
	public final String item_name;

	public boolean matches(ItemStack itemStack) {
		return itemStack == null ? false
				: (itemStack.getType() == base_material && (itemStack.hasItemMeta()
						? itemStack.getItemMeta().getDisplayName().equals(item_name) : item_name == null));
	}

}
