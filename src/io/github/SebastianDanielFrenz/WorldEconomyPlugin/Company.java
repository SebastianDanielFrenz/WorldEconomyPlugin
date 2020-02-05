package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public abstract class Company implements MailboxOwner {

	public String companyName;
	public long companyEmployerID;
	public String companyType;
	public long ID;
	public long bankingID;

	public long mailboxID;

	public Company(long ID, String name, String type, long employerID, long bankingID, long mailboxID) {
		this.ID = ID;
		companyName = name;
		companyType = type;
		companyEmployerID = employerID;
		this.bankingID = bankingID;

		this.mailboxID = mailboxID;
	}

	public abstract long getOwnerEmployeeID();

	@Override
	public long getMailboxID() {
		return mailboxID;
	}

}
