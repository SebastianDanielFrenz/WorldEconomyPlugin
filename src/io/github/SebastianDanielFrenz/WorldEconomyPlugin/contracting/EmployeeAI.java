package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

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
