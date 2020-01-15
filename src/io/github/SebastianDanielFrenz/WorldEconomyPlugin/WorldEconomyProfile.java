package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.UUID;

public class WorldEconomyProfile {

	public UUID uuid;
	public int employeeID;
	public int employerID;
	public String username;
	public int bankingID;

	public WorldEconomyProfile(UUID uuid, int employeeID, int employerID, String username, int bankingID) {
		this.uuid = uuid;
		this.employeeID = employeeID;
		this.employerID = employerID;
		this.username = username;
		this.bankingID = bankingID;
	}

}
