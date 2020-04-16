package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;

public class CustomBlockPlacerTask implements Runnable {

	public CustomBlockPlacerTask(List<Location> locations, List<CustomBlockType> blocks) {
		this.locations = locations;
		this.blocks = blocks;
	}

	private List<Location> locations;
	private List<CustomBlockType> blocks;

	@Override
	public void run() {
		//System.out.println("Running task!");

		for (int i = 0; i < locations.size(); i++) {
			try {
				//System.out.println(locations.get(i) + " -> " + blocks.get(i).ID);
				CustomBlockType.placeBlock(locations.get(i), blocks.get(i));
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
