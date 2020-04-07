package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;

public class CustomBlockPlacerTask implements Runnable {

	public CustomBlockPlacerTask(List<Location> locations, List<CustomBlock> blocks) {
		this.locations = locations;
		this.blocks = blocks;
	}

	private List<Location> locations;
	private List<CustomBlock> blocks;

	@Override
	public void run() {
		//System.out.println("Running task!");

		for (int i = 0; i < locations.size(); i++) {
			try {
				//System.out.println(locations.get(i) + " -> " + blocks.get(i).ID);
				CustomBlock.placeBlock(locations.get(i), blocks.get(i));
			} catch (InstantiationException | IllegalAccessException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
