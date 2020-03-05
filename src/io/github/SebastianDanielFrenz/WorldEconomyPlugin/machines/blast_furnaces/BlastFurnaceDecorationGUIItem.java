package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;

public class BlastFurnaceDecorationGUIItem extends GUIItem {

	public BlastFurnaceDecorationGUIItem(int row, int column) {
		super(row, column, new ItemStack(Material.BLAST_FURNACE));
	}

	@Override
	public void event(InventoryClickEvent event) {
	}

}
