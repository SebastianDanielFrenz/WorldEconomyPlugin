package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineKategory;

public class SieveDecorationGUIItem extends GUIItem {

	public SieveDecorationGUIItem(int row, int column) {
		super(row, column, new ItemStack(MachineKategory.SIEVING.display));
	}

	@Override
	public void event(InventoryClickEvent event) {
	}

}
