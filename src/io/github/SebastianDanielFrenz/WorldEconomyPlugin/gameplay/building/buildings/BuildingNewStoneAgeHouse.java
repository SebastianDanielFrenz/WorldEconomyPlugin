package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building.buildings;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building.ActualBuilding;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building.SmallBuilding;

public class BuildingNewStoneAgeHouse extends SmallBuilding {

	public BuildingNewStoneAgeHouse(String path) {
		super(path);
	}

	@Override
	public ActualBuilding getNextLevel() {
		return null;
	}

	@Override
	public Age getNextLevelAge() {
		return null;
	}

}
