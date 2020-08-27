package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class PowerGrid {

	public PowerGrid(Block block, PowerGridMemberType type) {
		ID = PowerGridRegistry.lastPowerGridID + 1;
		PowerGridRegistry.lastPowerGridID++;
		
		if (type == PowerGridMemberType.CONSUMER) {
			consumers.add(block);
		} else if (type == PowerGridMemberType.STORAGE) {
			storages.add(block);
		} else if (type == PowerGridMemberType.SUPPLYER) {
			supplyers.add(block);
		}

		all_connected.add(block);
	}

	public final long ID;

	public List<Block> consumers = new ArrayList<Block>();
	public List<Block> storages = new ArrayList<Block>();
	public List<Block> supplyers = new ArrayList<Block>();

	public List<Block> all_connected = new ArrayList<Block>();

	public boolean isNextToGrid(Location loc) {
		Location loc2;

		for (Block block : all_connected) {
			loc2 = block.getLocation();

			if (loc.getBlockX() == loc2.getBlockX() && loc.getBlockY() == loc2.getBlockY() && loc.getBlockZ() == loc2.getBlockZ() + 1) {
				return true;
			} else if (loc.getBlockX() == loc2.getBlockX() && loc.getBlockY() == loc2.getBlockY() && loc.getBlockZ() == loc2.getBlockZ() - 1) {
				return true;
			} else if (loc.getBlockX() == loc2.getBlockX() && loc.getBlockY() == loc2.getBlockY() + 1 && loc.getBlockZ() == loc2.getBlockZ()) {
				return true;
			} else if (loc.getBlockX() == loc2.getBlockX() && loc.getBlockY() == loc2.getBlockY() - 1 && loc.getBlockZ() == loc2.getBlockZ()) {
				return true;
			} else if (loc.getBlockX() == loc2.getBlockX() + 1 && loc.getBlockY() == loc2.getBlockY() && loc.getBlockZ() == loc2.getBlockZ()) {
				return true;
			} else if (loc.getBlockX() == loc2.getBlockX() - 1 && loc.getBlockY() == loc2.getBlockY() && loc.getBlockZ() == loc2.getBlockZ()) {
				return true;
			}
		}

		return false;
	}

	public void integrateForeignPowerGrid(PowerGrid powerGrid) {
		for (Block consumer : powerGrid.consumers) {
			consumers.add(consumer);

		}
		for (Block storage : powerGrid.storages) {
			storages.add(storage);
		}
		for (Block supplyer : powerGrid.supplyers) {
			supplyers.add(supplyer);
		}
		for (Block connected : powerGrid.all_connected) {
			all_connected.add(connected);
		}
	}

}