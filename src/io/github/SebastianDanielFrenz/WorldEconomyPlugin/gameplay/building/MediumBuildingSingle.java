package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;

public abstract class MediumBuildingSingle extends MediumBuilding {

	public MediumBuildingSingle(String path) {
		this.path = path;
	}

	protected String path;

	@Override
	public void place(Location center, CustomBlockType pathway) throws Exception {
		BuildingIO.loadInternal(path, center);
	}

}
