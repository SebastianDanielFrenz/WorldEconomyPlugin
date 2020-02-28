package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

public class CustomItemStack {

	public CustomItemStack(CustomItem item, int count) {
		this.item = item;
		this.count = count;
	}

	private CustomItem item;
	private int count;

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
}
