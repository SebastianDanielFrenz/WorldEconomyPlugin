package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.Set;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions.EmployeeProfession;

@DataBaseRepresentation
public class AIProfile extends PlayingEntity implements MailboxOwner, ResearchEntity {

	public String username;
	public long bankingID;
	public long aiAsEmployerID;
	public long employeeID;
	public long aiID;

	public long mailboxID;

	public AIProfile(long aiID, String username, long bankingID, long employeeID, long aiAsEmployerID, long mailboxID,
			Set<EmployeeProfession> professions, double health, double maxHealth, double saturation, double happyness,
			boolean religious, double religious_satisfaction, double endurance, double max_endurance, boolean in_heaven,
			long heaven_time_end_millis, Age age) {
		super(professions, health, maxHealth, saturation, happyness, religious, religious_satisfaction, endurance,
				max_endurance, in_heaven, heaven_time_end_millis, age);

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
