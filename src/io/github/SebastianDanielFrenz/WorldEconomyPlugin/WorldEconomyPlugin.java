package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialogRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.EventListener;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUIRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.CreditPaymentHandlerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.EmptyProductStackCleanerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.MachineInventoryAutoSaveThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.SalaryHandlerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain.CustomChunkGenerator;

import net.milkbowl.vault.economy.Economy;

public class WorldEconomyPlugin extends JavaPlugin {

	public static Economy economy;
	public static Connection sql_connection;

	public static WorldEconomyPlugin plugin;

	public static long user_count;
	public static long AI_count;

	private static Thread salaryHandlerThread;
	private static Thread creditPaymentHandlerThread;
	private static Thread emptyProductStackCleanerThread;
	private static Thread machineInventoryAutoSaveThread;

	@Override
	public void onEnable() {
		plugin = this;

		if (!setupEconomy()) {
			getLogger().info("ERROR: Could not hook into Vault!");
			getLogger().info("The money on hand will now be handled by World Economy!");
		}

		try {
			Files.createDirectories(Paths.get("plugins/WorldEconomy"));
			Files.createDirectories(Paths.get("plugins/WorldEconomy/saved_inventories"));
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

		getServer().getPluginManager().registerEvents(new EventListener(), this);

		getCommand("we").setExecutor(new WorldEconomyCommandExecutor());

		startThreads();

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

		// this sets the global counter for AI count and user count

		try {
			ResultSet r = runSQLquery("SELECT COUNT(*) AS total FROM user_profiles");
			r.next();
			user_count = r.getLong("total");

			r = runSQLquery("SELECT COUNT(*) AS total FROM ai_profiles");
			r.next();
			AI_count = r.getLong("total");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * ==================================================
		 * 
		 * This part is responsible for transferring metadata to machine blocks
		 * in the world.
		 * 
		 * ==================================================
		 */

		// try {
		// WEDB.loadMachines();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// getLogger().severe("Could not load the machines from the last server
		// run!");
		// }

		// Bukkit.getWorld("world").getPopulators().add(new
		// WorldEconomyBlockPopulator());

	}

	@Override
	public void onDisable() {
		stopThreads();

		while (creditPaymentHandlerThread.isAlive() || emptyProductStackCleanerThread.isAlive()
				|| machineInventoryAutoSaveThread.isAlive() || salaryHandlerThread.isAlive()) {
			try {
				Thread.sleep(1000);
				getLogger().info("Waiting for background threads to finish...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

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
						+ "(\"aiID\", 1), (\"mailboxID\", 1), (\"creditID\", 1), (\"stockMarketProductID\", 1)");
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
			runSQL("CREATE TABLE user_profiles (" + "playerUUID text PRIMARY KEY," + "employeeID integer NOT NULL,"
					+ "playerAsEmployerID integer NOT NULL," + "username text NOT NULL,"
					+ "playerBankingID integer NOT NULL," + "mailboxID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(employeeID) REFERENCES employees(employeeID),"
					+ "FOREIGN KEY(playerAsEmployerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(playerBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE sys_enumerator (" + "key text PRIMARY KEY," + "value integer DEFAULT 1" + ");");

			runSQL("CREATE TABLE employee_matching (" + "employee_matchingID integer PRIMARY KEY,"
					+ "employerID integer NOT NULL," + "employeeID integer NOT NULL," + "contractID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(employerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(employeeID) REFERENCES employees(employeeID)" + ");");

			runSQL("CREATE TABLE contracts (" + "contractID integer PRIMARY KEY," + "contractType text NOT NULL"
					+ ");");

			runSQL("CREATE TABLE companies (" + "companyID integer PRIMARY KEY," + "companyName text NOT NULL,"
					+ "companyType text NOT NULL," + "companyEmployerID integer NOT NULL,"
					+ "companyBankingID integer NOT NULL," + "mailboxID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(companyEmployerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(companyBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE banks (" + "bankID integer PRIMARY KEY," + "bankName text NOT NULL,"
					+ "bankCapital real DEFAULT 0," + "companyID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(companyID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE bank_accounts (" + "bankAccountID integer PRIMARY KEY,"
					+ "bankAccountBalance real NOT NULL," + "bankID integer NOT NULL,"
					+ "customerBankingID integer NOT NULL," + "customerType text NOT NULL,"
					+ "bankAccountName text NOT NULL,"
					// references
					+ "FOREIGN KEY(bankID) REFERENCES banks(bankID),"
					+ "FOREIGN KEY(customerBankingID) REFERENCES bank_customers(bankingID)" + ");");

			runSQL("CREATE TABLE products (" + "productID integer PRIMARY KEY,"
					+ "productManifacturerID integer NOT NULL," + "productPrice real NOT NULL,"
					+ "productName text NOT NULL," + "productItemID text NOT NULL,"
					+ "productItemAmount integer DEFAULT 1,"
					// references
					+ "FOREIGN KEY(productManifacturerID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE chests (" + "chestID integer PRIMARY KEY," + "chestType text NOT NULL,"
					+ "chestX integer NOT NULL," + "chestY integer NOT NULL," + "chestZ integer NOT NULL,"
					+ "chestWorld text NOT NULL" + ");");

			runSQL("CREATE TABLE shop_signs (" + "signID integer PRIMARY KEY," + "supplyChestID integer NOT NULL,"
					+ "signOwnerCompanyID integer NOT NULL," + "productID integer NOT NULL,"
					+ "signPrice real NOT NULL,"
					// references
					+ "FOREIGN KEY(supplyChestID) REFERENCES supply_chests(chestID),"
					+ "FOREIGN KEY(signOwnerCompanyID) REFERENCES companies(companyID)" + ");");

			// might have to make sub-tables with primary key as foreign key
			runSQL("CREATE TABLE signs (" + "signID integer PRIMARY KEY," + "signX integer NOT NULL,"
					+ "signY integer NOT NULL," + "signZ integer NOT NULL," + "signWorld text NOT NULL,"
					+ "signType text NOT NULL" + ");");

			runSQL("CREATE TABLE supply_chests (" + "chestID integer PRIMARY KEY,"
					+ "chestOwnerCompanyID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(chestOwnerCompanyID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE companies_corporations (companyID integer PRIMARY KEY,"
					+ "CEO_employeeID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(CEO_employeeID) REFERENCES employees(employeeID)" + ");");

			runSQL("CREATE TABLE companies_private (companyID integer PRIMARY KEY,"
					+ "ownerEmployeeID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(ownerEmployeeID) REFERENCES employees(employeeID)" + ");");

			runSQL("CREATE TABLE employees (employeeID integer PRIMARY KEY," + "employeeType text NOT NULL" + ");");

			runSQL("CREATE TABLE contracts_employment_default (contractID integer PRIMARY KEY,"
					+ "contractSalary real NOT NULL," + "contractLastSalary int NOT NULL" + ");");

			runSQL("CREATE TABLE employers (" + "employerID integer PRIMARY KEY," + "employerType text NOT NULL"
					+ ");");

			runSQL("CREATE TABLE ai_profiles (" + "aiID integer PRIMARY KEY," + "employeeID integer NOT NULL,"
					+ "aiAsEmployerID integer NOT NULL," + "username text NOT NULL," + "aiBankingID integer NOT NULL,"
					+ "mailboxID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(employeeID) REFERENCES employees(employeeID),"
					+ "FOREIGN KEY(aiAsEmployerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(aiBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE mailboxes (" + "mailboxID integer PRIMARY KEY," + "ownerType text NOT NULL" + ");");

			runSQL("CREATE TABLE mails (" + "mailID integer PRIMARY KEY," + "mailboxID integer NOT NULL,"
					+ "message text NOT NULL," + "senderMailboxID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID),"
					+ "FOREIGN KEY(senderMailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE bank_customers (" + "bankingID integer PRIMARY KEY," + "bankCustomerType text NOT NULL"
					+ ");");

			runSQL("CREATE TABLE bank_credits (" + "creditID integer PRIMARY KEY," + "creditBankID integer NOT NULL,"
					+ "creditRecieverBankingID integer NOT NULL," + "creditAmount real NOT NULL,"
					+ "creditInterest real NOT NULL," + "creditDuration integer NOT NULL,"
					+ "creditStart integer NOT NULL," + "creditRecieverBankAccountID integer NOT NULL,"
					// references
					+ "FOREIGN KEY(creditBankID) REFERENCES banks(bankID),"
					+ "FOREIGN KEY(creditRecieverBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(creditRecieverBankAccountID) REFERENCES bank_accounts(bankAccountID)" + ");");

			runSQL("CREATE TABLE stock_marekt_products (" + "stockMarketProductID integer PRIMARY KEY,"
					+ "stockMarketPrice real," + "stockMarketProductName text NOT NULL,"
					+ "stockMarketProductType text NOT NULL" + ");");

			runSQL("CREATE TABLE shares (" + "stockMarketProductID integer PRIMARY KEY,"
					+ "shareTotalAmount integer NOT NULL," + "shareTotalPartage real NOT NULL,"
					+ "shareCompanyID integer NOT NULL," + "shareDividend real NOT NULL," + "shareType text NOT NULL,"
					// references
					+ "FOREIGN KEY(shareCompanyID) REFERENCES corporations(companyID)" + ");");

			runSQL("CREATE TABLE stock_market_possesions (" + "stockMarketPossesionID integer PRIMARY KEY,"
					+ "stockMarketProductID integer NOT NULL," + "ownerBankAccountID integer NOT NULL,"
					+ "purchaseTime integer NOT NULL," + "purchasePrice real NOT NULL,"
					+ "purchaseAmount integer NOT NULL,"
					// references
					+ "FOREIGN KEY(ownerBankAccountID) REFERENCES bank_account(bankAccountID)" + ");");

			runSQL("CREATE TABLE resources (" + "resourceID integer PRIMARY KEY," + "resourceItemID text,"
					+ "resourceStoredAmount real," + "resourcePriceStep real," + "resourceMaxPrice real" + ");");

			runSQL("CREATE TABLE machines (" + "machineID integer PRIMARY KEY," + "machineGroup text,"
					+ "machineX integer," + "machineY integer," + "machineZ integer," + "machineWorld text" + ");");

			// enumerator

			setupEnumerator();

			// credit system

			WEDB.registerBank("central_bank");

			WEDB.registerResource(Material.IRON_INGOT, 640, 64, Math.pow(2, 10) * 1);
			WEDB.registerResource(Material.DIAMOND, 640, 64, Math.pow(2, 10) * 20);
			WEDB.registerResource(Material.GOLD_INGOT, 640, 64, Math.pow(2, 10) * 8);
			WEDB.registerResource(Material.LAPIS_LAZULI, 640, 64, Math.pow(2, 10) * 2);
			WEDB.registerResource(Material.SAND, 640, 64, Math.pow(2, 10) * 0.1);

		}

		return is_new;
	}

	public static void startThreads() {
		salaryHandlerThread = new Thread(new SalaryHandlerThread());
		salaryHandlerThread.start();
		creditPaymentHandlerThread = new Thread(new CreditPaymentHandlerThread());
		creditPaymentHandlerThread.start();
		emptyProductStackCleanerThread = new Thread(new EmptyProductStackCleanerThread());
		emptyProductStackCleanerThread.start();
		machineInventoryAutoSaveThread = new Thread(new MachineInventoryAutoSaveThread());
		machineInventoryAutoSaveThread.start();
	}

	public static void stopThreads() {
		salaryHandlerThread.interrupt();
		creditPaymentHandlerThread.interrupt();
		emptyProductStackCleanerThread.interrupt();
		machineInventoryAutoSaveThread.interrupt();
	}

	public static void resetDB() throws SQLException, IOException {
		plugin.getLogger().info("Shutting down background threads...");

		stopThreads();

		sql_connection.close();

		String filepath = plugin.getDataFolder().toString() + "\\data.db";

		Files.delete(Paths.get(filepath));

		setupSQL();

		WEDB.loadMachines();

		startThreads();
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

	public static void runSQLasync(String query) {
		plugin.getLogger().info("async SQL: " + query);

	}

	public static String PREFIX = "§f[§eWorld Economy§f]: §e";

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return new CustomChunkGenerator();
	}
}
