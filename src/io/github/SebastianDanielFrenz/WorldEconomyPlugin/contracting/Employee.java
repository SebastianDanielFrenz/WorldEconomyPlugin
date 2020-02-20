package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public abstract class Employee {

	public long ID;

	public Employee(long ID) {
		this.ID = ID;
	}

	public abstract String getType();

}
