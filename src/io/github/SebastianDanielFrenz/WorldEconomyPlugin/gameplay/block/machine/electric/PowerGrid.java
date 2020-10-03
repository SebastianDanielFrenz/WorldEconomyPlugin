package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.util.OrderedList;

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

	// public void setDistributionTask(PowerDistributionTask task) {
	// powerDistributionTask = task;
	// }

	public final long ID;
	// public PowerDistributionTask powerDistributionTask;

	public List<Block> consumers = new OrderedList<Block>(new PriorityComparator());
	public List<Block> storages = new OrderedList<Block>(new PriorityComparator());
	public List<Block> supplyers = new OrderedList<Block>(new PriorityComparator());

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

	public void distributePower() {

		for (Block consumer : consumers) {
			CustomBlockMetadataValue meta = CustomBlockTypeRegistry.getBlockDetails(consumer);
			((PowerConsumerBlockType) meta.getBlock()).assignPower(consumer.getLocation(), meta.getBlockData(),
					((PowerConsumerBlockType) meta.getBlock()).getMaxPower(consumer.getLocation(), meta.getBlockData()));
		}

		for (Block consumer : consumers) {
			CustomBlockMetadataValue meta = CustomBlockTypeRegistry.getBlockDetails(consumer);
			((PowerConsumerBlockType) meta.getBlock()).usePower(consumer.getLocation(), meta.getBlockData());
		}
	}

	public void dump(CommandSender sender) {
		sender.sendMessage(" ------ " + ID + ":");
		sender.sendMessage(" --- supplyers:");
		for (Block supplyer : supplyers) {
			sender.sendMessage(CustomBlockTypeRegistry.getBlock(supplyer).ID);
		}

		sender.sendMessage(" --- storages:");
		for (Block storage : storages) {
			sender.sendMessage(CustomBlockTypeRegistry.getBlock(storage).ID);
		}

		sender.sendMessage(" --- consumers:");
		for (Block consumer : consumers) {
			sender.sendMessage(CustomBlockTypeRegistry.getBlock(consumer).ID);
		}

		sender.sendMessage(" --- cables:");
		for (Block cable : all_connected) {
			if (((CustomBlockMetadataValue) cable.getMetadata("customBlockType").get(0)).getBlock() instanceof PowerCableBlockType) {
				sender.sendMessage(CustomBlockTypeRegistry.getBlock(cable).ID);
			}
		}
	}

}