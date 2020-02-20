package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class EmployeeAI extends Employee {

	public long aiID;

	public EmployeeAI(long ID, long aiID) {
		super(ID);

		this.aiID = aiID;
	}

	@Override
	public String getType() {
		return "AI";
	}

}
