package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialogRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUIRegistry;
import net.milkbowl.vault.economy.Economy;

public class WorldEconomyPlugin extends JavaPlugin {

	public static Economy economy;
	public static Connection sql_connection;

	public static WorldEconomyPlugin plugin;

	@Override
	public void onEnable() {
		plugin = this;

		if (!setupEconomy()) {
			getLogger().info("ERROR: Could not hook into Vault!");
			getServer().getPluginManager().disablePlugin(this);
		}

		try {
			Files.createDirectories(Paths.get("plugins/WorldEconomy"));

		} catch (IOException e1) {
			e1.printStackTrace();
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

		new Thread(new SalaryHandlerThread()).start();

		/**
		 * ==================================================
		 * 
		 * This part is responsible for registering GUI related things.
		 * 
		 * ==================================================
		 */
		getServer().getPluginManager().registerEvents(new WEGUIRegistry(), this);

		/**
		 * ==================================================
		 * 
		 * This part is responsible for registering chat dialog (ChatDialog)
		 * related things.
		 * 
		 * ==================================================
		 */
		getServer().getPluginManager().registerEvents(new ChatDialogRegistry(), this);
	}

	@Override
	public void onDisable() {
		try {
			sql_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static long tick_counter;

	private static void setupEnumerator() {
		runSQLsafe(
				"INSERT INTO sys_enumerator (key, value) VALUES (\"bankingID\", 1), (\"employerID\", 1), (\"employeeID\", 1), (\"chestID\", 1),"
						+ "(\"signID\", 1), (\"bankID\", 1), (\"bankAccountID\", 1), (\"companyID\", 1), (\"productID\", 1), (\"contractID\", 1),"
						+ "(\"aiID\", 1), (\"mailboxID\", 1)");
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return economy != null;
	}

	public static boolean setupSQL() throws SQLException {
		boolean is_new = !Files.exists(Paths.get(plugin.getDataFolder().toString() + "\\data.db"));

		sql_connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder().toString() + "\\data.db");

		// prepare DB

		if (is_new) {
			runSQL("CREATE TABLE user_profiles (" + "playerUUID text PRIMARY KEY," + "employeeID integer,"
					+ "playerAsEmployerID integer," + "username text," + "playerBankingID integer,"
					+ "mailboxID integer" + ");");

			runSQL("CREATE TABLE sys_enumerator (" + "key text PRIMARY KEY," + "value integer" + ");");

			runSQL("CREATE TABLE employee_matching (" + "employee_matchingID integer PRIMARY KEY,"
					+ "employerID integer," + "employeeID integer," + "contractID integer" + ");");

			runSQL("CREATE TABLE credits (" + "creditID integer PRIMARY KEY," + "creditAmount real,"
					+ "creditInterest real," + "creditorBankingID integer" + ");");

			runSQL("CREATE TABLE contracts (" + "contractID integer PRIMARY KEY," + "contractType text" + ");");

			runSQL("CREATE TABLE companies (" + "companyID integer PRIMARY KEY," + "companyName text,"
					+ "companyType text," + "companyEmployerID integer," + "companyBankingID integer,"
					+ "mailboxID integer" + ");");

			runSQL("CREATE TABLE banks (" + "bankID integer PRIMARY KEY," + "bankName text" + ");");

			runSQL("CREATE TABLE bank_accounts (" + "bankAccountID integer PRIMARY KEY," + "bankAccountBalance real,"
					+ "bankID integer," + "customerBankingID integer," + "customerType text," + "bankAccountName text"
					+ ");");

			runSQL("CREATE TABLE products (" + "productID integer PRIMARY KEY," + "productManifacturerID integer,"
					+ "productPrice real," + "productName text," + "productItemID text," + "productItemAmount integer"
					+ ");");

			runSQL("CREATE TABLE chests (" + "chestID integer PRIMARY KEY," + "chestType text," + "chestX integer,"
					+ "chestY integer," + "chestZ integer," + "chestWorld text" + ");");

			runSQL("CREATE TABLE shop_signs (" + "signID integer PRIMARY KEY," + "supplyChestID integer,"
					+ "signOwnerCompanyID integer," + "productID integer," + "signPrice real" + ");");

			runSQL("CREATE TABLE signs (" + "signID integer PRIMARY KEY," + "signX integer," + "signY integer,"
					+ "signZ integer," + "signWorld text," + "signType text" + ");");

			runSQL("CREATE TABLE supply_chests (" + "chestID integer PRIMARY KEY," + "chestOwnerCompanyID integer"
					+ ");");

			runSQL("CREATE TABLE companies_corporations (companyID integer PRIMARY KEY," + "CEO_employeeID integer"
					+ ");");

			runSQL("CREATE TABLE companies_private (companyID integer PRIMARY KEY," + "ownerEmployeeID integer" + ");");

			runSQL("CREATE TABLE employees (employeeID integer PRIMARY KEY," + "employeeType text" + ");");

			runSQL("CREATE TABLE contracts_employment_default (contractID integer PRIMARY KEY," + "contractSalary real,"
					+ "contractLastSalary int" + ");");

			runSQL("CREATE TABLE employers (" + "employerID integer PRIMARY KEY," + "employerType text" + ");");

			runSQL("CREATE TABLE ai_profiles (" + "aiID integer PRIMARY KEY," + "employeeID integer,"
					+ "aiAsEmployerID integer," + "username text," + "aiBankingID integer," + "mailboxID integer"
					+ ");");

			runSQL("CREATE TABLE mailboxes (" + "mailboxID integer PRIMARY KEY," + "ownerType text" + ");");

			runSQL("CREATE TABLE mails (" + "mailID integer PRIMARY KEY," + "mailboxID integer," + "message text,"
					+ "senderMailboxID integer" + ");");

			runSQL("CREATE TABLE bank_customers (" + "bankingID integer PRIMARY KEY," + "bankCustomerType text" + ");");

			setupEnumerator();
		}

		return is_new;
	}

	public static void resetDB() throws SQLException, IOException {
		sql_connection.close();

		String filepath = plugin.getDataFolder().toString() + "\\data.db";

		Files.delete(Paths.get(filepath));

		setupSQL();
	}

	public static ResultSet runSQLquery(String query) throws SQLException {
		plugin.getLogger().info("SQL: " + query);
		return sql_connection.createStatement().executeQuery(query);
	}

	public static void runSQL(String query) throws SQLException {
		plugin.getLogger().info("SQL: " + query);
		sql_connection.createStatement().execute(query);
	}

	public static ResultSet runSQLsafeQuery(String query) {
		plugin.getLogger().info("SQL: " + query);
		try {
			return sql_connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void runSQLsafe(String query) {
		plugin.getLogger().info("SQL: " + query);
		try {
			sql_connection.createStatement().execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String PREFIX = "�f[�eWorld Economy�f]: �e";
}
