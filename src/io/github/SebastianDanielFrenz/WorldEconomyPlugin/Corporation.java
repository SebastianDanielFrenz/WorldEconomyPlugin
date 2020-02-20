package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

@DataBaseRepresentation
public class Corporation extends Company {

	public Corporation(long ID, String name, long employerID, long bankingID, long CEO_employeeID, long mailboxID) {
		super(ID, name, "corporation", employerID, bankingID, mailboxID);
		this.CEO_employeeID = CEO_employeeID;
	}

	public long CEO_employeeID;

	@Override
	public long getOwnerEmployeeID() {
		return CEO_employeeID;
	}

	@Override
	public String getDisplayName() {
		return companyName;
	}

}
