package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class GUIItem {

	public final int slot;
	public ItemStack itemStack;

	public GUIItem(int slot, ItemStack itemStack) {
		this.slot = slot;
		this.itemStack = itemStack;
	}

	public GUIItem(int row, int column, ItemStack itemStack) {
		slot = row * 9 + column;
		this.itemStack = itemStack;
	}

	public abstract void event(InventoryClickEvent event);

}
