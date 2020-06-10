package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

public class TaskWorkerStatus {

	public TaskWorkerStatus(Task[] tasks, boolean overloaded, int pending_tasks) {
		this.tasks = tasks;
		this.overloaded = overloaded;
		this.pending_tasks = pending_tasks;
	}

	public final Task[] tasks;
	public final boolean overloaded;
	public final int pending_tasks;

	public String[] getFormattedStatus() {
		String[] out = new String[tasks.length + 2];
		out[0] = "==============================";
		out[0] += " " + tasks.length + " threads working on " + pending_tasks + " tasks ";
		out[0] += "==============================";
		for (int i = 0; i < tasks.length; i++) {
			out[i + 1] = tasks[i].getName();
		}
		out[tasks.length + 1] = "========================================================================================";
		return out;
	}

}
