package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task arg0, Task arg1) {
		return arg0.getPriority() > arg1.getPriority() ? 1 : arg0.getPriority() == arg1.getPriority() ? 0 : -1;
	}

}
