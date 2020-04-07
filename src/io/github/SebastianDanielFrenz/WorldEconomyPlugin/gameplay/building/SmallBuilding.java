package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;

public abstract class SmallBuilding extends Building implements ActualBuilding {

	public SmallBuilding(String path) {
		this.path = path;
	}

	protected String path;

	@Override
	public int getSize() {
		return SMALL;
	}

	@Override
	public void place(Location center, CustomBlock pathway) throws InstantiationException, IllegalAccessException, IOException, SQLException {

		for (int x = 0; x < SMALL + 2; x++) {
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX() + x, center.getBlockY(), center.getBlockZ()), pathway);
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX() + x, center.getBlockY(), center.getBlockZ() + SMALL + 1),
					pathway);
		}
		for (int z = 0; z < SMALL + 2; z++) {
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX(), center.getBlockY(), center.getBlockZ() + z), pathway);
			CustomBlock.placeBlock(new Location(center.getWorld(), center.getBlockX() + SMALL + 1, center.getBlockY(), center.getBlockZ() + z),
					pathway);
		}

		BuildingIO.loadInternal(path, new Location(center.getWorld(), center.getBlockX() + 1, center.getBlockY(), center.getBlockZ() + 1));
	}

}
