package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling;

import java.util.Comparator;

public class ScheduledTaskComparator implements Comparator<ScheduledTask> {

	/**
	 * This will return +1 if the task in arg0 has to be dealt with earlier.
	 */
	@Override
	public int compare(ScheduledTask arg0, ScheduledTask arg1) {
		if (arg0.measurementType != arg1.measurementType) {
			throw new RuntimeException("ScheduledTask objects " + arg0.toString() + " and " + arg1.toString()
					+ " could not be put in queue because they do not have the same time measurement type! This should never happen! This bug is probably caused in the TaskScheduler class.");
		}
		if (arg0.due < arg1.due) {
			return 1;
		}
		if (arg0.due == arg1.due) {
			return 0;
		}
		return -1;
	}

}
