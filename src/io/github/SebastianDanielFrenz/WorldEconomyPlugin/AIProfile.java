package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

public class AIProfile implements MailboxOwner {

	public String username;
	public long bankingID;
	public long aiAsEmployerID;
	public long employeeID;
	public long aiID;

	public long mailboxID;

	public AIProfile(long aiID, String username, long bankingID, long employeeID, long aiAsEmployerID, long mailboxID) {
		this.aiID = aiID;
		this.username = username;
		this.bankingID = bankingID;
		this.employeeID = employeeID;
		this.aiAsEmployerID = aiAsEmployerID;

		this.mailboxID = mailboxID;
	}

	@Override
	public long getMailboxID() {
		return mailboxID;
	}
}
