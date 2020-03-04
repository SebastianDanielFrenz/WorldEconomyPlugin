package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;

public class WEGUIRegistry implements Listener {

	public static List<WEGUI> GUIs = new ArrayList<WEGUI>();

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		InventoryHolder holder = event.getInventory().getHolder();

		WEGUI gui;
		for (int i = 0; i < GUIs.size(); i++) {
			gui = GUIs.get(i);
			if (holder == gui) {
				gui.eventHandler(event);
				break;
			}
		}
	}

	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		InventoryHolder holder = event.getInventory().getHolder();

		WEGUI gui;
		for (int i = 0; i < GUIs.size(); i++) {
			gui = GUIs.get(i);
			if (holder == gui) {
				gui.eventHandler(event);
				break;
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		GUIs.remove(event.getInventory().getHolder());
	}

}
