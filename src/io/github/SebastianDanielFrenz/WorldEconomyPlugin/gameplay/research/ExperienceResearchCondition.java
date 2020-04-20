package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.sql.SQLException;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class ExperienceResearchCondition extends ResearchCondition {

	public ExperienceResearchCondition(StatisticalObject statisticalObject, StatisticCategory category, double min) {
		this.statisticalObject = statisticalObject;
		this.min = min;
		this.category = category;
	}

	private StatisticalObject statisticalObject;
	private double min;
	private StatisticCategory category;

	@Override
	public boolean isMet(long entityID, String entityType) {
		try {
			return category.getValue(statisticalObject, entityID, entityType) >= min;
		} catch (SQLException e) {
			e.printStackTrace();
			WorldEconomyPlugin.plugin.getLogger().info("Could not evaluate experience research condition "
					+ statisticalObject + ":" + category.ID + " >= " + min + " for " + entityType + "" + entityID);
			return false;
		} catch (NullPointerException e) {
			WorldEconomyPlugin.plugin.getLogger()
					.info("Could not evaluate research condition " + statisticalObject + ":" + category.ID + " >= "
							+ min + " for " + entityType + "" + entityID
							+ " because no database entry could be found!");
			return false;
		}

	}

}
