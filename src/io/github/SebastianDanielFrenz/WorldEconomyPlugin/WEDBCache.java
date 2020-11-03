package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.SQLException;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai.AIProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public class WEDBCache {

	public static List<UserProfile> user_profiles;
	public static List<AIProfile> ai_profiles;
	public static List<BankAccount> bank_accounts;

	public void init() throws SQLException {
		// pls implement
		int I;

		user_profiles = WEDB.getAllUserProfiles();
		ai_profiles = WEDB.getAllAIs();
	}

}
