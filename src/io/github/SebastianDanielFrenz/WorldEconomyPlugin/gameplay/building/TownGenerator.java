package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockRegistry;

public class TownGenerator {

	public static void generate(Location location) throws Exception {
		new MediumBuildingComposited(new SmallBuilding[] { BuildingRegistry.new_stone_age_house, BuildingRegistry.new_stone_age_house,
				BuildingRegistry.new_stone_age_house, BuildingRegistry.new_stone_age_house }).place(location, CustomBlockRegistry.sandstone);
	}

}
