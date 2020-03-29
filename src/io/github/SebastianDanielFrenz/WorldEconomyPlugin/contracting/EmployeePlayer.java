package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import java.util.UUID;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class EmployeePlayer extends Employee {

	public EmployeePlayer(long employeeID, long lastResearched, UUID playerUUID) {
		super(employeeID, lastResearched);
		this.playerUUID = playerUUID;
	}

	public UUID playerUUID;

	@Override
	public String getType() {
		return "player";
	}

}
