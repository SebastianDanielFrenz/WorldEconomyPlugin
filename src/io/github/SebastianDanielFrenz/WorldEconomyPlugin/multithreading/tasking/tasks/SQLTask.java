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
		// TODO Auto-generated method stub
		return false;
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
