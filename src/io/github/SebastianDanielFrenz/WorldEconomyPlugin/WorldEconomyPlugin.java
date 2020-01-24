package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.ShopSignData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.SignData;
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
			throw new RuntimeException("Could not create DBs!", e);
		}

		getServer().getPluginManager().registerEvents(new JoinListener(), this);

		getCommand("we").setExecutor(new WorldEconomyCommandExecutor());
	}

	@Override
	public void onDisable() {
		try {
			sql_connection.commit();
			sql_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static long tick_counter;

	public static WorldEconomyProfile getUserProfile(OfflinePlayer player) throws SQLException {
		ResultSet r = runSQLquery("SELECT employeeID, employerID, username, bankingID FROM user_profiles WHERE ID = \""
				+ player.getUniqueId() + "\"");
		if (r.next()) {
			return new WorldEconomyProfile(player.getUniqueId(), r.getInt("employeeID"), r.getInt("employerID"),
					r.getString("username"), r.getInt("bankingID"));
		} else {
			return null;
		}
	}

	public static long getNextEnumerator(String type) {
		try {
			ResultSet res = runSQLquery("SELECT value FROM sys_enumerator WHERE key = \"" + type + "\"");
			if (!res.next()) {
				throw new RuntimeException("The enumerator \"" + type + "\" is not in the data base!");
			}
			return res.getLong("value");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void moveEnumerator(String type) {
		try {
			runSQL("UPDATE sys_enumerator SET value = value + 1 WHERE key = \"" + type + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void registerUserProfile(OfflinePlayer player) throws SQLException {
		runSQL("INSERT INTO user_profiles (playerUUID, employeeID, playerAsEmployerID, bankingID, username)"
				+ " VALUES (\"" + player.getUniqueId().toString() + "\", " + getNextEnumerator("employeeID") + ", "
				+ getNextEnumerator("employerID") + ", " + getNextEnumerator("bankingID") + ", " + player.getName());
	}

	public static void registerUserBankAccount(OfflinePlayer player, BankAccount account) throws SQLException {
		runSQL("INSERT INTO bank_accounts (bankAccountBalance, bankID, bankAccountName, customerBankingID) VALUES ("
				+ account.getBalance() + ", " + account.getBankID() + ", \"" + account.getName() + "\", "
				+ account.getAccountHolderID() + ")");
	}

	public static BankAccount getBankAccount(long bankingID, String name) throws SQLException {
		ResultSet r = runSQLquery("SELECT * FROM bank_accounts WHERE bankAccountHolderID = " + bankingID);
		if (r.next()) {
			return new BankAccount(r.getLong("bankID"), r.getDouble("bankAccountBalance"), name,
					r.getLong("bankAccountHolderID"));
		} else {
			return null;
		}
	}

	public static void registerBank(String name) throws SQLException {
		runSQL("INSERT INTO banks (bankID, bankName) VALUES (" + getNextEnumerator("bankID") + ", \"" + name + "\")");
		moveEnumerator("bankID");
	}

	public static long registerSupplyChest(Location location, long companyID) throws SQLException {
		long chestID = getNextEnumerator("chestID");

		runSQL("INSERT INTO chests (chestID, chestX, chestY, chestZ, chestWorld, chestType) VALUES" + "(" + chestID
				+ ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ", \""
				+ location.getWorld().getName() + "\", \"supply\")");

		runSQL("INSERT INTO supply_chests (chestID, chestOwnerCompanyID) VALUES (" + chestID + ", " + companyID + ")");

		return chestID;
	}

	public static SupplyChest getSupplyChest(Location location) throws SQLException {
		ResultSet res = runSQLquery("SELECT * FROM supply_chests WHERE signX = " + location.getBlockX()
				+ " AND signY = " + location.getBlockY() + " AND signZ = " + location.getBlockZ() + " AND signWorld = "
				+ location.getWorld().getName());
		if (res.next()) {
			ResultSet res2 = runSQLquery("SELECT * FROM chests WHERE chestID = " + res.getLong("chestID"));

			return new SupplyChest(res.getLong("chestID"), location, res.getLong("chestOwnerCompanyID"));
		} else {
			return null;
		}
	}

	public static SupplyChest getSupplyChest(long ID) throws SQLException {
		ResultSet res = runSQLquery("SELECT * FROM supply_chests WHERE chestID = " + ID);
		if (res.next()) {
			ResultSet res2 = runSQLquery("SELECT * FROM chests WHERE chestID = " + res.getLong("chestID"));

			return new SupplyChest(ID, new Location(Bukkit.getWorld(res.getString("chestWorld")), res.getInt("chestX"),
					res.getInt("chestY"), res.getInt("chestZ")), res.getLong("chestOwnerCompanyID"));
		} else {
			return null;
		}
	}

	public static long registerShopSign(Location location, long productID, double price) throws SQLException {
		return registerShopSign(location, productID, price, 0);
	}

	public static long registerShopSign(Location location, long productID, double price, long supplyChestID)
			throws SQLException {
		long signID = getNextEnumerator("signID");

		runSQL("INSERT INTO signs (signID, signX, signY, signZ, signWorld, signType) VALUES" + " (" + signID + ", "
				+ location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ", \""
				+ location.getWorld().getName() + "\", " + "\"shop\")");

		return signID;
	}

	public static long registerCompany(String name, String type) throws SQLException {
		long companyID = getNextEnumerator("companyID");

		runSQL("INSERT INTO companies (companyID, companyName, companyType, companyEmployerID) VALUES (" + companyID
				+ ", \"" + name + "\", \"" + type + "\", " + getNextEnumerator("employerID") + ")");

		moveEnumerator("companyID");
		moveEnumerator("employerID");

		return companyID;
	}
	
	public static Company getCompany(String name) {
		
	}

	public static long registerProduct(long productManifacturerID, String name, double price) throws SQLException {
		long productID = getNextEnumerator("productID");

		runSQL("INSERT INTO products (productID, productName, productPrice, productManifacturerID) VALUES (" + productID
				+ ", \"" + name + "\", " + price + ", " + productManifacturerID + ")");

		moveEnumerator("productID");

		return productID;
	}

	public static SignData getSign(Location location) throws SQLException {
		ResultSet res = runSQLquery("SELECT * FROM signs WHERE signX = " + location.getBlockX() + " AND signY = "
				+ location.getBlockY() + " AND signZ = " + location.getBlockZ() + " AND signWorld = \""
				+ location.getWorld().getName() + "\"");

		if (!res.next()) {
			return null;
		}

		if (res.getString("signType").equals("shop")) {
			ResultSet res2 = runSQLquery("SELECT * FROM shop_signs WHERE signID = " + res.getLong("signID"));

			return new ShopSignData(res.getLong("signID"), res.getInt("x"), res.getInt("y"), res.getInt("z"),
					Bukkit.getWorld(res.getString("signWorld")), res.getString("signType"),
					res2.getLong("supplyChestID"), res2.getLong("signProductID"), res2.getDouble("signPrice"));
		}

		throw new RuntimeException("sign type not supported!");
	}

	public static ShopSignData getShopSign(Location location) throws SQLException {
		return (ShopSignData) getSign(location);
	}

	private static void setupEnumerator() {
		runSQLsafe(
				"INSERT INTO sys_enumerator (key, value) VALUES (\"bankingID\", 1), (\"employerID\", 1), (\"employeeID\", 1), (\"chestID\", 1),"
						+ "(\"signID\", 1), (\"bankID\", 1), (\"bankAccountID\", 1), (\"companyID\", 1), (\"productID\", 1)");
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
		boolean is_new = !Files.exists(Paths.get(getDataFolder().toString() + "/data.db"));

		sql_connection = DriverManager.getConnection("jdbc:sqlite:" + getDataFolder().toString() + "/data.db");

		// prepare DB

		if (is_new) {
			runSQL("CREATE TABLE user_profiles (" + "playerUUID text PRIMARY KEY," + "employeeID integer,"
					+ "playerAsEmployerID integer," + "username text," + "playerBankingID integer" + ");");

			runSQL("CREATE TABLE sys_enumerator (" + "key text PRIMARY KEY," + "value integer" + ");");

			runSQL("CREATE TABLE employee_matching (" + "employee_matchingID integer PRIMARY KEY,"
					+ "employerID integer," + "employeeID integer," + "contractID integer" + ");");

			runSQL("CREATE TABLE credits (" + "creditID integer PRIMARY KEY," + "creditAmount real,"
					+ "creditInterest real," + "creditorBankingID integer" + ");");

			runSQL("CREATE TABLE contracts (" + "contractID integer PRIMARY KEY," + "contracType text,"
					+ "contractData text" + ");");

			runSQL("CREATE TABLE companies (" + "companyID integer PRIMARY KEY," + "companyName text,"
					+ "companyType text," + "companyEmployerID integer" + ");");

			runSQL("CREATE TABLE banks (" + "bankID integer PRIMARY KEY," + "bankName text" + ");");

			runSQL("CREATE TABLE bank_accounts (" + "bankAccountID integer PRIMARY KEY," + "bankAccountBalance real,"
					+ "bankID integer," + "customerBankingID integer," + "customerType text," + "bankAccountName text"
					+ ");");

			runSQL("CREATE TABLE products (" + "productID integer PRIMARY KEY," + "productManifacturerID integer,"
					+ "productPrice real," + "productName text" + ");");

			runSQL("CREATE TABLE chests (" + "chestID integer PRIMARY KEY," + "chestType text," + "chestX integer,"
					+ "chestY integer," + "chestZ integer," + "chestWorld text" + ");");

			runSQL("CREATE TABLE shop_signs (" + "signID integer PRIMARY KEY," + "supplyChestID integer,"
					+ "signOwnerCompanyID integer," + "productID integer," + "signPrice real" + ");");

			runSQL("CREATE TABLE signs (" + "signID integer PRIMARY KEY," + "signX integer," + "signY integer,"
					+ "signZ integer," + "signWorld text," + "signType text" + ");");

			runSQL("CREATE TABLE supply_chests (" + "chestID integer PRIMARY KEY," + "chestOwnerCompanyID integer"
					+ ");");

			setupEnumerator();
		}

		return is_new;
	}

	public static ResultSet runSQLquery(String query) throws SQLException {
		return sql_connection.createStatement().executeQuery(query);
	}

	public static void runSQL(String query) throws SQLException {
		sql_connection.createStatement().execute(query);
	}

	public static ResultSet runSQLsafeQuery(String query) {
		try {
			return sql_connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void runSQLsafe(String query) {
		try {
			sql_connection.createStatement().execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static final String T_GLOBAL_USER_PROFILES = "global_user_profiles";
	public static final String T_BANK_PROFILES = "bank_profiles";
	public static final String T_BANK_ACCOUNT_MATCHING = "bank_account_matching";
	public static final String T_BANK_ACCOUNTS = "bank_accounts";

	public static String PREFIX = "§f[§eWorld Economy§f]: §e";
}
