package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;

@DataBaseRepresentation
public class UserProfile implements MailboxOwner, ResearchEntity {

	public long ID;
	public UUID uuid;
	public long employeeID;
	public long employerID;
	public String username;
	public long bankingID;
	private Age age;

	public long mailboxID;

	public UserProfile(long ID, UUID uuid, long employeeID, long employerID, String username, long bankingID,
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

	public List<ResearchItem> getResearchedItems() throws SQLException {
		return WEDB.getResearchItems(this);
	}

	public Age getAge() {
		for (UserProfile profile : WorldEconomyPlugin.research_passby) {
			if (profile.uuid.equals(uuid)) {
				return Age.UNDEFINED;
			}
		}
		return age;
	}

	/**
	 * Only use this method when writing to the DB. It does not include ascended
	 * players!
	 * 
	 * @return
	 */
	public Age getActualAge() {
		return age;
	}

	public boolean isAgeReal() {
		for (UserProfile profile : WorldEconomyPlugin.research_passby) {
			if (profile.uuid.equals(uuid)) {
				return false;
			}
		}
		return true;
	}

}
