package io.github.SebastianDanielFrenz.WorldEconomyPlugin.market;

import org.bukkit.Location;

public abstract class ChestData {

	public long ID;
	public String type;
	public Location location;

	public ChestData(long ID, String type, Location location) {
		this.ID = ID;
		this.type = type;
		this.location = location;
	}

}
