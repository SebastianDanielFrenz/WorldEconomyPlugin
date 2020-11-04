package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
	private static List<ScheduledRepeatingTask> tasks_ticks_repeating = new ArrayList<ScheduledRepeatingTask>();
	private static int tasks_ticks_repeating_index = 0;
	private static boolean tasks_ticks_repeating_done = false;

	private static Queue<ScheduledTask> tasks_real_time = new PriorityQueue<ScheduledTask>(
			new ScheduledTaskComparator());

	private boolean request_shutdown = false;
	private boolean running = true;
	private static TaskScheduler instance;

	public static boolean isRunning() {
		return instance.running;
	}

	public synchronized static void scheduleTask(Task task, long time, TimeMeasurementType measurement_type) {
		if (measurement_type == TimeMeasurementType.REAL_TIME) {
			tasks_real_time
					.add(new ScheduledTask(task, TimeMeasurementType.REAL_TIME, System.currentTimeMillis() + time));
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
		} else {
			tasks_ticks_repeating.add(new ScheduledRepeatingTask(task, delay + WorldEconomyPlugin.tick_counter, time));
		}
	}

	public static void scheduleRepeatingTask(Task task, long time, TimeMeasurementType measurement_type) {
		scheduleRepeatingTask(task, 0, time, measurement_type);
	}

	private static ScheduledTask assign_tmp;
	private static ScheduledRepeatingTask assign_tmp2;

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
			if (assign_tmp != null) {
				// System.out.println("attempting to assign single tick
				// scheduled task " + assign_tmp.task.getName());
				if (assign_tmp.due <= WorldEconomyPlugin.tick_counter) {
					return tasks_ticks.remove().task;
				}
				return null;
			}
			// System.out.println("no single tasks found!");

			// now looking at repeating tasks
			if (tasks_ticks_repeating_done) {
				return null;
			}

			if (tasks_ticks_repeating_index == tasks_ticks_repeating.size()) {
				tasks_ticks_repeating_done = true;
				return null;
			}

			assign_tmp2 = tasks_ticks_repeating.get(tasks_ticks_repeating_index);
			long overdue = WorldEconomyPlugin.tick_counter - assign_tmp2.due;

			// System.out.println(assign_tmp2.task.getName() + " is overdue " +
			// overdue + " with interval "
			// + assign_tmp2.interval + " --> " + (overdue %
			// assign_tmp2.interval));

			if (overdue >= 0 && overdue % assign_tmp2.interval == 0) {
				tasks_ticks_repeating_index++;
				// System.out.println("assigning " +
				// assign_tmp2.task.getName());
				return assign_tmp2.task;
			} else {
				tasks_ticks_repeating_done = true;
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

			for (task = assign(TimeMeasurementType.REAL_TIME); task != null; task = assign(
					TimeMeasurementType.REAL_TIME)) {
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

	public static void resetTickBasedAssignmentIndex() {
		tasks_ticks_repeating_index = 0;
		tasks_ticks_repeating.sort(new Comparator<ScheduledRepeatingTask>() {

			long t1;
			long t2;

			@Override
			public int compare(ScheduledRepeatingTask o1, ScheduledRepeatingTask o2) {
				t1 = o1.timeUntilDueAgain();
				t2 = o2.timeUntilDueAgain();

				if (t1 < t2) {
					return -1;
				} else if (t1 == t2) {
					return 0;
				} else {
					return 1;
				}
			}
		});

		tasks_ticks_repeating_done = false;

		// String strg = "";
		// strg += tasks_ticks_repeating.size();
		// strg += ": ";
		//
		// for (ScheduledRepeatingTask task : tasks_ticks_repeating) {
		// strg += task.task.getName() + ", ";
		// }
		// System.out.println(strg);
	}

}
