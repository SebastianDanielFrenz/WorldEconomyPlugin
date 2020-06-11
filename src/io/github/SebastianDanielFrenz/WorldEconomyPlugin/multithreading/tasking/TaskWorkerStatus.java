package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

public class TaskWorkerStatus {

	public TaskWorkerStatus(Task[] tasks, boolean overloaded, int pending_tasks, float[] idle_durations,
			float[] working_durations) {
		this.tasks = tasks;
		this.overloaded = overloaded;
		this.pending_tasks = pending_tasks;
		this.idle_durations = idle_durations;
		this.working_durations = working_durations;
	}

	public final Task[] tasks;
	public final boolean overloaded;
	public final int pending_tasks;
	public final float[] idle_durations;
	public final float[] working_durations;

	public String[] getFormattedStatus() {
		String[] out = new String[tasks.length + 1];
		out[0] = "==========";
		out[0] += " " + tasks.length + " threads on " + Runtime.getRuntime().availableProcessors() + " logical cores ";
		out[0] += "=========";

		float load;

		for (int i = 0; i < tasks.length; i++) {
			out[i + 1] = "§eThread " + (i + 1) + "§f: §a";
			if (tasks[i] == null) {
				out[i + 1] += "---";
			} else {
				out[i + 1] += tasks[i].getName();
			}
			out[i + 1] += "§f; §eload: ";
			load = (working_durations[i] / (idle_durations[i] + working_durations[i]) * 100);
			if (load < 20) {
				out[i + 1] += "§1";
			} else if (load < 40) {
				out[i + 1] += "§3";
			} else if (load < 60) {
				out[i + 1] += "§2";
			} else if (load < 70) {
				out[i + 1] += "§a";
			} else if (load < 80) {
				out[i + 1] += "§e";
			} else if (load < 90) {
				out[i + 1] += "§6";
			} else if (load < 95) {
				out[i + 1] += "§c";
			} else {
				out[i + 1] += "§4";
			}
			out[i + 1] += Math.round(load) + "%";
		}
		return out;
	}

}
