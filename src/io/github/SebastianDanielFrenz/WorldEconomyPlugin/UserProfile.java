package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions.EmployeeProfession;

@DataBaseRepresentation
public class UserProfile extends PlayingEntity implements MailboxOwner, ResearchEntity {

	public long ID;
	public UUID uuid;
	public long employeeID;
	public long employerID;
	public String username;
	public long bankingID;

	public long mailboxID;

	public UserProfile(long ID, UUID uuid, long employeeID, long employerID, String username, long bankingID,
			long mailboxID, Set<EmployeeProfession> professions, double health, double maxHealth, double saturation,
			double happyness, boolean religious, double religious_satisfaction, double endurance, double max_endurance,
			boolean in_heaven, long heaven_time_end_millis, Age age) {
		super(employeeID, professions, health, maxHealth, saturation, happyness, religious, religious_satisfaction,
				endurance, max_endurance, in_heaven, heaven_time_end_millis, age);

		this.ID = ID;
		this.uuid = uuid;
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

	@Override
	public String getResearchEntityType() {
		return "player";
	}

	@Override
	public long getResearchSpecifiyEntityID() {
		return ID;
	}

	public List<ResearchItem> getResearchedItems() throws SQLException {
		return WEDB.getResearchItems(this);
	}

	@Override
	public Age getAge() {
		for (UserProfile profile : WorldEconomyPlugin.research_age_bypass) {
			if (profile.uuid.equals(uuid)) {
				return Age.UNDEFINED;
			}
		}
		return super.getAge();
	}

	/**
	 * Only use this method when writing to the DB. It does not include ascended
	 * players!
	 * 
	 * @return
	 */
	public Age getActualAge() {
		return super.getAge();
	}

	public boolean isAgeReal() {
		for (UserProfile profile : WorldEconomyPlugin.research_age_bypass) {
			if (profile.uuid.equals(uuid)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void kill() {
		try {
			WEDB.sendToHeaven(this, 1000);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
