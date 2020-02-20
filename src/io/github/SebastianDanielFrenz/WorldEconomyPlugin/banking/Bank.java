package io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class Bank {

	public Bank(long ID, String name, double capital, long companyID) {
		this.ID = ID;
		this.name = name;
		this.capital = capital;
		this.companyID = companyID;
	}

	public long ID;
	public String name;
	public double capital;
	public long companyID;

}
