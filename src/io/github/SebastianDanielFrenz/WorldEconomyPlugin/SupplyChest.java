package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import org.bukkit.Location;

public class SupplyChest {

	public long ID;
	public Location location;
	public long owningCompanyID;

	public SupplyChest(long ID, Location location, long owningCompanyID) {
		this.ID = ID;
		this.location = location;
		this.owningCompanyID = owningCompanyID;
	}

}
