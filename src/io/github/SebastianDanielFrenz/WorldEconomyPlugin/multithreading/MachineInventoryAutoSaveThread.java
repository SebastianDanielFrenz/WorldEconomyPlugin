package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.InventoryIO;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.ComparableLocation;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.MachineInventoryRegistry;

public class MachineInventoryAutoSaveThread implements Runnable {

	@Override
	public void run() {
		long duration = 10000;
		boolean last_run = false;

		while (true) {

			Map<ComparableLocation, Inventory> registry = MachineInventoryRegistry.copyRegistry();
			int size = registry.size();

			Iterator<ComparableLocation> locations = registry.keySet().iterator();
			Iterator<Inventory> inventories = registry.values().iterator();
			Inventory inv;
			Map<ComparableLocation, Inventory> current_registry;
			Iterator<ComparableLocation> current_locations;
			Location loc;
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
					Thread.sleep(duration / size);
				} catch (InterruptedException e) {
					WorldEconomyPlugin.plugin.getLogger().info("Shutting down salary handler thread!");
					return;
				}
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

			WorldEconomyPlugin.plugin.getLogger().info("Saved machine inventories!");
			if (last_run) {
				return;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				WorldEconomyPlugin.plugin.getLogger().info("Shutting down salary handler thread!");
				last_run = true;
			}
		}
	}

}
