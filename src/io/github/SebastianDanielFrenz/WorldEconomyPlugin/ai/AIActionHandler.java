package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.AIBaseActionTask;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;

public class AIActionHandler {
	
	public static Map<AIAction,AIActionCondition> conditional_behaviour = new TreeMap<AIAction,AIActionCondition>();

	public static void init() {
		try {
			for (AIProfile profile : WEDB.getAllAIs()) {
				TaskProcessor.registerTask(new AIBaseActionTask(profile));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
