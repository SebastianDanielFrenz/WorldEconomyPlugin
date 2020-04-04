package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;

@DataBaseRepresentation
public class AIProfile implements MailboxOwner, ResearchEntity {

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

	@Override
	public String getDisplayName() {
		return username;
	}

	@Override
	public String getResearchEntityType() {
		return "ai";
	}

	@Override
	public long getResearchSpecifiyEntityID() {
		return aiID;
	}
}
