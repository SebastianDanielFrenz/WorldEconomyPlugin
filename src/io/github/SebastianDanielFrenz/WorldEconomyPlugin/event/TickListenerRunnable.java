package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockMetadataValue;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling.TaskScheduler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling.TimeMeasurementType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

public class TickListenerRunnable implements Runnable {

	public static List<CustomBlock> placing_list = new ArrayList<CustomBlock>();

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		int year = (int) WEDB.getYear();
		WorldEconomyPlugin.tick_counter++;

		// place blocks in main thread
		int placed_blocks = 0;

		Block block;
		for (CustomBlock customBlock : placing_list) {
			block = customBlock.getLocation().toLocation().getBlock();
			block.setType(customBlock.getType().material);
			block.setData(customBlock.getType().vanilla_data);
			block.setMetadata("customBlockType",
					new CustomBlockMetadataValue(customBlock.getType(), customBlock.getData()));

			placed_blocks++;
		}

		if (placed_blocks != 0) {
			System.out.println("tick listener (main thread) placed " + placed_blocks + " blocks!");
			placing_list.clear();
		}

		if ((int) WEDB.getYear() != year) {
			// year just changed
			try {
				WEDB.createAllCompanyRevenues(year);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			WEDB.updateTickCounter(WorldEconomyPlugin.tick_counter);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Task task;
		TaskScheduler.resetTickBasedAssignmentIndex();

		// handle scheduled tasks for tick time
		for (task = TaskScheduler.assign(TimeMeasurementType.TICKS); task != null; task = TaskScheduler
				.assign(TimeMeasurementType.TICKS)) {
			// debugging
			System.out.println("scheduler pushed real time based task " + task + " to task processor!");
			TaskProcessor.registerTask(task);
		}
	}

}
