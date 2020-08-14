package io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;

@DataBaseRepresentation
public class JobOffer {

	public JobOffer(long companyPositionID, double salary, int positions) {
		this.companyPositionID = companyPositionID;
		this.salary = salary;
		this.positions = positions;
	}

	public double salary;
	public long companyPositionID;
	public int positions;

}
