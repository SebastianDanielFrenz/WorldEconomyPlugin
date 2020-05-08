package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class ScheduledTask {

	public final Task task;
	public final TimeMeasurementType measurementType;
	public final long due;

	public ScheduledTask(Task task, TimeMeasurementType type, long due_time) {
		this.task = task;
		measurementType = type;
		due = due_time;
	}

}
