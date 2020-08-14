package io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks;

import java.sql.SQLException;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;

public class SQLTask extends Task {

	private String query;

	public SQLTask(String query) {
		this.query = query;
	}

	@Override
	public int getPriority() {
		return -1000;
	}

	@Override
	public void init() {
	}

	@Override
	public void work() {
		try {
			WorldEconomyPlugin.runSQLasync(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		return "SQL Task";
	}

	@Override
	public boolean discardOnOverload() {
		return false;
	}

}
