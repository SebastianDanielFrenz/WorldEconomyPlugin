package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.LiveGUI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class LiveGUIUpdaterTask extends Task {

	private LiveGUI gui;

	public LiveGUIUpdaterTask(LiveGUI gui) {
		this.gui = gui;
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public void init() {
	}

	@Override
	public void work() {
		gui.updateInventoryView();
	}

	@Override
	public void discard() {
	}

	@Override
	public boolean startOnShutdown() {
		return false;
	}

	@Override
	public boolean continueOnShutdown() {
		return false;
	}

	@Override
	public boolean hasFinished() {
		return true;
	}

	@Override
	public String getName() {
		return "Live GUI Updater";
	}

	@Override
	public boolean discardOnOverload() {
		return false;
	}

}
