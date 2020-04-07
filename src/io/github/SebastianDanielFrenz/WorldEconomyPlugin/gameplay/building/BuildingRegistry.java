package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building.buildings.BuildingNewStoneAgeHouse;

public class BuildingRegistry {

	private static List<Building> buildings = new ArrayList<Building>();
	private static List<Building> Ss = new ArrayList<Building>();
	private static List<Building> Ms = new ArrayList<Building>();
	private static List<Building> Ls = new ArrayList<Building>();

	public static void register(Building building) {
		if (!(building instanceof ActualBuilding)) {
			throw new RuntimeException(
					"In order to be registered, " + building.getClass().getCanonicalName() + " needs to implement ActualBuilding!");
		}

		buildings.add(building);
		switch (building.getSize()) {
		case Building.SMALL:
			Ss.add(building);
			break;
		case Building.MEDIUM:
			Ms.add(building);
			break;
		case Building.LARGE:
			Ls.add(building);
			break;
		default:
			throw new RuntimeException("Unkown building size " + building.getSize() + "!");
		}
	}

	public static final SmallBuilding new_stone_age_house = new BuildingNewStoneAgeHouse("new_stone_age_house.schematic");

	public static void init() {
		register(new_stone_age_house);
	}

}
