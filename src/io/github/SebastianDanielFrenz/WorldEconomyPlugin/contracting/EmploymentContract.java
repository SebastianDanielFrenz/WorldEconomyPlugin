package io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting;

import java.util.Map;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Utils;

public class EmploymentContract extends Contract {

	public EmploymentContract(long ID, double salary) {
		super(ID, "employment.default", "salary=" + salary);
	}

	public EmploymentContract(long ID, String data) {
		super(ID, "employment.default", data);
	}

	public EmploymentContract(long ID, String type, String data) {
		super(ID, type, data);
	}

	private double salary;

	@Override
	public void loadData(String rawData) {
		Map<String, String> data = Utils.getSepData(rawData);
		salary = Double.parseDouble(data.get("salary"));
	}

	@Override
	public String saveData() {
		return "salary=" + salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
