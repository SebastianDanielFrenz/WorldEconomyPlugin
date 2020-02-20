package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

@DataBaseRepresentation
public class Employer {

	public long ID;
	public long bankingID;

	public Employer(long ID, long bankingID) {
		this.ID = ID;
		this.bankingID = bankingID;
	}

}
