package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.inventory.ItemStack;

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
		return itemStack == null ? false
				: (itemStack.getType() == item.base_material
						&& (itemStack.hasItemMeta() ? (itemStack.getItemMeta().getDisplayName().equals(item.item_name) && itemStack.getItemMeta().getLore()==null?data.isEmpty():) : item.item_name == null));
	}
}
