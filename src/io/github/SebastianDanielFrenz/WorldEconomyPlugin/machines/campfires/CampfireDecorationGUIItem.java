package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.campfires;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.GUIItem;

public class CampfireDecorationGUIItem extends GUIItem {

	public CampfireDecorationGUIItem(int row, int column) {
		super(row, column, new ItemStack(Material.CAMPFIRE));
	}

	@Override
	public void event(InventoryClickEvent event) {
	}

}
