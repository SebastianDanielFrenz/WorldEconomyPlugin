package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class PrivateCompany extends Company {

	public PrivateCompany(long ID, String name, long employerID, long bankingID, long ownerEmployeeID) {
		super(ID, name, "private", employerID, bankingID);
		this.ownerEmployeeID = ownerEmployeeID;
	}

	public long ownerEmployeeID;

	@Override
	public long getOwnerEmployeeID() {
		return ownerEmployeeID;
	}

}
