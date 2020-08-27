package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.LiveGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUIRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

public class LiveGUIUpdateSchedulerTask extends Task {

	@Override
	public void work() {
		for (LiveGUI gui : WEGUIRegistry.getLiveGUIs()) {
			if (gui.getLastUpdate() >= 5) {
				TaskProcessor.registerTask(new LiveGUIUpdaterTask(gui));
			}
		}
	}

	@Override
	public boolean startOnShutdown() {
		return false;
	}

	@Override
	public void init() {

	}

	@Override
	public boolean hasFinished() {
		return true;
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public String getName() {
		return "Live GUI Updater";
	}

	@Override
	public boolean discardOnOverload() {
		return false;
	}

	@Override
	public void discard() {
	}

	@Override
	public boolean continueOnShutdown() {
		return false;
	}

}
