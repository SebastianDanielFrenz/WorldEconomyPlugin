package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling;

import java.util.PriorityQueue;
import java.util.Queue;

import org.bukkit.Bukkit;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

/**
 * on tick event handler needed!
 * 
 * @author crash
 *
 */
public class TaskScheduler implements Runnable {

	private static Queue<ScheduledTask> tasks_ticks = new PriorityQueue<ScheduledTask>(new ScheduledTaskComparator());
	private static Queue<ScheduledTask> tasks_real_time = new PriorityQueue<ScheduledTask>(new ScheduledTaskComparator());

	private boolean request_shutdown = false;
	private boolean running = true;
	private static TaskScheduler instance;

	public static boolean isRunning() {
		return instance.running;
	}

	public synchronized static void scheduleTask(Task task, long time, TimeMeasurementType measurement_type) {
		if (measurement_type == TimeMeasurementType.REAL_TIME) {
			tasks_real_time.add(new ScheduledTask(task, TimeMeasurementType.REAL_TIME, System.currentTimeMillis() + time));
		} else {
			// ticks
			tasks_ticks.add(new ScheduledTask(task, TimeMeasurementType.TICKS, WorldEconomyPlugin.tick_counter + time));
		}
	}

	@SuppressWarnings("deprecation")
	public static void scheduleRepeatingTask(Task task, long delay, long time, TimeMeasurementType measurement_type) {
		if (measurement_type == TimeMeasurementType.REAL_TIME) {
			Bukkit.getScheduler().scheduleAsyncRepeatingTask(WorldEconomyPlugin.plugin, new Runnable() {

				@Override
				public void run() {
					TaskProcessor.registerTask(task);
				}
			}, delay, time);
		}
	}

	public static void scheduleRepeatingTask(Task task, long time, TimeMeasurementType measurement_type) {
		scheduleRepeatingTask(task, 0, time, measurement_type);
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
		instance = new TaskScheduler();
		new Thread(instance).start();
	}

	@Override
	public void run() {
		// only pushes real time based ScheduledTask objects to the
		// TaskProcessor; tick based stuff should be handeled by a tick
		// listener.

		long start;
		long end;
		long run_duration;
		Task task;

		while (!request_shutdown) {
			start = System.currentTimeMillis();
			// logic

			for (task = assign(TimeMeasurementType.REAL_TIME); task != null; task = assign(TimeMeasurementType.REAL_TIME)) {
				// debugging
				System.out.println("scheduler pushed real time based task " + task + " to task processor!");
				TaskProcessor.registerTask(task);
			}

			// end logic
			end = System.currentTimeMillis();
			run_duration = end - start;
			if (run_duration < 1000) {
				try {
					Thread.sleep(1000 - run_duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		running = false;
	}

	public static void orderShutdown() {
		instance.request_shutdown = true;
	}

}
