package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class ScheduledRepeatingTask extends ScheduledTask {

	public ScheduledRepeatingTask(Task task, long due_time, long interval) {
		super(task, TimeMeasurementType.TICKS, due_time);
		this.interval = interval;
	}

	public final long interval;

	public long timeUntilDueAgain() {
		if (due > WorldEconomyPlugin.tick_counter) {
			return due - WorldEconomyPlugin.tick_counter;
		}
		long ticks_since_due = WorldEconomyPlugin.tick_counter - due;
		if (ticks_since_due % interval == 0) {
			return 0;
		}
		return interval - ticks_since_due % interval;
	}

}
