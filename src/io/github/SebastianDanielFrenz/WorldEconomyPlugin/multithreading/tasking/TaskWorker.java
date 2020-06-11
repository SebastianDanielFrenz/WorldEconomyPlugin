package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

import java.util.LinkedList;
import java.util.Queue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Config;

public class TaskWorker implements Runnable {

	private boolean shutdown_request = false;
	private boolean finished = false;

	/**
	 * This variable only exists for monitoring purposes.
	 */
	private Task current_task;
	private Queue<long[]> idles = new LinkedList<long[]>();
	private Queue<long[]> working = new LinkedList<long[]>();

	private int period = Config.getPerformanceMonitoringPeriod() * 1000;

	private boolean request_data = false;
	private Thread requesting_thread;

	@Override
	public void run() {
		long start;
		Task task;
		long last_purge = 0;
		while (true) {
			if (request_data) {
				requesting_thread.interrupt();
				while (true) {
					try {
						Thread.sleep(Long.MAX_VALUE);
					} catch (InterruptedException e) {
						break;
					}
				}
				request_data = false;

				System.out.println("continuing program");
			}
			if (last_purge + 1000 < System.currentTimeMillis()) {
				purgeOldTrackingData();
				last_purge = System.currentTimeMillis();
			}

			task = TaskProcessor.assignTask();
			if (task == null) {
				current_task = null;
				// idle
				if (shutdown_request) {
					finished = true;
					return;
				}
				try {
					Thread.sleep(TaskProcessor.getWaitDuration());
					idles.add(new long[] { TaskProcessor.getWaitDuration(), System.currentTimeMillis() + period });
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// System.out.println("Assigned task " + task + " to " +
				// Thread.currentThread().getName() + "!");
				start = System.nanoTime();

				current_task = task;

				if (!task.hasStarted()) {
					if (shutdown_request && !task.startOnShutdown()) {
						task.discard();
						working.add(new long[] { System.nanoTime() - start, System.currentTimeMillis() + period });
						continue;
					}
					task.setup();
					task.init();
				}

				if (shutdown_request) {
					if (!task.continueOnShutdown()) {
						task.discard();
						working.add(new long[] { System.nanoTime() - start, System.currentTimeMillis() + period });
						continue;
					}
				}

				try {
					task.work();
					if (!task.hasFinished()) {
						TaskProcessor.registerTask(task);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				working.add(new long[] { System.nanoTime() - start, System.currentTimeMillis() + period });

			}
		}
	}

	public void orderShutdown() {
		shutdown_request = true;
	}

	public boolean hasFinished() {
		return finished;
	}

	public Task getCurrentTask() {
		return current_task;
	}

	public float getIdleTime() {
		long total = 0;
		for (long[] idle : idles) {
			total += idle[0];
		}
		return total / 1000f;
	}

	public float getWorkingTime() {
		long total = 0;
		for (long[] work : working) {
			total += work[0];
		}
		return total / 1000000000f;
	}

	public void purgeOldTrackingData() {
		try {
			while (true) {
				if (idles.peek()[1] <= System.currentTimeMillis()) {
					idles.poll();
				} else {
					break;
				}
			}
		} catch (NullPointerException e) {
		}
		try {
			while (true) {
				if (working.peek()[1] <= System.currentTimeMillis()) {
					working.poll();
				} else {
					break;
				}
			}
		} catch (NullPointerException e) {
		}
	}

	/**
	 * This function exists with a halted worker thread.
	 */
	public void requestData(Thread thread) {
		request_data = true;
		requesting_thread = thread;
		while (true) {
			try {
				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
