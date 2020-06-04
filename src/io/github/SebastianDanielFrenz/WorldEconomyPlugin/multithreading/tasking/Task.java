package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking;

public abstract class Task {

	private boolean started = false;

	public abstract int getPriority();

	public void setup() {
		started = true;
	}

	public abstract void init();

	public abstract void work();

	/**
	 * This is called if the task is discarded before being completed during
	 * shutdown.
	 */
	public abstract void discard();

	public abstract boolean startOnShutdown();

	public abstract boolean continueOnShutdown();

	public abstract boolean hasFinished();

	public boolean hasStarted() {
		return started;
	}
	
	public abstract String getName();

}
