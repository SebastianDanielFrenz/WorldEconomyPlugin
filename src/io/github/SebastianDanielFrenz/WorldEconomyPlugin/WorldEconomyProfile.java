package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.UUID;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;

@DataBaseRepresentation
public class WorldEconomyProfile implements MailboxOwner, ResearchEntity {

	public long ID;
	public UUID uuid;
	public long employeeID;
	public long employerID;
	public String username;
	public long bankingID;
	public Age age;

	public long mailboxID;

	public WorldEconomyProfile(long ID, UUID uuid, long employeeID, long employerID, String username, long bankingID,
			long mailboxID, Age age) {
		this.ID = ID;
		this.uuid = uuid;
		this.employeeID = employeeID;
		this.employerID = employerID;
		this.username = username;
		this.bankingID = bankingID;

		this.mailboxID = mailboxID;
		this.age = age;
	}

	@Override
	public long getMailboxID() {
		return mailboxID;
	}

	@Override
	public String getDisplayName() {
		return username;
	}

	@Override
	public String getResearchEntityType() {
		return "player";
	}

	@Override
	public long getResearchSpecifiyEntityID() {
		return ID;
	}

}
