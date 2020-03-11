package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItemStack {

	public CustomItemStack(CustomItem item, int count) {
		this.item = item;
		this.count = count;
		data = new CustomItemData();
	}

	public CustomItemStack(CustomItem item, int count, CustomItemData data) {
		this.item = item;
		this.count = count;
		this.data = data;
	}

	private CustomItem item;
	private int count;
	private CustomItemData data;

	public CustomItem getItem() {
		return item;
	}

	public void setItem(CustomItem item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CustomItemData getData() {
		return data;
	}

	public boolean matches(ItemStack itemStack) {
		if (itemStack == null) {
			return false;
		} else {
			if (itemStack.getType() != item.base_material) {
				return false;
			}

			if (itemStack.getItemMeta().getDisplayName() != null && itemStack.hasItemMeta()) {
				if (!itemStack.getItemMeta().getDisplayName().equals(item.item_name)) {
					return false;
				}
			} else {
				if (item.item_name != null) {
					return false;
				}
			}
			if (!data.matches(itemStack)) {
				return false;
			}

			return true;

		}
	}

	public ItemStack toItemStack() {
		ItemStack itemStack = new ItemStack(item.base_material, count);
		data.apply(itemStack);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(item.item_name);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static ItemStack[] convert(CustomItemStack[] stacks) {
		ItemStack[] out = new ItemStack[stacks.length];
		for (int i = 0; i < stacks.length; i++) {
			out[i] = stacks[i].toItemStack();
		}
		return out;
	}
}
