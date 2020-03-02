package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.InventoryIO;

public class MachineInventoryRegistry {

	public static Map<Location, Inventory> inventories = new TreeMap<Location, Inventory>();

	public static void setupMachines() {
		for (File file : new File("plugins/WorldEconomyPlugin/saved_inventories").listFiles()) {
			String part = file.getName().substring(8);
			String part2 = part.split("[.]")[0];
			String[] part3 = part2.split("[_]");

			World world = Bukkit.getWorld(part3[0]);
			int x = Integer.parseInt(part3[1]);
			int y = Integer.parseInt(part3[2]);
			int z = Integer.parseInt(part3[3]);
			Location location = new Location(world, x, y, z);

			try {
				addMachine(location,
						InventoryIO.loadInventoryFromFile(Machine.getMachine(world.getBlockAt(location)), ""));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addMachine(Location location, Inventory inventory) {
		inventories.put(location, inventory);
	}

	public static void removeMachine(Location location) {
		inventories.remove(location);
		try {
			Files.delete(Paths.get("machine_" + location.getWorld().getName() + "_" + location.getBlockX() + "_"
					+ location.getBlockY() + "_" + location.getBlockZ() + ".mcinv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Inventory getInventory(Location location) {
		return inventories.get(location);
	}

}
