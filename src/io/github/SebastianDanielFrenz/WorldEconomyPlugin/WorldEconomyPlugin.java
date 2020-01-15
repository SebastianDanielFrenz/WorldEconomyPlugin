package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.OfflinePlayer;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankingProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.EmployeeProfile;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccountHolderType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankCustomer;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.employment.EmploymentContract;

import net.milkbowl.vault.economy.Economy;

public class WorldEconomyPlugin extends JavaPlugin {

	public static Economy economy;
	public static Connection sql_connection;

	public static List<WorldEconomyProfile> global_user_profiles = new ArrayList<WorldEconomyProfile>();

	@Override
	public void onEnable() {

		// register tick counter

		if (!setupEconomy()) {
			getLogger().info("ERROR: Could not hook into Vault!");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		try {
			if (setupSQL()) {
				getLogger().info("Created databases!");
			}
		} catch (SQLException e) {
			getLogger().log(Level.SEVERE, "Failed to setup the databases. The error is as follows:");
			getLogger().log(Level.SEVERE, e.getStackTrace().toString());
		}
	}

	@Override
	public void onDisable() {
	}

	public static long tick_counter;

	public static WorldEconomyProfile getUserProfile(OfflinePlayer player) throws SQLException {
		ResultSet r = runSQL("SELECT employeeID, employerID, username, bankingID FROM user_profiles WHERE ID = \""+player.getUniqueId()+"\"");
		if (r.next()) {
			return new WorldEconomyProfile(player.getUniqueId(), r.getInt("employeeID"), r.getInt("employerID"), r.getString("username"), r.getInt("bankingID"));
		}
		else {
			return null;
		}
	}
	
	public static int getNextEnumerator(String type) {
		try {
			ResultSet res = runSQL("SELECT status FROM sys_enumerator WHERE type = \""+type+"\"");
		if (!res.next()) {
			throw new RuntimeException("The enumerator \""+type+"\" is not in the data base!");
			}
			return res.getInt(0);
		} catch (SQLException e) {
			throw new RuntimeException(e.getStackTrace().toString());
		}
	}

	public static void registerUserProfile(OfflinePlayer player) throws SQLException {
		runSQL("INSERT INTO user_profiles (ID, employeeID, employerID, bankingID, username) VALUES (\""+player.getUniqueId().toString()+"\", ");
	}

	public static void registerUserBankAccount(OfflinePlayer player, BankAccount account) {
		getUserProfile(player.getUniqueId()).bankingProfile.getBankCustomer(account.getBank())
				.createAccount(account.getBank(), account.getName());
	}

	public static void registerUserBankCustomer(OfflinePlayer player, BankCustomer customer) {
		getUserProfile(player).bankingProfile.registerBankCustomer(customer);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return economy != null;
	}

	@SuppressWarnings("unchecked")
	private boolean setupSQL() throws SQLException {
		boolean is_new = Files.exists(Paths.get(getDataFolder().toString() + "/data.db"));

		sql_connection = DriverManager.getConnection("jdbc:sqlite:" + getDataFolder().toString() + "/data.db");

		// prepare DB

		if (is_new) {
			runSQL("CREATE TABLE " + T_GLOBAL_USER_PROFILES + " (\n"
					+ "id text PRIMARY KEY\n" + ");");
			// runSQL("CREATE TABLE "+T_BANK_PROFILES);

			/*
			 * runSQL("CREATE TABLE "+T_BANK_ACCOUNTS+" (\n" +
			 * "id integer PRIMARY KEY,\n" + "balance BLOB NOT NULL,\n" +
			 * "name text NOT NULL\n" + ");");
			 */

			runSQL("CREATE TABLE " + T_BANK_PROFILES + " (\n"
			+ "player_id text PRIMARY KEY,\n"
			+ "customer_profiles BLOB NOT NULL\n" + ");");
		}

		// load DB data into class structure

		ResultSet globalProfileResult = runSQL("SELECT id FROM " + T_GLOBAL_USER_PROFILES + ";");

		while (globalProfileResult.next()) {
			String id = globalProfileResult.getString("id");

			ResultSet bankProfileResult = runSQL(
					"SELECT customer_profiles FROM " + T_BANK_PROFILES + " WHERE player_id = \"" + id + "\";");
			ArrayList<BankCustomer> bankCustomers;

			if (bankProfileResult.next()) {

				bankCustomers = ((ArrayList<BankCustomer>) bankProfileResult.getObject("customer_profiles"));
			} else {
				throw new RuntimeException("no bank customer profiles for player with id \"" + id + "\"!");
			}

			OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(id));
			registerUserProfile(player);
			for (BankCustomer customer : bankCustomers) {
				registerUserBankCustomer(player, customer);
			}

		}

		return is_new;
	}

	public static ResultSet runSQL(String query) throws SQLException {
		return sql_connection.createStatement().executeQuery(query);
	}

	public static ResultSet runSQLsafe(String query) {
		try {
			return sql_connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final String T_GLOBAL_USER_PROFILES = "global_user_profiles";
	public static final String T_BANK_PROFILES = "bank_profiles";
	public static final String T_BANK_ACCOUNT_MATCHING = "bank_account_matching";
	public static final String T_BANK_ACCOUNTS = "bank_accounts";
}
