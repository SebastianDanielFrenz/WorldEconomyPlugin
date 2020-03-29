package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public abstract class Employee {

	public long ID;

	public long lastResearched; // saved in milliseconds

	public Employee(long ID, long lastResearched) {
		this.ID = ID;
		this.lastResearched = lastResearched;
	}

	public abstract String getType();

}
