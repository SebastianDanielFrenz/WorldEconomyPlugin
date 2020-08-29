package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.PowerConnectedBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

public class PowerGridRegistry {

	public static long lastPowerGridID = 0;

	public static List<PowerGrid> powerGrids = new ArrayList<PowerGrid>();

	public static PowerGrid getPowerGrid(long ID) {
		for (PowerGrid powerGrid : powerGrids) {
			if (powerGrid.ID == ID) {
				return powerGrid;
			}
		}

		return null;
	}

	public static void dumpPowerGrids(CommandSender sender) {
		sender.sendMessage("dumping power grids:");

		for (PowerGrid powerGrid : powerGrids) {
			sender.sendMessage(" ------ " + powerGrid.ID + ":");
			sender.sendMessage(" --- supplyers:");
			for (Block supplyer : powerGrid.supplyers) {
				sender.sendMessage(CustomBlockTypeRegistry.getBlock(supplyer).ID);
			}

			sender.sendMessage(" --- storages:");
			for (Block storage : powerGrid.storages) {
				sender.sendMessage(CustomBlockTypeRegistry.getBlock(storage).ID);
			}

			sender.sendMessage(" --- consumers:");
			for (Block consumer : powerGrid.consumers) {
				sender.sendMessage(CustomBlockTypeRegistry.getBlock(consumer).ID);
			}

			sender.sendMessage(" --- cables:");
			for (Block cable : powerGrid.all_connected) {
				if (((CustomBlockMetadataValue) cable.getMetadata("customBlockType").get(0))
						.getBlock() instanceof PowerCableBlockType) {
					sender.sendMessage(CustomBlockTypeRegistry.getBlock(cable).ID);
				}
			}
		}
	}

	/**
	 * This method creates new power grids or attaches newly placed powered
	 * blocks to existing power grids nearby.
	 * 
	 * @param location
	 * @param memberType
	 * @return
	 */
	public static PowerGrid extendPowerGridTo(Location location, PowerGridMemberType memberType) {
		PowerGrid out = null;

		for (PowerGrid powerGrid : powerGrids) {
			if (powerGrid.isNextToGrid(location)) {
				if (out == null) {
					out = powerGrid;
				} else {
					out.integrateForeignPowerGrid(powerGrid);
					powerGrids.remove(powerGrid);
					for (Block block : powerGrid.all_connected) {
						PowerConnectedBlockData data = ((PowerConnectedBlockData) CustomBlockTypeRegistry
								.getBlockDetails(block).getBlockData());
						data.powerGrid = out;
						TaskProcessor.registerTask(new Task() {

							@Override
							public void work() {
								try {
									WEDB.updateBlockData(block, data);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}

							@Override
							public boolean startOnShutdown() {
								return true;
							}

							@Override
							public void init() {
							}

							@Override
							public boolean hasFinished() {
								return true;
							}

							@Override
							public int getPriority() {
								return Integer.MIN_VALUE;
							}

							@Override
							public String getName() {
								return "SQL Block Data Updater";
							}

							@Override
							public boolean discardOnOverload() {
								return false;
							}

							@Override
							public void discard() {
							}

							@Override
							public boolean continueOnShutdown() {
								return true; // does not matter as it will never
												// have to continue, it finishes
												// instantly.
							}
						});
					}
				}
			}
		}

		if (out == null) {
			out = new PowerGrid(location.getBlock(), memberType);
			powerGrids.add(out);
			return out;
		} else {
			out.all_connected.add(location.getBlock());
			if (memberType == PowerGridMemberType.SUPPLYER) {
				out.supplyers.add(location.getBlock());
			} else if (memberType == PowerGridMemberType.STORAGE) {
				out.storages.add(location.getBlock());
			} else if (memberType == PowerGridMemberType.CONSUMER) {
				out.consumers.add(location.getBlock());
			}
			return out;
		}
	}

}
