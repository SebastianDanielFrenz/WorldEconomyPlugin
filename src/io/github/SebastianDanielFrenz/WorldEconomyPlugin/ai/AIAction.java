package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public abstract class AIAction extends Task {

	public final AIAction parent;

	public AIAction(AIAction parent) {
		this.parent = parent;
	}

}
