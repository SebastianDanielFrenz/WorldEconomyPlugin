package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research;

import java.sql.SQLException;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;

public class StatisticCategory {

	public final String ID;

	public StatisticCategory(String ID) {
		this.ID = ID;
		StatisticCategoryRegistry.register(this);
	}

	public double getValue(StatisticalObject object, long entityID, String entityType) throws SQLException {
		return WEDB.getStatistic(object.getStatisticID() + "$" + ID, entityID, entityType);
	}

}