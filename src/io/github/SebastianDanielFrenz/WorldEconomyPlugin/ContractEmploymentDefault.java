package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;

public class ContractEmploymentDefault extends Contract {

	public ContractEmploymentDefault(long ID, double salary) {
		super(ID);
		this.salary = salary;
	}

	public double salary;

	@Override
	public String getType() {
		return "employment.default";
	}

}
