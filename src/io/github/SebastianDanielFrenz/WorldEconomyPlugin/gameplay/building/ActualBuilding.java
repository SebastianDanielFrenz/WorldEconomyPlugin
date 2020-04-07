package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public interface ActualBuilding {

	public ActualBuilding getNextLevel();

	public Age getNextLevelAge();

	public default boolean hasUpgradePath() {
		return getNextLevel() != null;
	}

}
