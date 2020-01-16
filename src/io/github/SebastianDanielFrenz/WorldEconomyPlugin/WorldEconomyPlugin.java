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
			ResultSet res = runSQL("SELECT value FROM sys_enumerator WHERE key = \""+type+"\"");
		if (!res.next()) {
			throw new RuntimeException("The enumerator \""+type+"\" is not in the data base!");
			}
			return res.getInt(0);
		} catch (SQLException e) {
			throw new RuntimeException(e.getStackTrace().toString());
		}
	}
	
	public static void moveEnumerator(String type) {
		try {
			runSQL("UPDATE sys_enumerator SET value = value + 1 WHERE key = \""+type+"\"");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void registerUserProfile(OfflinePlayer player) throws SQLException {
		runSQL("INSERT INTO user_profiles (ID, employeeID, employerID, bankingID, username)" + " VALUES (\""
				+ player.getUniqueId().toString() + "\", " + getNextEnumerator("employeeID") + ", "
				+ getNextEnumerator("employerID") + ", " + getNextEnumerator("bankingID") + ", " + player.getName());
	}

	public static void registerUserBankAccount(OfflinePlayer player, BankAccount account) throws SQLException {
		runSQL("INSERT INTO bank_accounts (balance, bankID) VALUES (" + account.getBalance() + ", "
				+ account.getBankID());
	}
	
	private static void setupEnumerator() {
		runSQLsafe("INSERT INTO sys_enumerator (key, value) VALUES (\"bankingID\", 1), (\"employerID\", 1), (\"employeeID\", 1)");
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
			runSQL("CREATE TABLE user_profiles ("
					+ "UUID text PRIMARY KEY,"
					+ "employeeID integer,"
					+ "employerID integer,"
					+ "username text,"
					+ "bankingID integer"
					+ ");");
			
			runSQL("CREATE TABLE sys_enumerator ("
					+ "key text PRIMARY KEY,"
					+ "value integer"
					+ ");");
			
			runSQL("CREATE TABLE employee_matching ("
					+ "ID integer PRIMARY KEY,"
					+ "employerID integer,"
					+ "employeeID integer,"
					+ "contractID integer"
					+ ");");
			
			runSQL("CREATE TABLE credits ("
					+ "ID integer PRIMARY KEY,"
					+ "amount real,"
					+ "interest real,"
					+ "creditorID integer"
					+ ");");
			
			runSQL("CREATE TABLE contracts ("
					+ "ID integer PRIMARY KEY,"
					+ "type text,"
					+ "data text"
					+ ");");
			
			runSQL("CREATE TABLE companies ("
					+ "ID integer PRIMARY KEY,"
					+ "name text,"
					+ "type text,"
					+ "employerID integer"
					+ ");");
			
			runSQL("CREATE TABLE banks ("
					+ "ID integer PRIMARY KEY,"
					+ "bankname text"
					+ ");");
			
			runSQL("CREATE TABLE bank_accounts ("
					+ "ID integer PRIMARY KEY,"
					+ "balance real,"
					+ "bankID integer,"
					+ "customerBaningID integer,"
					+ "accountName text"
					+ ");");
			
			setupEnumerator();
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
