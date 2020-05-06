package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

import java.util.PriorityQueue;
import java.util.Queue;

public class TaskProcessor {

	private static long wait_duration;

	private static TaskWorker[] workers;

	public static void init(int threads, long wait_duration) {
		setWaitDuration(wait_duration);

		workers = new TaskWorker[threads];
		for (int i = 0; i < threads; i++) {
			workers[i] = new TaskWorker();
			new Thread(workers[i]);
		}
	}

	private static Queue<Task> tasks = new PriorityQueue<Task>(new TaskComparator());

	public static void registerTask(Task task) {
		tasks.add(task);
	}

	public static Task assignTask() {
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

}
