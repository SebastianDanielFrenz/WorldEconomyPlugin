package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGrid;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGridRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling.TaskScheduler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

public class PowerDistributionSchedulerTask extends Task {

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void init() {
	}

	@Override
	public void work() {
		for (PowerGrid powerGrid:PowerGridRegistry.powerGrids) {
			TaskProcessor.registerTask(new PowerDistributionTask(powerGrid));
		}
	}

	@Override
	public void discard() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean startOnShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean continueOnShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFinished() {
		return true;
	}

	@Override
	public String getName() {
		return "Power Distribution Scheduler Task";
	}

	@Override
	public boolean discardOnOverload() {
		// TODO Auto-generated method stub
		return false;
	}

}
