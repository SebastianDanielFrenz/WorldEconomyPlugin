package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

@DataBaseRepresentation
public class BankCompany extends Company {

	public BankCompany(long companyID, String name, long employerID, long bankingID, long mailboxID, long bankID) {
		super(companyID, name, "bank", employerID, bankingID, mailboxID);

		this.bankID = bankID;
	}

	public long bankID;

	@Override
	public String getDisplayName() {
		return companyName;
	}

	@Override
	public long getOwnerEmployeeID() {
		throw new RuntimeException("Banks do not have owners yet!");
	}

}
