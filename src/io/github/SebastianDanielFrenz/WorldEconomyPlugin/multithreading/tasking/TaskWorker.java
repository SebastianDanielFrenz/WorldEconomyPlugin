package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

public class TaskWorker implements Runnable {

	private boolean shutdown_request = false;
	private boolean finished = false;

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

				if (!task.hasStarted()) {
					if (shutdown_request && !task.startOnShutdown()) {
						task.discard();
						continue;
					}
					task.setup();
					task.init();
				}

				if (shutdown_request) {
					if (!task.continueOnShutdown()) {
						task.discard();
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
			}
		}
	}

	public void orderShutdown() {
		shutdown_request = true;
	}

	public boolean hasFinished() {
		return finished;
	}

}
