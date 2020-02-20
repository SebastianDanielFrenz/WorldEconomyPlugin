package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;

@DataBaseRepresentation
public class ContractEmploymentDefault extends Contract {

	public ContractEmploymentDefault(long ID, double salary, int last_salary) {
		super(ID);
		this.salary = salary;
		this.last_salary = last_salary;
	}

	public double salary;
	public int last_salary;

	@Override
	public String getType() {
		return "employment.default";
	}

}
