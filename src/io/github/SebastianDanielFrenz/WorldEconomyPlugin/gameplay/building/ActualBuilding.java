package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchableObject;

public interface ActualBuilding extends ResearchableObject {

	public ActualBuilding getNextLevel();

	public Age getNextLevelAge();

	public default boolean hasUpgradePath() {
		return getNextLevel() != null;
	}

}
