package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WorldEconomyExtensionManager;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialog.ChatDialogRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.CmdReg;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.WEPCmdRegExecutor;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.CustomBlockEventHandler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.CustomEntityInvincebilityHandler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.CustomItemInteractionEventHandler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.DeathEventHandler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.EventListener;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.ItemPickupIntegrationEventHandler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.event.TickListenerRunnable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.NMSUtil;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entities.EntityAI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.WEGUIRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.CreditPaymentHandlerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.EmptyProductStackCleanerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.ResearchHandlerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.SQLHandlerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.SalaryHandlerThread;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling.TaskScheduler;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.scheduling.TimeMeasurementType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks.LiveGUIUpdateSchedulerTask;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.tasks.PowerDistributionSchedulerTask;
import net.minecraft.server.v1_12_R1.EntityZombie;

/**
 * Bukkit docs:<br>
 * <a href="https://helpch.at/docs/1.12.2/overview-summary.html">here</a>
 * 
 * @author crash
 *
 */
public class WorldEconomyPlugin extends JavaPlugin {

	public static Connection sql_connection;

	public static WorldEconomyPlugin plugin;

	public static long status = 0;

	public static final long STATUS_CONSTRUCTING = 0;
	public static final long STATUS_STARTING = 1;
	public static final long STATUS_RUNNING = 2;
	public static final long STATUS_STOPPING = 3;

	public static long user_count;
	public static long AI_count;

	private static Thread salaryHandlerThread;
	private static Thread creditPaymentHandlerThread;
	private static Thread emptyProductStackCleanerThread;
	private static Thread researchHandlerThread;
	private static Thread sqlHandlerThread;

	public static Random randomizer = new Random();

	private static Version version;

	public static List<UserProfile> research_age_bypass = new ArrayList<UserProfile>(1);

	public WorldEconomyPlugin() {
	}

	@Override
	public void onEnable() {
		plugin = this;
		status = STATUS_CONSTRUCTING;
		version = Version.parseVersion(getDescription().getVersion());

		// first the languages
		Lang.lang_map.put("en_us", "en_us");
		Lang.lang_map.put("en_au", "en_us");
		Lang.lang_map.put("en_ca", "en_us");
		Lang.lang_map.put("en_gb", "en_us");
		Lang.lang_map.put("en_nz", "en_us");

		Lang.lang_map.put("lol_us", "en_us");

		Lang.lang_map.put("de_de", "de_de");
		Lang.lang_map.put("de_at", "de_de");

		Config.setup();

		try {
			Files.createDirectories(Paths.get("plugins/WorldEconomy"));
			Files.createDirectories(Paths.get("plugins/WorldEconomy/saved_inventories"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			if (setupSQL()) {
				getLogger().info("Created databases!");
				tick_counter = 0;
			} else {
				ResultSet result = runSQLquery("SELECT value FROM sys_enumerator WHERE key = \"ticks\"");
				if (result.next()) {
					tick_counter = result.getLong("value");
				} else {
					throw new RuntimeException();
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			getLogger().log(Level.SEVERE, "Failed to setup the databases. The error is as follows:");
			throw new RuntimeException("Could not create DBs!", e);
		}

		getServer().getPluginManager().registerEvents(new EventListener(), this);
		getServer().getPluginManager().registerEvents(new ItemPickupIntegrationEventHandler(), this);
		getServer().getPluginManager().registerEvents(new CustomBlockEventHandler(), this);
		getServer().getPluginManager().registerEvents(new CustomItemInteractionEventHandler(), this);
		getServer().getPluginManager().registerEvents(new DeathEventHandler(), this);

		// getCommand("we").setExecutor(new WorldEconomyCommandExecutor());
		// getCommand("we").setTabCompleter(new WorldEconomyCommandCompleter());
		// getCommand("kill").setExecutor(new WorldEconomyCommandExecutor());

		startThreads();

		TaskProcessor.init(Config.getBackGroundThreadCount(), Config.getIdleWaitMillis());
		TaskScheduler.init();

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
		 * This part is responsible for registering vanilla crafting recipes.
		 * 
		 * ==================================================
		 */

		// VanillaRecipe.init();
		// getServer().addRecipe(VanillaRecipe.STICK__PLANKS____CRAFTING_TABLE);

		CustomBlockTypeRegistry.init();
		CustomItemRegistry.init();
		ResearchItemRegistry.init();
		Age.init();

		// if plugins are supposed to work, there is a need for event calls here
		// (in order to register items, blocks, research items, etc.

		/**
		 * ==================================================
		 * 
		 * This part is responsible for registering custom entities and
		 * replacing vanilla ones.
		 * 
		 * ==================================================
		 */

		// NMSUtil.registerEntity("ai", 54, EntityZombie.class, EntityAI.class);
		NMSUtil.registerEntity("ai", 120, EntityZombie.class, EntityAI.class);

		getServer().getPluginManager().registerEvents(new CustomEntityInvincebilityHandler(), this);
		getServer().getPluginManager().registerEvents(new DeathEventHandler(), this);

		/*
		 * On tick stuff
		 */

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new TickListenerRunnable(), 1l, 1l);

		TaskScheduler.scheduleRepeatingTask(new LiveGUIUpdateSchedulerTask(), 1, TimeMeasurementType.TICKS);
		TaskScheduler.scheduleRepeatingTask(new PowerDistributionSchedulerTask(), 200, TimeMeasurementType.REAL_TIME);

		status = STATUS_STARTING;

		// register custom commands

		WorldEconomyPlugin.plugin.getLogger().info("Constructing command tree...");
		WEPCmdRegExecutor.root.put("we", CmdReg.WE);

		WEPCmdRegExecutor WECCRCE;
		for (String root : WEPCmdRegExecutor.root.keySet()) {
			WECCRCE = new WEPCmdRegExecutor(root);
			WorldEconomyPlugin.plugin.getCommand(root).setExecutor(WECCRCE);
			WorldEconomyPlugin.plugin.getCommand(root).setTabCompleter(WECCRCE);
		}
	}

	@Override
	public void onDisable() {
		status = STATUS_STOPPING;

		stopThreads();

		TaskProcessor.orderShutdown();
		TaskScheduler.orderShutdown();

		while (creditPaymentHandlerThread.isAlive() || emptyProductStackCleanerThread.isAlive()
				|| salaryHandlerThread.isAlive() || researchHandlerThread.isAlive() || TaskProcessor.isRunning()
				|| TaskScheduler.isRunning()) {
			try {
				Thread.sleep(1000);
				getLogger().info("Waiting for background threads to finish...");
				// if (TaskProcessor.isRunning()) {
				if (TaskProcessor.getQueueLength() > 0) {
					getLogger().info(TaskProcessor.getQueueLength() + " tasks pending...");
					for (String line : TaskProcessor.getStatus().getFormattedStatus()) {
						getLogger().info(line);
					}
				}
				// }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// here is the place for future task persistence features.

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
						+ "(\"aiID\", 1), (\"mailboxID\", 1), (\"creditID\", 1), (\"stockMarketProductID\", 1), (\"ticks\", 0)");
	}

	public static boolean setupSQL() throws SQLException, ClassNotFoundException {
		boolean is_new;

		switch (Config.getSQLConnectionType()) {
		case sqlite:
			is_new = !Files.exists(Paths.get(plugin.getDataFolder().toString() + "\\data.db"));

			sql_connection = DriverManager
					.getConnection("jdbc:sqlite:" + plugin.getDataFolder().toString() + "\\data.db");

			if (is_new) {
				runSQL("CREATE TABLE user_profiles (" + "playerID integer PRIMARY KEY," + "playerUUID text,"
						+ "employeeID integer NOT NULL," + "playerAsEmployerID integer NOT NULL,"
						+ "username text NOT NULL," + "playerBankingID integer NOT NULL,"
						+ "mailboxID integer NOT NULL,"

						+ "health real DEFAULT 100," + "maxHealth real DEFAULT 100," + "saturation real DEFAULT 100,"
						+ "happyness real DEFAULT 40," + "religious integer DEFAULT 1,"
						+ "religious_satisfaction real DEFAULT 100," + "endurance real DEFAULT 100,"
						+ "maxEndurance real DEFAULT 100," + "inHeaven integer DEFAULT 0,"
						+ "heavenEndTimeMillis integer DEFAULT 0,"

						+ "age text NOT NULL,"
						// references
						+ "$ref$FOREIGN KEY(employeeID) REFERENCES employees(employeeID),"
						+ "FOREIGN KEY(playerAsEmployerID) REFERENCES employers(employerID),"
						+ "FOREIGN KEY(playerBankingID) REFERENCES bank_customers(bankingID),"
						+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID)" + ");");

				runSQL("CREATE TABLE sys_enumerator (" + "\"key\" text PRIMARY KEY," + "\"value\" integer DEFAULT '1'"
						+ ");");
			}

			break;
		case mySQL:
			is_new = true;

			Class.forName("com.mysql.jdbc.Driver");
			sql_connection = DriverManager.getConnection(
					"jdbc:mysql://" + Config.getSQLHost() + ":" + Config.getSQLPort() + "/" + Config.getSQLDataBase(),
					Config.getSQLUser(), Config.getSQLPassword());

			runSQL("CREATE TABLE user_profiles (" + "playerID integer," + "playerUUID text,"
					+ "employeeID integer NOT NULL," + "playerAsEmployerID integer NOT NULL,"
					+ "username text NOT NULL," + "playerBankingID integer NOT NULL," + "mailboxID integer NOT NULL,"
					+ "health real DEFAULT 100," + "maxHealth real DEFAULT 100," + "saturation real DEFAULT 100,"
					+ "happyness real DEFAULT 40," + "religious integer DEFAULT 1,"
					+ "religious_satisfaction real DEFAULT 100," + "endurance real DEFAULT 100,"
					+ "maxEndurance real DEFAULT 100," + "inHeaven integer DEFAULT 0,"
					+ "heavenEndTimeMillis integer DEFAULT 0," + "age text NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(employeeID) REFERENCES employees(employeeID),"
					+ "FOREIGN KEY(playerAsEmployerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(playerBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE sys_enumerator (" + "\"key\" text," + "\"value\" integer DEFAULT '1',"
					+ "PRIMARY KEY(\"key\"(25))" + ");");
			break;
		default:
			throw new RuntimeException("Invalid database type! Please use mySQL or sqlite!");
		}

		// prepare DB

		if (is_new) {

			runSQL("CREATE TABLE employee_matching (" + "employee_matchingID integer PRIMARY KEY,"
					+ "employerID integer NOT NULL," + "employeeID integer NOT NULL," + "contractID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(employerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(employeeID) REFERENCES employees(employeeID)" + ");");

			runSQL("CREATE TABLE contracts (" + "contractID integer PRIMARY KEY," + "contractType text NOT NULL"
					+ ");");

			runSQL("CREATE TABLE companies (" + "companyID integer PRIMARY KEY," + "companyName text NOT NULL,"
					+ "companyType text NOT NULL," + "companyEmployerID integer NOT NULL,"
					+ "companyBankingID integer NOT NULL," + "mailboxID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(companyEmployerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(companyBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE banks (" + "bankID integer PRIMARY KEY," + "bankName text NOT NULL,"
					+ "bankCapital real DEFAULT 0," + "companyID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(companyID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE bank_accounts (" + "bankAccountID integer PRIMARY KEY,"
					+ "bankAccountBalance real NOT NULL," + "bankID integer NOT NULL,"
					+ "customerBankingID integer NOT NULL," + "customerType text NOT NULL,"
					+ "bankAccountName text NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(bankID) REFERENCES banks(bankID),"
					+ "FOREIGN KEY(customerBankingID) REFERENCES bank_customers(bankingID)" + ");");

			runSQL("CREATE TABLE products (" + "productID integer PRIMARY KEY,"
					+ "productManifacturerID integer NOT NULL," + "productPrice real NOT NULL,"
					+ "productName text NOT NULL," + "productItemID text NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(productManifacturerID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE chests (" + "chestID integer PRIMARY KEY," + "chestType text NOT NULL,"
					+ "chestX integer NOT NULL," + "chestY integer NOT NULL," + "chestZ integer NOT NULL,"
					+ "chestWorld text NOT NULL" + ");");

			runSQL("CREATE TABLE shop_signs (" + "signID integer PRIMARY KEY," + "supplyChestID integer NOT NULL,"
					+ "signOwnerCompanyID integer NOT NULL," + "productID integer NOT NULL,"
					+ "signPrice real NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(supplyChestID) REFERENCES supply_chests(chestID),"
					+ "FOREIGN KEY(signOwnerCompanyID) REFERENCES companies(companyID)" + ");");

			// might have to make sub-tables with primary key as foreign key
			runSQL("CREATE TABLE signs (" + "signID integer PRIMARY KEY," + "signX integer NOT NULL,"
					+ "signY integer NOT NULL," + "signZ integer NOT NULL," + "signWorld text NOT NULL,"
					+ "signType text NOT NULL" + ");");

			runSQL("CREATE TABLE supply_chests (" + "chestID integer PRIMARY KEY,"
					+ "chestOwnerCompanyID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(chestOwnerCompanyID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE companies_corporations (companyID integer PRIMARY KEY,"
					+ "CEO_employeeID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(CEO_employeeID) REFERENCES employees(employeeID)" + ");");

			runSQL("CREATE TABLE companies_private (companyID integer PRIMARY KEY,"
					+ "ownerEmployeeID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(ownerEmployeeID) REFERENCES employees(employeeID)" + ");");

			runSQL("CREATE TABLE employees (employeeID integer PRIMARY KEY," + "employeeType text NOT NULL,"
					+ "employeeLastResearched integer DEFAULT `0`" + ");");

			runSQL("CREATE TABLE contracts_employment_default (contractID integer PRIMARY KEY,"
					+ "contractSalary real NOT NULL," + "contractLastSalary int NOT NULL" + ");");

			runSQL("CREATE TABLE employers (" + "employerID integer PRIMARY KEY," + "employerType text NOT NULL"
					+ ");");

			runSQL("CREATE TABLE ai_profiles (" + "aiID integer PRIMARY KEY," + "employeeID integer NOT NULL,"
					+ "aiAsEmployerID integer NOT NULL," + "username text NOT NULL," + "aiBankingID integer NOT NULL,"
					+ "mailboxID integer NOT NULL," + "health real DEFAULT 100," + "maxHealth real DEFAULT 100,"
					+ "saturation real DEFAULT 100," + "happyness real DEFAULT 40," + "religious integer DEFAULT 1,"
					+ "religious_satisfaction real DEFAULT 100," + "endurance real DEFAULT 100,"
					+ "maxEndurance real DEFAULT 100," + "inHeaven integer DEFAULT 0,"
					+ "heavenEndTimeMillis integer DEFAULT 0," + "age text NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(employeeID) REFERENCES employees(employeeID),"
					+ "FOREIGN KEY(aiAsEmployerID) REFERENCES employers(employerID),"
					+ "FOREIGN KEY(aiBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE mailboxes (" + "mailboxID integer PRIMARY KEY," + "ownerType text NOT NULL" + ");");

			runSQL("CREATE TABLE mails (" + "mailID integer PRIMARY KEY," + "mailboxID integer NOT NULL,"
					+ "message text NOT NULL," + "senderMailboxID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(mailboxID) REFERENCES mailboxes(mailboxID),"
					+ "FOREIGN KEY(senderMailboxID) REFERENCES mailboxes(mailboxID)" + ");");

			runSQL("CREATE TABLE bank_customers (" + "bankingID integer PRIMARY KEY," + "bankCustomerType text NOT NULL"
					+ ");");

			runSQL("CREATE TABLE bank_credits (" + "creditID integer PRIMARY KEY," + "creditBankID integer NOT NULL,"
					+ "creditRecieverBankingID integer NOT NULL," + "creditAmount real NOT NULL,"
					+ "creditInterest real NOT NULL," + "creditDuration integer NOT NULL,"
					+ "creditStart integer NOT NULL," + "creditRecieverBankAccountID integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(creditBankID) REFERENCES banks(bankID),"
					+ "FOREIGN KEY(creditRecieverBankingID) REFERENCES bank_customers(bankingID),"
					+ "FOREIGN KEY(creditRecieverBankAccountID) REFERENCES bank_accounts(bankAccountID)" + ");");

			runSQL("CREATE TABLE stock_market_products (" + "stockMarketProductID integer PRIMARY KEY,"
					+ "stockMarketPrice real," + "stockMarketProductName text NOT NULL,"
					+ "stockMarketProductType text NOT NULL" + ");");

			runSQL("CREATE TABLE shares (" + "stockMarketProductID integer PRIMARY KEY,"
					+ "shareTotalAmount integer NOT NULL," + "shareTotalPartage real NOT NULL,"
					+ "shareCompanyID integer NOT NULL," + "shareDividend real NOT NULL," + "shareType text NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(shareCompanyID) REFERENCES corporations(companyID)" + ");");

			runSQL("CREATE TABLE stock_market_possesions (" + "stockMarketPossesionID integer PRIMARY KEY,"
					+ "stockMarketProductID integer NOT NULL," + "ownerBankAccountID integer NOT NULL,"
					+ "purchaseTime integer NOT NULL," + "purchasePrice real NOT NULL,"
					+ "purchaseAmount integer NOT NULL,"
					// references
					+ "$ref$FOREIGN KEY(ownerBankAccountID) REFERENCES bank_account(bankAccountID)" + ");");

			runSQL("CREATE TABLE resources (" + "resourceID integer PRIMARY KEY," + "resourceCustomItemID text,"
					+ "resourceStoredAmount real," + "resourcePriceStep real," + "resourceMaxPrice real" + ");");

			runSQL("CREATE TABLE machines (" + "machineID integer PRIMARY KEY," + "machineGroup text,"
					+ "machineX integer," + "machineY integer," + "machineZ integer," + "machineWorld text" + ");");

			runSQL("CREATE TABLE custom_blocks (" + "customBlockID integer PRIMARY KEY," + "blockX integer,"
					+ "blockY integer," + "blockZ integer," + "blockWorld text," + "blockType text," + "blockData text"
					+ ");");

			runSQL("CREATE TABLE employee_professions (employeeProfessionMatchingID integer PRIMARY KEY,"
					+ "employeeID integer," + "professionName text" + ");");

			runSQL("CREATE TABLE research (researchID integer PRIMARY KEY," + "researchItem string,"
					+ "researchEntityID integer," + "researchEntityType string" + ");");

			runSQL("CREATE TABLE statistics (statisticID text," + "entityID integer," + "entityType text,"
					+ "value real," + "PRIMARY KEY(statisticID, entityID, entityType)" + ");");

			runSQL("CREATE TABLE company_revenues (companyID integer," + "year integer," + "spendings real,"
					+ "earnings real," + "PRIMARY KEY(companyID, year),"
					// references
					+ "$ref$FOREIGN KEY(companyID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE company_positions (companyPositionID integer PRIMARY KEY," + "companyID integer,"
					+ "positionName text,"
					// references
					+ "$ref$FOREIGN KEY(companyID) REFERENCES companies(companyID)" + ");");

			runSQL("CREATE TABLE company_position_professions (companyPositionID integer," + "professionName text,"
					+ "PRIMARY KEY(companyPositionID, professionName),"
					// references
					+ "$ref$FOREIGN KEY(companyPositionID) REFERENCES company_positions(companyPositionID)" + ");");

			runSQL("CREATE TABLE job_offers (jobOfferID integer PRIMARY KEY," + "companyPositionID integer,"
					+ "salary real," + "positions integer,"
					// references
					+ "$ref$FOREIGN KEY(companyPositionID) REFERENCES company_positions(companyPositionID)" + ");");

			// enumerator

			setupEnumerator();

			// credit system

			WEDB.registerBank("central_bank");

			WEDB.registerResource(CustomItemRegistry.IRON_INGOT, 640, 64, Math.pow(2, 10) * 1);
			WEDB.registerResource(CustomItemRegistry.DIAMOND, 640, 64, Math.pow(2, 10) * 20);
			WEDB.registerResource(CustomItemRegistry.GOLD_INGOT, 640, 64, Math.pow(2, 10) * 8);
			WEDB.registerResource(CustomItemRegistry.LAPIS_LAZULI, 640, 64, Math.pow(2, 10) * 2);
			WEDB.registerResource(CustomItemRegistry.SAND, 640, 64, Math.pow(2, 10) * 0.1);

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
		researchHandlerThread = new Thread(new ResearchHandlerThread());
		researchHandlerThread.start();
		sqlHandlerThread = new Thread(new SQLHandlerThread());
		sqlHandlerThread.start();
	}

	public static void stopThreads() {
		salaryHandlerThread.interrupt();
		creditPaymentHandlerThread.interrupt();
		emptyProductStackCleanerThread.interrupt();
		researchHandlerThread.interrupt();
		sqlHandlerThread.interrupt();
	}

	public static void resetDB() throws SQLException, IOException, ClassNotFoundException {
		plugin.getLogger().info("Shutting down background threads...");

		status = STATUS_STOPPING;
		stopThreads();

		sql_connection.close();

		String filepath = plugin.getDataFolder().toString() + "\\data.db";

		Files.delete(Paths.get(filepath));

		setupSQL();

		status = STATUS_STARTING;
		startThreads();
		status = STATUS_RUNNING;
	}

	public static ResultSet runSQLquery(String query) throws SQLException {
		// plugin.getLogger().info("SQL: " + query);
		return sql_connection.createStatement().executeQuery(query);
	}

	public synchronized static void runSQL(String query) throws SQLException {
		// plugin.getLogger().info("SQL: " + query);
		if (query.startsWith("CREATE TABLE ") && Config.getSQLConnectionType() == SQLConnectionType.mySQL) {
			if (query.contains("$ref$")) {
				String[] split = query.split(Pattern.quote("$ref$"));
				query = split[0];
				query = query.substring(0, query.length() - 1) + ");";
			}

			query = query.replace(" integer", " int");

			plugin.getLogger().info("mySQL adapted table creation: " + query);
		} else {
			query = query.replace("$ref$", "");
		}

		if (Config.getSQLConnectionType() == SQLConnectionType.mySQL) {
			query = query.replace("\"", "`");
			plugin.getLogger().info("mySQL adapted strings: " + query);
		}
		sql_connection.createStatement().execute(query);
	}

	public synchronized static ResultSet runSQLsafeQuery(String query) {
		// plugin.getLogger().info("SQL: " + query);
		try {
			return sql_connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public synchronized static void runSQLsafe(String query) {
		// plugin.getLogger().info("SQL: " + query);
		try {
			sql_connection.createStatement().execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method should only be used for cached values (data that is only
	 * saved in the database for persistency).
	 * 
	 * @param query
	 * @throws SQLException
	 */
	public static void runSQLasync(String query) throws SQLException {
		// plugin.getLogger().info("async SQL: " + query);
		// later: add async functionality
		runSQL(query);
	}

	public static void queueSQL(String query) {
		// TaskProcessor.registerTask(new SQLTask(query));

	}

	public static String PREFIX = "�f[�eWorld Economy�f]: �e";

	// @Override
	// public ChunkGenerator getDefaultWorldGenerator(String worldName, String
	// id) {
	// return new CustomChunkGenerator();
	// }

	public static Version getVersion() {
		return version;
	}
}
