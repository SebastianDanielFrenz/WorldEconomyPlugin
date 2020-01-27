package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class Corporation extends Company {

	public Corporation(long ID, String name, long employerID, long bankingID, long CEO_employeeID) {
		super(ID, name, "corporation", employerID, bankingID);
		this.CEO_employeeID = CEO_employeeID;
	}

	public long CEO_employeeID;

	@Override
	public long getOwnerEmployeeID() {
		return CEO_employeeID;
	}

}
