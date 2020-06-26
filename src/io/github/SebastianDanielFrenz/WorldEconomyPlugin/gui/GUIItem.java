package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public abstract class GUIItem {

	public final int slot;
	public ItemStack itemStack;
	public Age min_age;

	public GUIItem(int slot, ItemStack itemStack) {
		this.slot = slot;
		this.itemStack = itemStack;
	}

	public GUIItem(int row, int column, ItemStack itemStack) {
		slot = row * 9 + column;
		this.itemStack = itemStack;
	}

	public GUIItem(int slot, ItemStack itemStack, Age min_age) {
		this.slot = slot;
		this.itemStack = itemStack;
		this.min_age = min_age;
	}

	public GUIItem(int row, int column, ItemStack itemStack, Age min_age) {
		slot = row * 9 + column;
		this.itemStack = itemStack;
		this.min_age = min_age;
	}

	public abstract void event(InventoryClickEvent event);

}
