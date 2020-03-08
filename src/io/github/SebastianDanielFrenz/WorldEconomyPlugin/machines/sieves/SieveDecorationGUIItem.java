package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineCategory;

public class SieveDecorationGUIItem extends GUIItem {

	public SieveDecorationGUIItem(int row, int column) {
		super(row, column, new ItemStack(MachineCategory.SIEVING.display));
	}

	@Override
	public void event(InventoryClickEvent event) {
	}

}
