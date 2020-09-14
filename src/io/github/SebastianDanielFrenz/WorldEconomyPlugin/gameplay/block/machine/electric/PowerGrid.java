package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks.PowerDistributionTask;
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
		double total_possible_consumption = 0;

		for (Block consumer : consumers) {
			CustomBlockMetadataValue meta = CustomBlockTypeRegistry.getBlockDetails(consumer);
			total_possible_consumption += ((PowerConsumerBlockType) meta.getBlock()).getMaxPower(consumer.getLocation(), meta.getBlockData());
		}

		double total_possible_supply = 0;

		for (Block supplyer : supplyers) {
			CustomBlockMetadataValue meta = CustomBlockTypeRegistry.getBlockDetails(supplyer);
			total_possible_supply += ((PowerSupplyerBlockType) meta.getBlock()).getPowerOutput(supplyer.getLocation(), meta.getBlockData(),
					total_possible_consumption - total_possible_supply);
		}

		if (total_possible_consumption > total_possible_supply) {
			double total_possible_storage_out = 0;

			for (Block storage : storages) {
				CustomBlockMetadataValue meta = CustomBlockTypeRegistry.getBlockDetails(storage);
				total_possible_storage_out += ((PowerStorageBlockType) meta.getBlock()).getMaxPowerOutput(storage.getLocation(), meta.getBlockData());
			}

			if (total_possible_consumption > total_possible_supply + total_possible_storage_out) {
				for (Block consumer : consumers) {
					CustomBlockMetadataValue consumerMeta = CustomBlockTypeRegistry.getBlockDetails(consumer);
					PowerConsumerBlockType blockType = ((PowerConsumerBlockType) consumerMeta.getBlock());

					double max = blockType.getMaxPower(consumer.getLocation(), consumerMeta.getBlockData());

					if (max > total_possible_supply + total_possible_storage_out) {
						if (blockType.acceptPower(consumer.getLocation(), consumerMeta.getBlockData(),
								total_possible_storage_out + total_possible_supply)) {
							for (Block supplyer : supplyers) {
								CustomBlockMetadataValue supplyerMeta = CustomBlockTypeRegistry.getBlockDetails(supplyer);
								double max_supplyer_output = ((PowerSupplyerBlockType) consumerMeta.getBlock()).getPowerOutput(supplyer.getLocation(),
										consumerMeta.getBlockData(), max);
								((PowerSupplyerBlockType) supplyerMeta.getBlock()).usePower(supplyer.getLocation(), supplyerMeta.getBlockData(),
										max_supplyer_output);
								// if getPowerOutput makes ingame changes, this
								// needs to be run
							}
							for (Block storage : storages) {
								CustomBlockMetadataValue storageMeta = CustomBlockTypeRegistry.getBlockDetails(storage);
								double max_storage_output = ((PowerStorageBlockType) storageMeta.getBlock()).getMaxPowerOutput(storage.getLocation(),
										storageMeta.getBlockData());
								((PowerStorageBlockType) storageMeta.getBlock()).usePower(storage.getLocation(), storageMeta.getBlockData(),
										max_storage_output);
							}
							return; // all the power is used up
						} else {
							continue;
						}
					} else {
						if (max > total_possible_supply) {

						}
					}
				}
			}
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