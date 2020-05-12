package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling;

import java.util.PriorityQueue;
import java.util.Queue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

/**
 * on tick event handler needed!
 * 
 * @author crash
 *
 */
public class TaskScheduler implements Runnable {

	private static Queue<ScheduledTask> tasks_ticks = new PriorityQueue<ScheduledTask>(new ScheduledTaskComparator());
	private static Queue<ScheduledTask> tasks_real_time = new PriorityQueue<ScheduledTask>(
			new ScheduledTaskComparator());
	
	private boolean request_shutdown = false;
	private static TaskScheduler instance;
	
	public synchronized static void scheduleTask(Task task, long time, TimeMeasurementType measurement_type) {
		if (measurement_type == TimeMeasurementType.REAL_TIME) {
			tasks_real_time
					.add(new ScheduledTask(task, TimeMeasurementType.REAL_TIME, System.currentTimeMillis() + time));
		} else {
			// ticks
			tasks_ticks.add(new ScheduledTask(task, TimeMeasurementType.TICKS, WorldEconomyPlugin.tick_counter + time));
		}
	}

	private static ScheduledTask assign_tmp;

	public synchronized static Task assign(TimeMeasurementType type) {
		// this can be improved to pull both kinds of tasks on CPU overload.

		if (type == TimeMeasurementType.REAL_TIME) {
			assign_tmp = tasks_real_time.peek();
			if (assign_tmp == null) {
				return null;
			}
			if (assign_tmp.due <= System.currentTimeMillis()) {
				return tasks_real_time.remove().task;
			}
			return null;
		} else {
			assign_tmp = tasks_ticks.peek();
			if (assign_tmp == null) {
				return null;
			}
			if (assign_tmp.due <= WorldEconomyPlugin.tick_counter) {
				return tasks_ticks.remove().task;
			}
			return null;
		}
	}

	public static void init() {
		#please implement
		instance = new TaskScheduler();
		new Thread(instance).start();
	}

	@Override
	public void run() {
		long start;
		long end;
		long run_duration;
		while (!request_shutdown) {
			start = System.currentTimeMillis();
			// logic
			
		}
	}

}
