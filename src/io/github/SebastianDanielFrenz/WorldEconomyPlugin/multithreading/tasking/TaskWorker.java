package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

public class TaskWorker implements Runnable {

	private boolean shutdown_request = false;
	private boolean finished = false;

	/**
	 * This variable only exists for monitoring purposes.
	 */
	private Task current_task;

	@Override
	public void run() {
		Task task;
		while (true) {
			task = TaskProcessor.assignTask();
			if (task == null) {
				// idle
				if (shutdown_request) {
					finished = true;
					return;
				}
				try {
					Thread.sleep(TaskProcessor.getWaitDuration());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// System.out.println("Assigned task " + task + " to " +
				// Thread.currentThread().getName() + "!");

				current_task = task;

				if (!task.hasStarted()) {
					if (shutdown_request && !task.startOnShutdown()) {
						task.discard();
						current_task = null;
						continue;
					}
					task.setup();
					task.init();
				}

				if (shutdown_request) {
					if (!task.continueOnShutdown()) {
						task.discard();
						current_task = null;
						continue;
					}
				}

				try {
					task.work();
					if (!task.hasFinished()) {
						TaskProcessor.registerTask(task);
					}
					current_task = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
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

}
