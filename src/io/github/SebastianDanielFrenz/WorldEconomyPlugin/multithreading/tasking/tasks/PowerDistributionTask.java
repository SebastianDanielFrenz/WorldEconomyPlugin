package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class PowerDistributionTask extends Task {

	@Override
	public int getPriority() {
		return 10000;
	}

	@Override
	public void init() {
	}

	@Override
	public void work() {

	}

	@Override
	public void discard() {
	}

	@Override
	public boolean startOnShutdown() {
		return true;
	}

	@Override
	public boolean continueOnShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean discardOnOverload() {
		// TODO Auto-generated method stub
		return false;
	}

}
