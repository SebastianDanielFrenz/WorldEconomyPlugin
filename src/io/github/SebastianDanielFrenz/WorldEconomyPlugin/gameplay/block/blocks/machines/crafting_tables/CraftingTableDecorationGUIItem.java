package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.crafting_tables;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;

public class CraftingTableDecorationGUIItem extends GUIItem {

	public CraftingTableDecorationGUIItem(int row, int column) {
		super(row, column, new ItemStack(Material.WORKBENCH));
	}

	@Override
	public void event(InventoryClickEvent event) {
	}

}
