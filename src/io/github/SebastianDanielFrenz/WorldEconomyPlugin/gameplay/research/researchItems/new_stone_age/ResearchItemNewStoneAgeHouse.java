package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.researchItems.new_stone_age;

import org.bukkit.Material;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building.BuildingRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchCondition;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;

public class ResearchItemNewStoneAgeHouse extends ResearchItem {

	public ResearchItemNewStoneAgeHouse() {
		super("new_stone_age_house", BuildingRegistry.new_stone_age_house, new ResearchItem[] {}, new ResearchCondition[] {}, Age.NEW_STONE_AGE,
				"House (New Stone Age)", Material.WOOD_DOOR, 0);
	}

}