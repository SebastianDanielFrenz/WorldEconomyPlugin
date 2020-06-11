package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Config;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class TaskProcessor {

	private static long wait_duration;

	private static TaskWorker[] workers;
	private static Thread[] worker_threads;

	public static void init(int threads, long wait_duration) {
		setWaitDuration(wait_duration);

		workers = new TaskWorker[threads];
		worker_threads = new Thread[threads];

		for (int i = 0; i < threads; i++) {
			workers[i] = new TaskWorker();
			worker_threads[i] = new Thread(workers[i], "World Economy Task Worker #" + String.valueOf(i + 1));
			worker_threads[i].start();
		}
	}

	private static Queue<Task> tasks = new PriorityQueue<Task>(new TaskComparator());

	private static long last_checked = 0;

	public synchronized static void registerTask(Task task) {
		if (getQueueLength() > Config.getPendingTaskLimit()) {
			if (Config.doOverloadWarnings()) {
				if (System.currentTimeMillis() > last_checked + 1000 * Config.getOverloadWarningInterval()) {
					last_checked = System.currentTimeMillis();
					if (getQueueLength() > 1000) {
						WorldEconomyPlugin.plugin.getLogger().log(Level.WARNING,
								"WEP background task handler overloaded (" + getQueueLength()
										+ " tasks pending, limit: " + Config.getPendingTaskLimit() + "!");
					}
				}
			}
		} else {
			tasks.add(task);
		}

	}

	public synchronized static Task assignTask() {
		return tasks.poll();
	}

	public static void orderShutdown() {
		for (TaskWorker worker : workers) {
			worker.orderShutdown();
		}
	}

	/**
	 * Returns weather there are any workers left running.
	 * 
	 * @return
	 */
	public static boolean isRunning() {
		for (TaskWorker worker : workers) {
			if (!worker.hasFinished()) {
				return true;
			}
		}
		return false;
	}

	public static int getThreads() {
		return workers.length;
	}

	public static long getWaitDuration() {
		return wait_duration;
	}

	private static void setWaitDuration(long wait_duration) {
		TaskProcessor.wait_duration = wait_duration;
	}

	public static int getQueueLength() {
		return tasks.size();
	}

	public static TaskWorkerStatus getStatus() {
		Task[] current_tasks = new Task[workers.length];
		float[] idle_durations = new float[workers.length];
		float[] working_durations = new float[workers.length];
		for (int i = 0; i < workers.length; i++) {
			current_tasks[i] = workers[i].getCurrentTask();

			workers[i].requestData(Thread.currentThread());

			idle_durations[i] = workers[i].getIdleTime();
			working_durations[i] = workers[i].getWorkingTime();

			worker_threads[i].interrupt();
		}
		int pending_tasks = getQueueLength();
		return new TaskWorkerStatus(current_tasks, pending_tasks > Config.getPendingTaskLimit(), pending_tasks,
				idle_durations, working_durations);
	}

}
