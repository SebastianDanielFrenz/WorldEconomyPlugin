package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.io.IOException;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.InventoryIO;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.ComparableLocation;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineInventoryRegistry;

public class MachineInventoryAutoSaveThread implements Runnable {

	@Override
	public void run() {
		long duration = 60000;
		while (true) {
			int size = MachineInventoryRegistry.inventories.size();

			Iterator<ComparableLocation> locations = MachineInventoryRegistry.inventories.keySet().iterator();
			Iterator<Inventory> inventories = MachineInventoryRegistry.inventories.values().iterator();
			Inventory inv;
			Location loc;
			for (int i = 0; i < size; i++) {
				inv = inventories.next();
				loc = locations.next().toLocation();
				try {
					InventoryIO.writeInventoryToFile(inv,
							"machine_" + loc.getBlockX() + "_" + loc.getBlockY() + "_" + loc.getBlockZ() + ".mcinv");
				} catch (IOException e) {
					e.printStackTrace();
				}
				WorldEconomyPlugin.plugin.getLogger().info("Saved machine inventories!");
				try {
					Thread.sleep(duration / size);
				} catch (InterruptedException e) {
					WorldEconomyPlugin.plugin.getLogger().info("Shutting down salary handler thread!");
					return;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				WorldEconomyPlugin.plugin.getLogger().info("Shutting down salary handler thread!");
				return;
			}
		}
	}

}
