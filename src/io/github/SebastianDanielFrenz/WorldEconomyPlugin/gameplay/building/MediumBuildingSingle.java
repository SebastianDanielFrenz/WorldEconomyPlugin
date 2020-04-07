package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;

public abstract class MediumBuildingSingle extends MediumBuilding {

	public MediumBuildingSingle(String path) {
		this.path = path;
	}

	protected String path;

	@Override
	public void place(Location center, CustomBlock pathway) throws Exception {
		BuildingIO.loadInternal(path, center);
	}

}
