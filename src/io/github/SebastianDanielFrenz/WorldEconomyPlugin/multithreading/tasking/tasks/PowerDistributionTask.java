package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.machine.electric.PowerGrid;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class PowerDistributionTask extends Task {

	public PowerDistributionTask(PowerGrid powerGrid) {
		this.powerGrid = powerGrid;
	}

	private PowerGrid powerGrid;

	@Override
	public int getPriority() {
		return 10000;
	}

	@Override
	public void init() {
	}

	@Override
	public void work() {
		powerGrid.distributePower();
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
		return true;
	}

	@Override
	public boolean hasFinished() {
		return true;
	}

	@Override
	public String getName() {
		return "Power Grid #" + powerGrid.ID + " Distribution";
	}

	@Override
	public boolean discardOnOverload() {
		return false;
	}

}
