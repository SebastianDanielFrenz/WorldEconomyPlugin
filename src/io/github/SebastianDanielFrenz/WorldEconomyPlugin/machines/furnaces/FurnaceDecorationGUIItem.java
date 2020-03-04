package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;

public class FurnaceDecorationGUIItem extends GUIItem {

	public FurnaceDecorationGUIItem(int row, int column) {
		super(row, column, new ItemStack(Material.FURNACE));
	}

	@Override
	public void event(InventoryClickEvent event) {
	}

}
