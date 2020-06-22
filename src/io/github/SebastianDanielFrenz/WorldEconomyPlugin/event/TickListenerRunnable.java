package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import java.sql.SQLException;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling.TaskScheduler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling.TimeMeasurementType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

public class TickListenerRunnable implements Runnable {

	@Override
	public void run() {
		WorldEconomyPlugin.tick_counter++;
		try {
			WEDB.updateTickCounter(WorldEconomyPlugin.tick_counter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Task task;

		// handle scheduled tasks for tick time
		for (task = TaskScheduler.assign(TimeMeasurementType.TICKS); task != null; task = TaskScheduler.assign(TimeMeasurementType.TICKS)) {
			// debugging
			System.out.println("scheduler pushed real time based task " + task + " to task processor!");
			TaskProcessor.registerTask(task);
		}
	}

}
