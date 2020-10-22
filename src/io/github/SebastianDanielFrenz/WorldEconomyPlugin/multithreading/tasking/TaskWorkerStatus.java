package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Units;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.SQLHandlerThread;

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
		String[] out = new String[tasks.length + 3];
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

		out[out.length - 2] = "§eSQL Thread§f: §e" + SQLHandlerThread.queueLength() + "waiting§f; §eload: ";
		load = (SQLHandlerThread.getWorkingTime()
				/ (SQLHandlerThread.getIdleTime() + SQLHandlerThread.getWorkingTime() * 100));
		if (load < 20) {
			out[out.length - 2] += "§1";
		} else if (load < 40) {
			out[out.length - 2] += "§3";
		} else if (load < 60) {
			out[out.length - 2] += "§2";
		} else if (load < 70) {
			out[out.length - 2] += "§a";
		} else if (load < 80) {
			out[out.length - 2] += "§e";
		} else if (load < 90) {
			out[out.length - 2] += "§6";
		} else if (load < 95) {
			out[out.length - 2] += "§c";
		} else {
			out[out.length - 2] += "§4";
		}
		out[out.length - 2] += Math.round(load) + "%";

		double ram_usage = (1 - Runtime.getRuntime().freeMemory() / (double) Runtime.getRuntime().maxMemory());

		out[out.length - 1] = "§eRAM: ";

		if (ram_usage < 20) {
			out[out.length - 1] += "§1";
		} else if (ram_usage < 40) {
			out[out.length - 1] += "§3";
		} else if (ram_usage < 60) {
			out[out.length - 1] += "§2";
		} else if (ram_usage < 70) {
			out[out.length - 1] += "§a";
		} else if (ram_usage < 80) {
			out[out.length - 1] += "§e";
		} else if (ram_usage < 90) {
			out[out.length - 1] += "§6";
		} else if (ram_usage < 95) {
			out[out.length - 1] += "§c";
		} else {
			out[out.length - 1] += "§4";
		}

		out[out.length - 1] += Math
				.round(Runtime.getRuntime().freeMemory() / (double) Runtime.getRuntime().maxMemory() * 100) + "% (";

		long usage = (Runtime.getRuntime().freeMemory());
		if (usage < Units.GB * 8) {
			out[out.length - 1] += usage / Units.MB + "MB";
		} else if (usage < Units.GB * 32) {
			out[out.length - 1] += Math.round((usage / (double) Units.GB) * 10) / 10.0 + "GB";
		} else if (usage < Units.TB * 4) {
			out[out.length - 1] += usage / Units.GB + "GB";
		} else {
			out[out.length - 1] += Math.round((usage / (double) Units.TB) * 10) / 10.0 + "TB";
		}

		out[out.length - 1] += "/";
		long max = Runtime.getRuntime().maxMemory();

		if (max < Units.GB * 8) {
			out[out.length - 1] += max / Units.MB + "MB";
		} else if (max < Units.GB * 32) {
			out[out.length - 1] += Math.round((max / (double) Units.GB) * 10) / 10.0 + "GB";
		} else if (max < Units.TB * 4) {
			out[out.length - 1] += max / Units.GB + "GB";
		} else {
			out[out.length - 1] += Math.round((max / (double) Units.TB) * 1000) / 1000.0 + "TB";
		}

		out[out.length - 1] += ")";

		return out;
	}

}
