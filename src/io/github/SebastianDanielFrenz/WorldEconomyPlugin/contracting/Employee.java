package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

public abstract class Employee {

	public long ID;

	public Employee(long ID) {
		this.ID = ID;
	}

	public abstract String getType();

}
