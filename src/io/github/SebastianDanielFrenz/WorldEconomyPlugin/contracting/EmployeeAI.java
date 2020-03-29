package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class EmployeeAI extends Employee {

	public long aiID;

	public EmployeeAI(long ID, long lastResearched, long aiID) {
		super(ID, lastResearched);

		this.aiID = aiID;
	}

	@Override
	public String getType() {
		return "AI";
	}

}
