package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.UUID;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;

@DataBaseRepresentation
public class WorldEconomyProfile implements MailboxOwner {

	public UUID uuid;
	public long employeeID;
	public long employerID;
	public String username;
	public long bankingID;

	public long mailboxID;

	public WorldEconomyProfile(UUID uuid, long employeeID, long employerID, String username, long bankingID,
			long mailboxID) {
		this.uuid = uuid;
		this.employeeID = employeeID;
		this.employerID = employerID;
		this.username = username;
		this.bankingID = bankingID;

		this.mailboxID = mailboxID;
	}

	@Override
	public long getMailboxID() {
		return mailboxID;
	}

	@Override
	public String getDisplayName() {
		return username;
	}

}
