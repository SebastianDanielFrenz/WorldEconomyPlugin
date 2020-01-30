package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import java.util.UUID;

public class EmployeePlayer extends Employee {

	public EmployeePlayer(long employeeID, UUID playerUUID) {
		super(employeeID);
		this.playerUUID = playerUUID;
	}

	public UUID playerUUID;

	@Override
	public String getType() {
		return "player";
	}

}
