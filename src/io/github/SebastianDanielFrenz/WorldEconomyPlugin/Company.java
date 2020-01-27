package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public abstract class Company {

	public String companyName;
	public long companyEmployerID;
	public String companyType;
	public long ID;
	public long bankingID;

	public Company(long ID, String name, String type, long employerID, long bankingID) {
		this.ID = ID;
		companyName = name;
		companyType = type;
		companyEmployerID = employerID;
		this.bankingID = bankingID;
	}
	
	public abstract long getOwnerEmployeeID();

}
