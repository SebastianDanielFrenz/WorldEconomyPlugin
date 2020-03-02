package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

	public ItemStack toItemStack() {
		if (item_name == null) {
			return new ItemStack(base_material);
		} else {
			ItemStack out = new ItemStack(base_material);
			ItemMeta meta = out.getItemMeta();
			meta.setDisplayName(item_name);
			out.setItemMeta(meta);
			return out;
		}
	}

}
