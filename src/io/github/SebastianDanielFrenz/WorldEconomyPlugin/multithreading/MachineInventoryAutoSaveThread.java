package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.InventoryIO;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.ComparableLocation;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineInventoryRegistry;

public class MachineInventoryAutoSaveThread implements Runnable {

	@Override
	public void run() {
		long duration = 10000;
		boolean last_run = false;
		boolean twice = false;

		while (true) {

			if (last_run) {
				if (!twice) {
					WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
							"Saving machine inventories for shutdown...");
				} else {
					WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
							"Saving machine inventories for shutdown...(2)");
				}
			}

			Map<ComparableLocation, Inventory> registry = MachineInventoryRegistry.copyRegistry();
			int size = registry.size();

			Iterator<ComparableLocation> locations = registry.keySet().iterator();
			Iterator<Inventory> inventories = registry.values().iterator();
			Inventory inv;
			Map<ComparableLocation, Inventory> current_registry;
			Iterator<ComparableLocation> current_locations;
			Location loc;
			boolean skip = false;

			for (int i = 0; i < size; i++) {
				inv = inventories.next();
				loc = locations.next().toLocation();
				try {
					InventoryIO.writeInventoryToFile(inv, "machine_" + loc.getWorld().getName() + "_" + loc.getBlockX()
							+ "_" + loc.getBlockY() + "_" + loc.getBlockZ() + ".mcinv");
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					if (!(last_run || skip)) {
						Thread.sleep(duration / size);
					}
				} catch (InterruptedException e) {
					WorldEconomyPlugin.plugin.getLogger().info("Shutting down machine inventory auto save thread!");
					skip = true;
				}
			}

			if (twice) {
				return;
			}
			if (last_run) {
				twice = true;
			}

			current_registry = MachineInventoryRegistry.copyRegistry();
			ComparableLocation tmp;

			locations = registry.keySet().iterator();
			boolean match = false;
			Location location;

			while (locations.hasNext()) {
				tmp = locations.next();
				current_locations = current_registry.keySet().iterator();
				while (current_locations.hasNext()) {
					if (tmp.compareTo(current_locations.next()) == 0) {
						match = true;
						break;
					}
				}
				if (!match) {
					try {
						location = tmp.toLocation();
						Files.delete(Paths.get("machine_" + location.getWorld().getName() + "_" + location.getBlockX()
								+ "_" + location.getBlockY() + "_" + location.getBlockZ() + ".mcinv"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			//WorldEconomyPlugin.plugin.getLogger().info("Saved machine inventories!");

			if (skip) {
				last_run = true;
			}

			try {
				if (!last_run) {
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				WorldEconomyPlugin.plugin.getLogger().info("Shutting down machine inventory auto save thread!");
				last_run = true;
			}
		}
	}

}
