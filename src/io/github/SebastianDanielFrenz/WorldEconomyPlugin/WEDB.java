package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.Credit;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Employee;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeeAI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeePlayer;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotImplementedException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.NotSupportedException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.ComparableLocation;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlock;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticCategory;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticCategoryRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.StatisticalObject;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.Mail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailSubsystem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.Product;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.ShopSignData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.SignData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.SupplyChestData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions.EmployeeProfession;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.Share;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.stockmarket.StockMarketProductStack;

public class WEDB {

	/**
	 * ==================================================
	 * 
	 * This section is dedicated to the user.
	 * 
	 * ==================================================
	 */

	/**
	 * Devs: Please update getMailboxOwner(long mailboxID) along with this
	 * method.
	 * 
	 * @param player
	 * @return
	 * @throws SQLException
	 */
	public static UserProfile getUserProfile(OfflinePlayer player) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM user_profiles WHERE playerUUID = \"" + player.getUniqueId() + "\"");

		if (r.next()) {
			return new UserProfile(r.getLong("playerID"), UUID.fromString(r.getString("playerUUID")),
					r.getInt("employeeID"), r.getInt("playerAsEmployerID"), r.getString("username"),
					r.getInt("playerBankingID"), r.getLong("mailboxID"), WEDB.getProfessions(r.getLong("employeeID")),
					r.getDouble("health"), r.getDouble("maxHealth"), r.getDouble("saturation"),
					r.getDouble("happyness"), r.getBoolean("religious"), r.getDouble("religious_satisfaction"),
					r.getDouble("endurance"), r.getDouble("maxEndurance"), r.getBoolean("inHeaven"),
					r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age")));
		} else {
			return null;
		}
	}

	public static UserProfile getUserProfile(UUID uuid) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles WHERE playerUUID = \"" + uuid + "\"");

		if (r.next()) {
			return new UserProfile(r.getLong("playerID"), UUID.fromString(r.getString("playerUUID")),
					r.getInt("employeeID"), r.getInt("playerAsEmployerID"), r.getString("username"),
					r.getInt("playerBankingID"), r.getLong("mailboxID"), WEDB.getProfessions(r.getLong("employeeID")),
					r.getDouble("health"), r.getDouble("maxHealth"), r.getDouble("saturation"),
					r.getDouble("happyness"), r.getBoolean("religious"), r.getDouble("religious_satisfaction"),
					r.getDouble("endurance"), r.getDouble("maxEndurance"), r.getBoolean("inHeaven"),
					r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age")));
		} else {
			return null;
		}
	}

	public static UserProfile getUserProfile(long playerID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles WHERE playerID = " + playerID);

		if (r.next()) {
			return new UserProfile(r.getLong("playerID"), UUID.fromString(r.getString("playerUUID")),
					r.getInt("employeeID"), r.getInt("playerAsEmployerID"), r.getString("username"),
					r.getInt("playerBankingID"), r.getLong("mailboxID"), WEDB.getProfessions(r.getLong("employeeID")),
					r.getDouble("health"), r.getDouble("maxHealth"), r.getDouble("saturation"),
					r.getDouble("happyness"), r.getBoolean("religious"), r.getDouble("religious_satisfaction"),
					r.getDouble("endurance"), r.getDouble("maxEndurance"), r.getBoolean("inHeaven"),
					r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age")));
		} else {
			return null;
		}
	}

	public static List<UserProfile> getAllUserProfiles() throws SQLException {
		List<UserProfile> out = new ArrayList<UserProfile>();
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles");
		while (r.next()) {
			out.add(new UserProfile(r.getLong("playerID"), UUID.fromString(r.getString("playerUUID")),
					r.getInt("employeeID"), r.getInt("playerAsEmployerID"), r.getString("username"),
					r.getInt("playerBankingID"), r.getLong("mailboxID"), WEDB.getProfessions(r.getLong("employeeID")),
					r.getDouble("health"), r.getDouble("maxHealth"), r.getDouble("saturation"),
					r.getDouble("happyness"), r.getBoolean("religious"), r.getDouble("religious_satisfaction"),
					r.getDouble("endurance"), r.getDouble("maxEndurance"), r.getBoolean("inHeaven"),
					r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age"))));
		}
		return out;
	}

	public static long getNextEnumerator(String type) {
		try {
			ResultSet res = WorldEconomyPlugin
					.runSQLquery("SELECT value FROM sys_enumerator WHERE key = \"" + type + "\"");
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
			WorldEconomyPlugin.runSQL("UPDATE sys_enumerator SET value = value + 1 WHERE key = \"" + type + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void registerUserProfile(OfflinePlayer player) throws SQLException {
		long ID = registerEmployee(player);

		WorldEconomyPlugin
				.runSQL("INSERT INTO user_profiles (playerUUID, employeeID, playerAsEmployerID, playerBankingID, username, mailboxID, age)"
						+ " VALUES (\"" + player.getUniqueId().toString() + "\", " + ID + ", "
						+ getNextEnumerator("employerID") + ", " + registerBankingEntity("player") + ", \""
						+ player.getName() + "\", " + registerBaseMailbox("player") + ", \""
						+ Age.EARLY_STONE_AGE.name() + "\"" + ")");

		moveEnumerator("employerID");
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to the banking system.
	 * 
	 * ==================================================
	 */

	public static void registerBankAccount(BankAccount account) throws SQLException {
		WorldEconomyPlugin
				.runSQL("INSERT INTO bank_accounts (bankAccountBalance, bankID, bankAccountName, customerBankingID, customerType) VALUES ("
						+ account.getBalance() + ", " + account.getBankID() + ", \"" + account.getName() + "\", "
						+ account.getAccountHolderID() + ", \"" + account.getType() + "\")");
	}

	@SQLInjection
	public static BankAccount getBankAccount(long bankingID, String name) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM bank_accounts WHERE customerBankingID = "
				+ bankingID + " AND bankAccountName = \"" + name + "\"");
		if (r.next()) {
			return new BankAccount(r.getLong("bankAccountID"), r.getLong("bankID"), r.getDouble("bankAccountBalance"),
					name, r.getLong("customerBankingID"), r.getString("customerType"));
		} else {
			return null;
		}
	}

	public static BankAccount getBankAccount(long bankAccountID) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM bank_accounts WHERE bankAccountID = " + bankAccountID);
		if (!r.next()) {
			return null;
		}
		return new BankAccount(bankAccountID, r.getLong("bankID"), r.getDouble("bankAccountBalance"),
				r.getString("bankAccountName"), r.getLong("customerBankingID"), r.getString("customerType"));
	}

	public static List<BankAccount> getBankAccounts(long bankingID) throws SQLException {
		List<BankAccount> out = new ArrayList<BankAccount>();
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM bank_accounts WHERE customerBankingID = " + bankingID);

		String type = getBankingEntityType(bankingID);

		while (r.next()) {
			out.add(new BankAccount(r.getLong("bankAccountID"), r.getLong("bankID"), r.getDouble("bankAccountBalance"),
					r.getString("bankAccountName"), bankingID, type));
		}
		return out;
	}

	public static List<BankAccount> getAllBankAccounts(Player player) throws SQLException {
		List<BankAccount> out = new ArrayList<BankAccount>();
		ResultSet r = WorldEconomyPlugin.runSQLquery(
				"SELECT * FROM bank_accounts WHERE customerBankingID = " + getUserProfile(player).bankingID);
		while (r.next()) {
			out.add(new BankAccount(r.getLong("bankAccountID"), r.getLong("bankID"), r.getDouble("bankAccountBalance"),
					r.getString("bankAccountName"), r.getLong("customerBankingID"), r.getString("customerType")));
		}
		return out;
	}

	public static void setBankAccountBalance(BankAccount account, double balance) throws SQLException {
		setBankAccountBalance(account.getID(), balance);
	}

	public static void setBankAccountBalance(long accountID, double balance) throws SQLException {
		WorldEconomyPlugin.runSQL(
				"UPDATE bank_accounts SET bankAccountBalance = " + balance + " WHERE bankAccountID = " + accountID);
	}

	public static void bankAccountTransaction(long accountID1, long accountID2, double amount) throws SQLException {
		WorldEconomyPlugin.runSQL("UPDATE bank_accounts SET bankAccountBalance = bankAccountBalance - " + amount
				+ " WHERE bankAccountID = " + accountID1);
		WorldEconomyPlugin.runSQL("UPDATE bank_accounts SET bankAccountBalance = bankAccountBalance + " + amount
				+ " WHERE bankAccountID = " + accountID2);
	}

	public static void bankAccountTransaction(BankAccount account1, BankAccount account2, double amount)
			throws SQLException {
		bankAccountTransaction(account1.getID(), account2.getID(), amount);
	}

	public static void bankTransaction(Bank bank1, Bank bank2, double amount) throws SQLException {
		WorldEconomyPlugin
				.runSQL("UPDATE banks SET bankCapital = bankCapital-" + amount + " WHERE bankID = " + bank1.ID);
		WorldEconomyPlugin
				.runSQL("UPDATE banks SET bankCapital = bankCapital+" + amount + " WHERE bankID = " + bank2.ID);
	}

	@SQLInjection
	public static void setBankAccountName(BankAccount account, String name) throws SQLException {
		WorldEconomyPlugin.runSQL(
				"UPDATE bank_accounts SET bankAccountName = \"" + name + "\" WHERE bankAccountID = " + account.getID());
	}

	@SQLInjection
	public static long registerBankingEntity(String type) throws SQLException {
		long bankingID = getNextEnumerator("bankingID");

		WorldEconomyPlugin.runSQL("INSERT INTO bank_customers (bankingID, bankCustomerType) VALUES (" + bankingID
				+ ", \"" + type + "\")");

		moveEnumerator("bankingID");

		return bankingID;
	}

	public static String getBankingEntityType(long bankingID) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT bankCustomerType FROM bank_customers WHERE bankingID = " + bankingID);

		if (!r.next()) {
			return null;
		}
		return r.getString("bankCustomerType");
	}

	@SQLInjection
	public static void registerBank(String name) throws SQLException {
		WorldEconomyPlugin.runSQL("INSERT INTO banks (bankID, bankName, companyID) VALUES ("
				+ getNextEnumerator("bankID") + ", \"" + name + "\"," + registerBaseCompany(name, "bank") + ")");
		moveEnumerator("bankID");
	}

	@SQLInjection
	public static Bank getBank(String name) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks WHERE bankName = \"" + name + "\"");
		if (!r.next()) {
			return null;
		} else {
			return new Bank(r.getLong("bankID"), r.getString("bankName"), r.getDouble("bankCapital"),
					r.getLong("companyID"));
		}
	}

	public static Bank getBank(long bankID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks WHERE bankID = " + bankID);
		if (!r.next()) {
			return null;
		}
		return new Bank(bankID, r.getString("bankName"), r.getDouble("bankCapital"), r.getLong("companyID"));
	}

	public static List<Bank> getAllBanks() throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks");
		List<Bank> out = new ArrayList<Bank>();

		while (r.next()) {
			out.add(new Bank(r.getLong("bankID"), r.getString("bankName"), r.getDouble("bankCapital"),
					r.getLong("companyID")));
		}
		return out;
	}

	public static long getBankMailboxIDFromCompanyID(long bankCompanyID) throws SQLException {
		return WEDB.getMailboxIDOfCompany(bankCompanyID);
	}

	public static long getBankMailboxIDFromBankID(long bankID) throws SQLException {
		return getBankMailboxIDFromCompanyID(WEDB.getBank(bankID).companyID);
	}

	public static Bank getCentralBank() throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks WHERE bankID = 1");
		if (!r.next()) {
			return null;
		}
		return new Bank(1, r.getString("bankName"), r.getDouble("bankCapital"), r.getLong("companyID"));
	}

	// Credit system

	private static long registerCredit(Credit credit) throws SQLException {
		long creditID = getNextEnumerator("creditID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO bank_credits (creditID, creditBankID, creditRecieverBankingID, creditAmount, creditInterest, creditDuration, creditStart, creditRecieverBankAccountID)"
						+ "VALUES (" + creditID + ", " + credit.bankID + ", " + credit.recieverBankingID + ", "
						+ credit.amount + ", " + credit.interest + ", " + credit.duration + ", " + credit.start + ", "
						+ credit.recieverBankAccountID + ")");

		moveEnumerator("creditID");

		return creditID;
	}

	public static long takeCredit(Credit credit, BankAccount destination) throws SQLException {
		long ID = registerCredit(credit);
		Bank bank = getBank(credit.bankID);
		bankTransaction(bank, getCentralBank(), credit.amount * 0.04);
		setBankAccountBalance(destination, credit.amount + destination.getBalance());
		return ID;
	}

	public static Credit getCredit(long creditID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM bank_credits WHERE creditID = " + creditID);
		if (!r.next()) {
			return null;
		}
		return new Credit(creditID, r.getLong("creditRecieverBankingID"), r.getLong("creditBankID"),
				r.getDouble("creditAmount"), r.getDouble("creditInterest"), r.getLong("creditDuration"),
				r.getLong("creditStart"), r.getLong("creditRecieverBankAccountID"));
	}

	public static List<Credit> getBankAccountCredits(long bankAccountID) throws SQLException {
		List<Credit> out = new ArrayList<Credit>();
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM bank_credits WHERE creditRecieverBankAccountID = " + bankAccountID);
		while (r.next()) {
			out.add(new Credit(r.getLong("creditID"), r.getLong("creditRecieverBankingID"), r.getLong("creditBankID"),
					r.getDouble("creditAmount"), r.getDouble("creditInterest"), r.getLong("creditDuration"),
					r.getLong("creditStart"), r.getLong("creditRecieverBankAccountID")));
		}
		return out;
	}

	public static List<Credit> getAllCredits() throws SQLException {
		List<Credit> out = new ArrayList<Credit>();
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM bank_credits");
		while (r.next()) {
			out.add(new Credit(r.getLong("creditID"), r.getLong("creditRecieverBankingID"), r.getLong("creditBankID"),
					r.getDouble("creditAmount"), r.getDouble("creditInterest"), r.getLong("creditDuration"),
					r.getLong("creditStart"), r.getLong("creditRecieverBankAccountID")));
		}

		return out;
	}

	public static void payOffCredit(Credit credit) throws SQLException {
		WorldEconomyPlugin.runSQL("UPDATE banks SET bankCapital=bankCapital-" + (credit.amount * 0.04)
				+ " WHERE bankName = \"central_bank\"");
		WorldEconomyPlugin.runSQL("UPDATE banks SET bankCapital=bankCapital+" + (credit.amount * 0.04)
				+ " WHERE bankID = " + credit.recieverBankingID);
		WEDB.setBankAccountBalance(credit.recieverBankAccountID,
				WEDB.getBankAccount(credit.recieverBankAccountID).getBalance() - credit.amount);
		WorldEconomyPlugin.runSQL("DELETE FROM bank_credits WHERE creditID = " + credit.ID);
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to the chest shop system.
	 * 
	 * ==================================================
	 */

	public static long registerSupplyChest(Location location, long companyID) throws SQLException {
		long chestID = getNextEnumerator("chestID");

		WorldEconomyPlugin.runSQL("INSERT INTO chests (chestID, chestX, chestY, chestZ, chestWorld, chestType) VALUES"
				+ "(" + chestID + ", " + location.getBlockX() + ", " + location.getBlockY() + ", "
				+ location.getBlockZ() + ", \"" + location.getWorld().getName() + "\", \"supply\")");

		WorldEconomyPlugin.runSQL(
				"INSERT INTO supply_chests (chestID, chestOwnerCompanyID) VALUES (" + chestID + ", " + companyID + ")");

		return chestID;
	}

	public static SupplyChestData getSupplyChest(Location location) throws SQLException {
		ResultSet res = WorldEconomyPlugin.runSQLquery("SELECT * FROM supply_chests WHERE signX = "
				+ location.getBlockX() + " AND signY = " + location.getBlockY() + " AND signZ = " + location.getBlockZ()
				+ " AND signWorld = " + location.getWorld().getName());
		if (res.next()) {
			return new SupplyChestData(res.getLong("chestID"), location, res.getLong("chestOwnerCompanyID"));
		} else {
			return null;
		}
	}

	public static SupplyChestData getSupplyChest(long ID) throws SQLException {
		ResultSet res = WorldEconomyPlugin.runSQLquery("SELECT * FROM supply_chests WHERE chestID = " + ID);
		ResultSet res2 = WorldEconomyPlugin.runSQLquery("SELECT * FROM chests WHERE chestID = " + ID);

		if (res.next()) {
			return new SupplyChestData(ID, new Location(Bukkit.getWorld(res2.getString("chestWorld")),
					res2.getInt("chestX"), res2.getInt("chestY"), res2.getInt("chestZ")),
					res.getLong("chestOwnerCompanyID"));
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

		WorldEconomyPlugin.runSQL("INSERT INTO signs (signID, signX, signY, signZ, signWorld, signType) VALUES" + " ("
				+ signID + ", " + location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ()
				+ ", \"" + location.getWorld().getName() + "\", " + "\"shop\")");

		moveEnumerator("signID");

		return signID;
	}

	public static SignData getSign(Location location) throws SQLException {
		ResultSet res = WorldEconomyPlugin.runSQLquery("SELECT * FROM signs WHERE signX = " + location.getBlockX()
				+ " AND signY = " + location.getBlockY() + " AND signZ = " + location.getBlockZ()
				+ " AND signWorld = \"" + location.getWorld().getName() + "\"");

		if (!res.next()) {
			return null;
		}

		if (res.getString("signType").equals("shop")) {
			ResultSet res2 = WorldEconomyPlugin
					.runSQLquery("SELECT * FROM shop_signs WHERE signID = " + res.getLong("signID"));

			return new ShopSignData(res.getLong("signID"), res.getInt("signX"), res.getInt("signY"),
					res.getInt("signZ"), Bukkit.getWorld(res.getString("signWorld")), res.getString("signType"),
					res2.getLong("supplyChestID"), res2.getLong("productID"), res2.getDouble("signPrice"));
		}

		throw new RuntimeException("sign type not supported!");
	}

	public static ShopSignData getShopSign(Location location) throws SQLException {
		return (ShopSignData) getSign(location);
	}

	public static void removeShopSign(Block block) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT signID FROM signs WHERE signX = " + block.getX() + " AND signY = " + block.getY()
						+ " AND signZ = " + block.getZ() + " AND signWorld = \"" + block.getWorld().getName() + "\"");
		long signID = r.getLong("signID");
		WorldEconomyPlugin.runSQL("DELETE FROM signs WHERE signID = " + signID);
		WorldEconomyPlugin.runSQL("DELETE FROM shop_signs WHERE signID = " + signID);
	}
	/*
	 * ==================================================
	 * 
	 * This section is dedicated to companies.
	 * 
	 * ==================================================
	 */

	@SQLInjection
	@FeaturePlanned(feature = "AIs should be forced to create a bank account for incomes.")
	public static long registerCorporation(String name, long CEO_employeeID) throws SQLException {
		long companyID = registerBaseCompany(name, "corporation");

		WorldEconomyPlugin.runSQL("INSERT INTO companies_corporations (companyID, CEO_employeeID) VALUES (" + companyID
				+ ", " + CEO_employeeID + ")");

		Employee CEO = getEmployee(CEO_employeeID);
		if (CEO instanceof EmployeePlayer) {
			sendMail(0, getUserProfile(((EmployeePlayer) CEO).playerUUID).mailboxID,
					"Please remember to register a bank account called \"shop_income\" for your company in order to be able to use shops!");
		} else if (CEO instanceof EmployeeAI) {
			throw new NotImplementedException(
					"There will be no automatic informing of AIs on their ToDos because they cannot actually read mails!"
							+ "This should only be implemented when AI can recieve custom encoded messages or if there is"
							+ " an ID attachement telling the AI the parameters and the purpose of the mail.");
		} else {
			throw new NotSupportedException(
					"Registering a corporation as an employee that is not a player or an AI is not supported! (Recieved employee object from class "
							+ CEO.getClass().getCanonicalName() + ")");
		}

		return companyID;
	}

	@SQLInjection
	public static long registerCorporation(String name, OfflinePlayer CEO) throws SQLException {
		return registerCorporation(name, getUserProfile(CEO).employeeID);
	}

	@SQLInjection
	public static long registerPrivateCompany(String name, OfflinePlayer owner) throws SQLException {
		long companyID = registerBaseCompany(name, "private");

		WorldEconomyPlugin.runSQL("INSERT INTO companies_private (companyID, ownerEmployeeID) VALUES (" + companyID
				+ ", " + getUserProfile(owner).employeeID + ")");

		sendMail(0, getUserProfile(owner).mailboxID,
				"Please remember to register a bank account called \"shop_income\" for your company in order to be able to use shops!");

		return companyID;
	}

	@SQLInjection
	public static long registerBaseCompany(String name, String type) throws SQLException {
		long companyID = getNextEnumerator("companyID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO companies (companyID, companyName, companyType, companyEmployerID, companyBankingID, mailboxID) VALUES ("
						+ companyID + ", \"" + name + "\", \"" + type + "\", " + registerEmployer("company") + ", "
						+ registerBankingEntity("company") + "," + registerMailbox((Bank) null) + ")");

		moveEnumerator("companyID");

		return companyID;
	}

	@SQLInjection
	@AccelerationPotential(lvl = AccelerationLevel.GARBAGE_COLLECTOR)
	public static Company getCompany(String name) throws SQLException {
		ResultSet res = WorldEconomyPlugin.runSQLquery(
				"SELECT companyID, companyType, companyEmployerID, companyBankingID, mailboxID FROM companies WHERE companyName = \""
						+ name + "\"");
		if (res.next()) {
			long ID = res.getLong("companyID");
			String type = res.getString("companyType");
			long employerID = res.getLong("companyEmployerID");
			long bankingID = res.getLong("companyBankingID");
			long mailboxID = res.getLong("mailboxID");

			ResultSet r;

			switch (type) {
			case "corporation":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies_corporations WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException("Corporation \"" + name + "\" not in the corporations table!");
				}
				return new Corporation(ID, name, employerID, bankingID, r.getLong("CEO_employeeID"), mailboxID);
			case "private":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies_private WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException(
							"Private company \"" + name + "\" is not in the private companies table!");
				}
				return new PrivateCompany(ID, name, employerID, bankingID, r.getLong("ownerEmployeeID"), mailboxID);
			default:
				throw new RuntimeException("Invalid company type \"" + type + "\"!");
			}
		} else {
			return null;
		}
	}

	/**
	 * Devs: Please update getMailboxOwner(long mailboxID) along with this
	 * method.
	 * 
	 * @param ID
	 * @return
	 * @throws SQLException
	 */
	@AccelerationPotential(lvl = AccelerationLevel.GARBAGE_COLLECTOR)
	public static Company getCompany(long ID) throws SQLException {
		ResultSet res = WorldEconomyPlugin.runSQLquery(
				"SELECT companyName, companyType, companyEmployerID, companyBankingID, mailboxID FROM companies WHERE companyID = "
						+ ID + "");

		if (res.next()) {
			String type = res.getString("companyType");
			long employerID = res.getLong("companyEmployerID");
			long bankingID = res.getLong("companyBankingID");
			String name = res.getString("companyName");
			long mailboxID = res.getLong("mailboxID");

			ResultSet r;

			switch (type) {
			case "corporation":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies_corporations WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException("Corporation \"" + name + "\" not in the corporations table!");
				}
				return new Corporation(ID, name, employerID, bankingID, r.getLong("CEO_employeeID"), mailboxID);
			case "private":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies_private WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException(
							"Private company \"" + name + "\" is not in the private companies table!");
				}
				return new PrivateCompany(ID, name, employerID, bankingID, r.getLong("ownerEmployeeID"), mailboxID);
			case "bank":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException("Bank \"" + name + "\" is not in the banks table!");
				}
				return new BankCompany(ID, name, employerID, bankingID, mailboxID, r.getLong("bankID"));
			default:
				throw new RuntimeException("Invalid company type \"" + type + "\"!");
			}
		} else {
			return null;
		}
	}

	@AccelerationPotential(lvl = AccelerationLevel.GARBAGE_COLLECTOR)
	public static List<Company> getAllCompanies() throws SQLException {
		List<Company> out = new ArrayList<Company>();
		ResultSet res = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies");

		while (res.next()) {
			String type = res.getString("companyType");
			long employerID = res.getLong("companyEmployerID");
			long bankingID = res.getLong("companyBankingID");
			String name = res.getString("companyName");
			long mailboxID = res.getLong("mailboxID");
			long ID = res.getLong("companyID");

			ResultSet r;

			switch (type) {
			case "corporation":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies_corporations WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException("Corporation \"" + name + "\" not in the corporations table!");
				}
				out.add(new Corporation(ID, name, employerID, bankingID, r.getLong("CEO_employeeID"), mailboxID));
				break;
			case "private":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies_private WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException(
							"Private company \"" + name + "\" is not in the private companies table!");
				}
				out.add(new PrivateCompany(ID, name, employerID, bankingID, r.getLong("ownerEmployeeID"), mailboxID));
				break;
			case "bank":
				r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks WHERE companyID = " + ID);
				if (!r.next()) {
					throw new RuntimeException("Bank \"" + name + "\" is not in the banks table!");
				}
				out.add(new BankCompany(ID, name, employerID, bankingID, mailboxID, r.getLong("bankID")));
				break;
			default:
				throw new RuntimeException("Invalid company type \"" + type + "\"!");
			}
		}

		return out;
	}

	public static List<BankAccount> getCompanyBankAccountsByBankingID(long companyBankingID) throws SQLException {
		List<BankAccount> out = new ArrayList<BankAccount>();
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM bank_accounts WHERE customerBankingID = " + companyBankingID);

		while (r.next()) {
			out.add(new BankAccount(r.getLong("bankAccountID"), r.getLong("bankID"), r.getDouble("bankAccountBalance"),
					r.getString("bankAccountName"), r.getLong("customerBankingID"), r.getString("customerType")));
		}

		return out;
	}

	public static List<BankAccount> getCompanyBankAccountsByCompanyID(long companyID) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT companyBankingID FROM companies WHERE companyID = " + companyID);
		if (!r.next()) {
			throw new RuntimeException("The company with ID " + companyID + " does not exist!");
		}
		return getCompanyBankAccountsByBankingID(r.getLong("companyBankingID"));
	}

	public static List<BankAccount> getCompanyBankAccounts(Company company) throws SQLException {
		return getCompanyBankAccountsByBankingID(company.bankingID);
	}

	public static long registerEmployee(OfflinePlayer player) throws SQLException {
		long employeeID = getNextEnumerator("employeeID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO employees (employeeID, employeeType) VALUES (" + employeeID + ", \"player\")");

		moveEnumerator("employeeID");

		return employeeID;
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to the employment system.
	 * 
	 * ==================================================
	 */

	public static long registerEmployee(AIProfile ai) throws SQLException {
		long employeeID = getNextEnumerator("employeeID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO employees (employeeID, employeeType) VALUES (" + employeeID + ", \"AI\")");

		moveEnumerator("employeeID");

		return employeeID;
	}

	public static Employee getEmployee(long ID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM employees WHERE employeeID = " + ID);
		if (r.getString("employeeType").equals("player")) {
			ResultSet r2 = WorldEconomyPlugin
					.runSQLquery("SELECT playerUUID FROM user_profiles WHERE employeeID = " + ID);
			return new EmployeePlayer(ID, r.getLong("employeeLastResearch"),
					UUID.fromString(r2.getString("playerUUID")));
		} else if (r.getString("employeeType").equals("AI")) {
			ResultSet r2 = WorldEconomyPlugin.runSQLquery("SELECT aiID FROM ai_profiles WHERE employeeID = " + ID);
			return new EmployeeAI(ID, r.getLong("employeeLastResearch"), r2.getLong("aiID"));
		} else {
			throw new RuntimeException("Invalid employee type \"" + r.getString("employeeType") + "\"!");
		}
	}

	public static void registerEmploymentWithContract(long employerID, long employeeID, Contract contract)
			throws SQLException {
		WorldEconomyPlugin.runSQL("INSERT INTO employee_matching (employerID, employeeID, contractID) VALUES ("
				+ employerID + ", " + employeeID + ", " + contract.ID + ")");
		registerContract(contract);
	}

	public static void registerEmployment(long employerID, long employeeID, long contractID) throws SQLException {
		WorldEconomyPlugin.runSQL("INSERT INTO employee_matching (employerID, employeeID, contractID) VALUES ("
				+ employerID + ", " + employeeID + ", " + contractID + ")");
	}

	public static List<Employee> getEmployeesFromCompany(Company company) throws SQLException {
		List<Employee> out = new ArrayList<Employee>();
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM employee_matching WHERE employerID = " + company.companyEmployerID);
		while (r.next()) {
			out.add(getEmployee(r.getLong("employeeID")));
		}
		return out;
	}

	@FeaturePlanned(feature = "In the future, there should be more employer types than just companies!")
	public static Employer getEmployer(long employerID) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT employerType FROM employers WHERE employerID = " + employerID);

		if (!r.next()) {
			return null;
		}
		String type = r.getString("employerType");
		ResultSet r2;

		if (type.equals("company")) {
			r2 = WorldEconomyPlugin
					.runSQLquery("SELECT companyBankingID FROM companies WHERE companyEmployerID = " + employerID);
			return new Employer(employerID, r2.getLong("companyBankingID"));
		} else {
			throw new RuntimeException("Invalid employer type \"" + type + "\"!");
		}
	}

	public static long registerEmployer(String employerType) throws SQLException {
		long ID = getNextEnumerator("employerID");

		WorldEconomyPlugin.runSQL(
				"INSERT INTO employers (employerID, employerType) VALUES (" + ID + ",\"" + employerType + "\")");

		moveEnumerator("employerID");

		return ID;
	}

	@FeaturePlanned(feature = "There should be more than just employment contracts!")
	public static long registerContract(Contract contract) throws SQLException {
		if (contract instanceof ContractEmploymentDefault) {
			return registerContract((ContractEmploymentDefault) contract);
		} else {
			throw new RuntimeException("Invalid contract type \"" + contract.getClass().getCanonicalName() + "\"");
		}
	}

	public static long registerContract(ContractEmploymentDefault contract) throws SQLException {
		long contractID = getNextEnumerator("contractID");

		registerBaseContract(contractID, contract.getType());

		WorldEconomyPlugin
				.runSQL("INSERT INTO contracts_employment_default (contractID, contractSalary, contractLastSalary) VALUES ("
						+ contractID + ", " + contract.salary + ", " + contract.last_salary + ")");

		moveEnumerator("contractID");

		return contractID;
	}

	private static void registerBaseContract(long ID, String type) throws SQLException {
		WorldEconomyPlugin
				.runSQL("INSERT INTO contracts (contractID, contractType) VALUES (" + ID + ", \"" + type + "\")");
	}

	public static Map<Long, Long> getEmploymentInformation(long employeeID) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT (employerID, contractID) FROM employee_matching WHERE employeeID = " + employeeID);
		Map<Long, Long> map = new HashMap<Long, Long>();
		while (r.next()) {
			map.put(r.getLong("employerID"), r.getLong("contractID"));
		}
		return map;
	}

	@FeaturePlanned(feature = "There should be more than just employment contracts!")
	public static Contract getContract(long contractID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM contracts WHERE contractID = " + contractID);
		if (!r.next()) {
			return null;
		} else {
			String type = r.getString("contractType");
			ResultSet r2;

			if (type.startsWith("employment.")) {
				if (type.equals("employment.default")) {
					r2 = WorldEconomyPlugin.runSQLquery(
							"SELECT (contractSalary, contractLastSalary) FROM contracts_employment_default WHERE contractID = "
									+ contractID);
					return new ContractEmploymentDefault(contractID, r2.getDouble("contractSalary"),
							r2.getInt("contractLastSalary"));
				}
			}

			throw new RuntimeException(
					"The contract with ID " + contractID + " has an invalid type (\"" + type + "\")!");
		}
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to products.
	 * 
	 * ==================================================
	 */

	@SQLInjection
	public static long registerProduct(long productManifacturerID, String name, double price, ItemStack product)
			throws SQLException {
		long productID = getNextEnumerator("productID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO products (productID, productName, productPrice, productManifacturerID, productItemID, productItemAmount) VALUES ("
						+ productID + ", \"" + name + "\", " + price + ", " + productManifacturerID + ", \""
						+ product.getType().toString() + "\", " + product.getAmount() + ")");

		moveEnumerator("productID");

		return productID;
	}

	public static Product getProduct(long ID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM products WHERE productID = " + ID);
		if (!r.next()) {
			return null;
		} else {
			return new Product(ID, r.getString("productName"), r.getLong("productManifacturerID"),
					r.getString("productItemID"), r.getInt("productItemAmount"), r.getDouble("productPrice"));
		}
	}

	public static List<Product> getProductsFromCompany(Company company) throws SQLException {
		List<Product> out = new ArrayList<Product>();
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM products WHERE productManifacturerID = " + company.ID);
		while (r.next()) {
			out.add(new Product(r.getLong("productID"), r.getString("productName"), company.ID,
					r.getString("productItemID"), r.getInt("productItemAmount"), r.getDouble("productPrice")));
		}
		return out;
	}

	public static long registerAI() throws SQLException {
		long aiID = getNextEnumerator("aiID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO ai_profiles (aiID, employeeID, aiAsEmployerID, username, aiBankingID) VALUES ("
						+ aiID + ", " + getNextEnumerator("employeeID") + ", " + getNextEnumerator("employerID")
						+ ", \"AI " + aiID + "\", " + getNextEnumerator("bankingID") + ")");

		moveEnumerator("employeeID");
		moveEnumerator("employerID");
		moveEnumerator("bankingID");

		moveEnumerator("aiID");

		return aiID;
	}

	/**
	 * Devs: Please update getMailboxOwner(long mailboxID) along with this
	 * method.
	 * 
	 * @param aiID
	 * @return
	 * @throws SQLException
	 */
	public static AIProfile getAI(long aiID) throws SQLException {

		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM ai_profiles WHERE aiID = " + aiID);

		if (!r.next()) {
			return null;
		}
		return new AIProfile(r.getLong("aiID"), r.getString("username"), r.getLong("aiBankingID"),
				r.getLong("employeeID"), r.getLong("aiAsEmployerID"), r.getLong("mailboxID"),
				getProfessions(r.getLong("employeeID")), r.getDouble("health"), r.getDouble("maxHealth"),
				r.getDouble("saturation"), r.getDouble("happyness"), r.getBoolean("religious"),
				r.getDouble("religious_satisfaction"), r.getDouble("endurance"), r.getDouble("maxEndurance"),
				r.getBoolean("inHeaven"), r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age")));
	}

	public static List<AIProfile> getAllAIs() throws SQLException {
		List<AIProfile> out = new ArrayList<AIProfile>();
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM ai_profiles");

		while (r.next()) {
			out.add(new AIProfile(r.getLong("aiID"), r.getString("username"), r.getLong("aiBankingID"),
					r.getLong("employeeID"), r.getLong("aiAsEmployerID"), r.getLong("mailboxID"),
					getProfessions(r.getLong("employeeID")), r.getDouble("health"), r.getDouble("maxHealth"),
					r.getDouble("saturation"), r.getDouble("happyness"), r.getBoolean("religious"),
					r.getDouble("religious_satisfaction"), r.getDouble("endurance"), r.getDouble("maxEndurance"),
					r.getBoolean("inHeaven"), r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age"))));
		}
		return out;
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to the mail system.
	 * 
	 * ==================================================
	 */

	public static long registerMailbox(Bank bank) throws SQLException {
		return registerBaseMailbox("bank");
	}

	public static long registerMailbox(Company company) throws SQLException {
		return registerBaseMailbox("company");
	}

	public static long registerMailbox(UserProfile userProfile) throws SQLException {
		return registerBaseMailbox("player");
	}

	public static long registerMailbox(AIProfile aiProfile) throws SQLException {
		return registerBaseMailbox("ai");
	}

	public static long registerBaseMailbox(String ownerType) throws SQLException {
		long ID = getNextEnumerator("mailboxID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO mailboxes (mailboxID, ownerType) VALUES (" + ID + ", \"" + ownerType + "\")");

		moveEnumerator("mailboxID");
		return ID;
	}

	@SQLInjection
	public static void sendMail(long senderMailboxID, long recieverMailboxID, String message) throws SQLException {
		WorldEconomyPlugin.runSQL("INSERT INTO mails (mailboxID, senderMailboxID, message) VALUES (" + recieverMailboxID
				+ ", " + senderMailboxID + ", \"" + message + "\")");

		MailSubsystem.deliveryNotification(senderMailboxID, recieverMailboxID, message);
	}

	public static List<Mail> getMails(long mailboxID, int max) throws SQLException {
		ArrayList<Mail> out = new ArrayList<Mail>(max);
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM mails WHERE mailboxID = " + mailboxID);

		for (int i = 0; i < max; i++) {
			if (!r.next()) {
				break;
			}
			out.add(new Mail(r.getLong("mailID"), r.getString("message"), r.getLong("senderMailboxID"), mailboxID));
		}
		return out;
	}

	public static List<Mail> getMails(OfflinePlayer player, int max) throws SQLException {
		return getMails(getUserProfile(player).mailboxID, max);
	}

	public static List<Mail> getMails(Company company, int max) throws SQLException {
		return getMails(getMailboxID(company), max);
	}

	public static String getMailboxOwnerType(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT ownerType FROM mailboxes WHERE mailboxID = " + mailboxID);

		if (!r.next()) {
			return null;
		}
		return r.getString("ownerType");
	}

	public static void removeMail(long mailID, long mailboxID) throws SQLException {
		// the second condition is just for security purposes
		WorldEconomyPlugin.runSQL("DELETE FROM mails WHERE mailID = " + mailID + " AND mailboxID = " + mailboxID);
	}

	public static long getMailboxID(Company company) throws SQLException {
		return getMailboxIDOfCompany(company.ID);
	}

	public static long getMailboxIDOfCompany(long companyID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT mailboxID FROM companies WHERE companyID = " + companyID);
		if (!r.next()) {
			return 0;
		}
		return r.getLong("mailboxID");
	}

	public static long getMailboxID(OfflinePlayer player) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery(
				"SELECT mailboxID FROM user_profiles WHERE playerUUID = \"" + player.getUniqueId().toString() + "\"");
		if (!r.next()) {
			return 0;
		}
		return r.getLong("mailboxID");
	}

	public static long getMailboxID(AIProfile ai) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT mailboxID FROM ai_profiles WHERE aiID = " + ai.aiID);
		if (!r.next()) {
			return 0;
		}
		return r.getLong("mailboxID");
	}

	public static long getMessageCount(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT COUNT(mailboxID) AS total FROM mails WHERE mailboxID = " + mailboxID);
		r.next();
		return r.getLong("total");
	}

	public static UserProfile getMailboxOwnerAsUserProfile(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles WHERE mailboxID = " + mailboxID);

		if (r.next()) {
			return new UserProfile(r.getLong("playerID"), UUID.fromString(r.getString("playerUUID")),
					r.getInt("employeeID"), r.getInt("playerAsEmployerID"), r.getString("username"),
					r.getInt("playerBankingID"), mailboxID, WEDB.getProfessions(r.getLong("employeeID")),
					r.getDouble("health"), r.getDouble("maxHealth"), r.getDouble("saturation"),
					r.getDouble("happyness"), r.getBoolean("religious"), r.getDouble("religious_satisfaction"),
					r.getDouble("endurance"), r.getDouble("maxEndurance"), r.getBoolean("inHeaven"),
					r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age")));
		} else {
			return null;
		}
	}

	public static AIProfile getMailboxOwnerAsAI(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM ai_profiles WHERE mailboxID = " + mailboxID);

		if (!r.next()) {
			return null;
		}
		return new AIProfile(r.getLong("aiID"), r.getString("username"), r.getLong("aiBankingID"),
				r.getLong("employeeID"), r.getLong("aiAsEmployerID"), r.getLong("mailboxID"),
				getProfessions(r.getLong("employeeID")), r.getDouble("health"), r.getDouble("maxHealth"),
				r.getDouble("saturation"), r.getDouble("happyness"), r.getBoolean("religious"),
				r.getDouble("religious_satisfaction"), r.getDouble("endurance"), r.getDouble("maxEndurance"),
				r.getBoolean("inHeaven"), r.getLong("heavenEndTimeMillis"), Age.valueOf(r.getString("age")));
	}

	public static long getMailboxOwnerAsCompanyID(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT companyID FROM companies WHERE mailboxID = " + mailboxID);
		if (!r.next()) {
			return 0;
		}
		return r.getLong("companyID");
	}

	// TODO optimize performance
	public static Company getMailboxOwnerAsCompany(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT companyID FROM companies WHERE mailboxID = " + mailboxID);
		if (!r.next()) {
			return null;
		}
		return getCompany(r.getLong("companyID"));
	}

	public static BankCompany getMailboxOwnerAsBankCompany(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery(
				"SELECT * FROM companies INNER JOIN banks ON banks.companyID = companies.companyID WHERE mailboxID = "
						+ mailboxID);
		if (!r.next()) {
			return null;
		}
		return new BankCompany(r.getLong("companyID"), r.getString("companyName"), r.getLong("companyEmployerID"),
				r.getLong("companyBankingID"), r.getLong("mailboxID"), r.getLong("bankID"));
	}

	public static MailboxOwner getMailboxOwner(long mailboxID) throws SQLException {
		String type = getMailboxOwnerType(mailboxID);

		switch (type) {
		case "player":
			return getMailboxOwnerAsUserProfile(mailboxID);
		case "ai":
			return getMailboxOwnerAsAI(mailboxID);
		case "company":
			return getMailboxOwnerAsCompany(mailboxID);
		case "bank":
			return getMailboxOwnerAsBankCompany(mailboxID);
		default:
			throw new RuntimeException("The mailbox owner's type is invalid (\"" + type + "\"!");
		}
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to the stock market.
	 * 
	 * ==================================================
	 */

	@SQLInjection
	public static long registerBaseStockMarketProduct(String name, String type) throws SQLException {
		long ID = getNextEnumerator("stockMarketProductID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO stock_market_products (stockMarketProductID, stockMarketProductName, stockMarketProductType) VALUES ("
						+ ID + ", \"" + name + "\",\"" + type + "\")");

		moveEnumerator("stockMarketProductID");

		return ID;
	}

	@SQLInjection
	public static long registerShare(long companyID, String name, String shareType, double partage, long amount,
			double dividend) throws SQLException {
		long ID = registerBaseStockMarketProduct(name, "share");

		WorldEconomyPlugin
				.runSQL("INSERT INTO shares (stockMarketProductID, shareTotalAmount, shareTotalPartage, shareCompanyID, shareDividend) VALUES"
						+ "(" + ID + ", " + amount + ", " + partage + ", " + companyID + ", " + dividend + ")");

		return ID;
	}

	@SQLInjection
	public static long registerShare(Company company, String name, String shareType, double partage, long amount,
			double dividend) throws SQLException {
		return registerShare(company.ID, name, shareType, partage, amount, dividend);

	}

	public static Share getShare(long stockMarketProductID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery(
				"SELECT * FROM shares INNER JOIN stock_market_products ON stock_market_products.stockMarketProductID = "
						+ stockMarketProductID + " WHERE stockMarketProductID = " + stockMarketProductID);

		if (!r.next()) {
			return null;
		}
		return new Share(stockMarketProductID, r.getString("stockMarketProductName"), r.getString("shareType"),
				r.getDouble("stockMarketProductPrice"), r.getLong("shareTotalAmount"), r.getLong("shareCompanyID"),
				r.getLong("shareTotalPartage"), r.getDouble("shareDividend"));
	}

	public static StockMarketProductStack getStockMarketProductStack(long stackID) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM stock_market_possesions WHERE stockMarketPossesionID = " + stackID);
		if (!r.next()) {
			return null;
		}
		return new StockMarketProductStack(stackID, r.getLong("stockMarketProductID"), r.getLong("ownerBankAccountID"),
				r.getLong("purchaseTime"), r.getDouble("purchasePrice"), r.getLong("purchaseAmount"));
	}

	/**
	 * This method returns the first product stack in the database that belongs
	 * to the given BankAccount.
	 * 
	 * @param owner
	 * @param stockMarketProductID
	 * @return
	 * @throws SQLException
	 */
	public static StockMarketProductStack getAStockMarketProductStack(BankAccount owner, long stockMarketProductID)
			throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM stock_market_possesions WHERE ownerBankAccountID = " + owner.getID());
		if (!r.next()) {
			return null;
		}
		return new StockMarketProductStack(r.getLong("stockMarketPossesionID"), stockMarketProductID, owner.getID(),
				r.getLong("purchaseTime"), r.getLong("purchasePrice"), r.getLong("purchaseAmount"));
	}

	public static void registerStackMarketProductStack(StockMarketProductStack stack) throws SQLException {
		// TODO: Warning: No primary key inserted!
		WorldEconomyPlugin
				.runSQL("INSERT INTO stock_market_possesions (stockMarketProductID, ownerBankAccountID, purchaseTime, purchasePrice, purchaseAmount) VALUES ("
						+ stack.productID + ", " + stack.ownerBankAccountID + ", " + stack.purchaseTime + ", "
						+ stack.purchasePrice + ", " + stack.purchaseAmount + ")");
	}

	public static void setStockMarketProductStackAmount(long ID, long amount) throws SQLException {
		WorldEconomyPlugin.runSQL("UPDATE stock_market_possesions SET purchaseAmount = " + amount
				+ " WHERE stockMarketPossesionID = " + ID);
	}

	public static void setStockMarketProductStackAmount(StockMarketProductStack stack, long amount)
			throws SQLException {
		setStockMarketProductStackAmount(stack.stackID, amount);
	}

	public static void removeStockMarketProductStack(long ID) throws SQLException {
		WorldEconomyPlugin.runSQL("DELETE FROM stock_market_possesions WHERE stockMarketPossesionID = " + ID);
	}

	/**
	 * This method is used to transfer stocks from one bank account to
	 * another.<br>
	 * <b style="color:red">This does not transfer money!</b>
	 * 
	 * @param seller
	 * @param buyer
	 * @param stockMarketProductID
	 * @param amount
	 * @param price
	 * @throws SQLException
	 */
	public static void transferStock(BankAccount seller, BankAccount buyer, long stockMarketProductID, long amount,
			double price) throws SQLException {
		WorldEconomyPlugin
				.runSQL("INSERT INTO stock_market_possesions (stockMarketProductID, ownerBankAccountID, purchaseTime, purchasePrice, purchaseAmount) VALUES ("
						+ stockMarketProductID + ", " + buyer + ", " + System.currentTimeMillis() + ", " + price + ", "
						+ amount + ")");
		long transfered_amount = 0;
		StockMarketProductStack stack;

		while (transfered_amount < amount) {
			stack = getAStockMarketProductStack(seller, stockMarketProductID);
			if (stack == null) {
				throw new RuntimeException(
						"WEDB.transferStock is not safe and does not check weather the seller has enough stock to go through with the trade! This is exactly what happend!");
			}
			if (stack.purchaseAmount > amount) {
				setStockMarketProductStackAmount(stack, stack.purchaseAmount - (amount - transfered_amount));
				// technically, one would have to set the variable
				// transfered_money = amount. I saved myself, the compiler and
				// the server the time because it is not needed after the loop
				// right now. If that changes, it has to be implemented.
				break;
			} else {
				removeStockMarketProductStack(stack.stackID);
				transfered_amount += stack.purchaseAmount;
			}
		}
		// TODO send notification mail!
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to resources.
	 * 
	 * ==================================================
	 */

	public static void registerResource(CustomItem material, long startAmount, double stepSize, double maxPrice)
			throws SQLException {
		WorldEconomyPlugin
				.runSQL("INSERT INTO resources (resourceCustomItemID, resourceStoredAmount, resourcePriceStep, resourceMaxPrice)"
						+ " VALUES (\"" + material.ID + "\", " + startAmount + ", " + stepSize + ", " + maxPrice + ")");
	}

	public static double getResourcePrice(CustomItem material) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM resources WHERE resourceCustomItemID = \"" + material.ID + "\"");
		if (!r.next()) {
			throw new RuntimeException("Resource \"" + material.ID + "\" not found!");
		}
		return r.getDouble("resourceMaxPrice") * Math.pow(0.5,
				1 / (r.getDouble("resourcePriceStep") * (WorldEconomyPlugin.AI_count + WorldEconomyPlugin.user_count))
						* r.getDouble("resourceStoredAmount"));
	}

	public static double getResourcePriceSell(CustomItem material, long amount) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM resources WHERE resourceCustomItemID = \"" + material.ID + "\"");
		if (!r.next()) {
			throw new RuntimeException("Resource \"" + material.ID + "\" not found!");
		}

		double out = 0;
		for (long i = 0; i < amount; i++) {
			out += r.getDouble("resourceMaxPrice") * Math.pow(0.5, 1
					/ (r.getDouble("resourcePriceStep") * (WorldEconomyPlugin.AI_count + WorldEconomyPlugin.user_count))
					* (r.getDouble("resourceStoredAmount") + amount));
		}
		return out;
	}

	public static double getResourcePriceBuy(CustomItem material, long amount) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM resources WHERE resourceCustomItemID = \"" + material.ID + "\"");
		if (!r.next()) {
			throw new RuntimeException("Resource \"" + material.ID + "\" not found!");
		}

		double out = 0;
		for (long i = 0; i < amount; i++) {
			out += r.getDouble("resourceMaxPrice") * Math.pow(0.5, 1
					/ (r.getDouble("resourcePriceStep") * (WorldEconomyPlugin.AI_count + WorldEconomyPlugin.user_count))
					* (r.getDouble("resourceStoredAmount") - amount));
		}
		return out;
	}

	public static double getResourcePriceWithFallback(CustomItem resource) {
		try {
			ResultSet r = WorldEconomyPlugin
					.runSQLquery("SELECT * FROM resources WHERE resourceItemID = \"" + resource.ID + "\"");
			if (!r.next()) {
				throw new RuntimeException("Resource \"" + resource.toString() + "\" not found!");
			}
			return r.getDouble("resourceMaxPrice") * Math.pow(0.5, 1 / (r.getDouble("resourcePriceStep")
					* (WorldEconomyPlugin.AI_count + WorldEconomyPlugin.user_count)));
		} catch (SQLException e) {
			e.printStackTrace();
			return Double.NaN;
		}
	}

	public static List<CustomItem> getAllResources() throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM resources");
		List<CustomItem> out = new ArrayList<CustomItem>();

		while (r.next()) {
			out.add(CustomItemRegistry.getItem(r.getString("resourceCustomItemID")));
		}

		return out;
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to custom blocks.
	 * 
	 * ==================================================
	 */

	public static void registerCustomBlock(Location location, CustomBlockType block, CustomBlockData data)
			throws SQLException {
		WorldEconomyPlugin
				.runSQL("INSERT INTO custom_blocks (blockX, blockY, blockZ, blockWorld, blockType, blockData) VALUES ("
						+ location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ() + ", \""
						+ location.getWorld().getName() + "\", \"" + block.ID + "\", \"" + data.save() + "\")");
	}

	public static void updateBlockData(Block block, String data) throws SQLException {
		Location l = block.getLocation();
		WorldEconomyPlugin.runSQL("UPDATE custom_blocks SET blockData = \"" + data + "\" WHERE blockX = "
				+ l.getBlockX() + " AND blockY = " + l.getBlockY() + " AND blockZ = " + l.getBlockZ());
	}

	public static void updateBlockData(Block block, CustomBlockData data) throws SQLException {
		updateBlockData(block, data.save());
	}

	public static List<CustomBlock> getAllCustomBlocks(World world)
			throws SQLException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM custom_blocks WHERE blockWorld = \"" + world.getName() + "\"");
		List<CustomBlock> out = new ArrayList<CustomBlock>();
		CustomBlockType type;
		while (r.next()) {
			type = CustomBlockTypeRegistry.getBlock(r.getString("blockType"));
			out.add(new CustomBlock(
					new ComparableLocation(world, r.getInt("blockX"), r.getInt("blockY"), r.getInt("blockZ")), type,
					CustomBlockData.create(type.blockDataType, r.getString("blockData"))));
		}
		return out;
	}

	public static void removeCustomBlock(Location location) throws SQLException {
		WorldEconomyPlugin.runSQL("DELETE FROM custom_blocks WHERE blockX = " + location.getBlockX() + " AND blockY = "
				+ location.getBlockY() + " AND blockZ = " + location.getBlockZ() + " AND blockWorld = \""
				+ location.getWorld().getName() + "\"");
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to research.
	 * 
	 * ==================================================
	 */

	public static void addResearchItem(ResearchEntity entity, ResearchItem item) throws SQLException {
		WorldEconomyPlugin.runSQL("INSERT INTO research (researchItem, researchEntityID, researchEntityType) VALUES (\""
				+ item.getID() + "\", " + entity.getResearchSpecifiyEntityID() + ", \"" + entity.getResearchEntityType()
				+ "\")");
	}

	public static boolean hasResearchItem(ResearchEntity entity, ResearchItem item) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM research WHERE researchEntityID = "
				+ entity.getResearchSpecifiyEntityID() + " AND researchEntityType = \"" + entity.getResearchEntityType()
				+ "\" AND researchItem = \"" + item.getID() + "\"");
		return r.next();
	}

	public static List<ResearchItem> getRawResearchItems(ResearchEntity entity) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM research WHERE researchEntityID = " + entity.getResearchSpecifiyEntityID()
						+ " AND researchEntityType = \"" + entity.getResearchEntityType() + "\"");
		List<ResearchItem> out = new ArrayList<ResearchItem>();

		while (r.next()) {
			out.add(ResearchItemRegistry.get(r.getString("researchItem")));
		}
		return out;
	}

	public static List<ResearchItem> getResearchItems(ResearchEntity entity) throws SQLException {
		if (entity instanceof UserProfile) {
			for (UserProfile profile : WorldEconomyPlugin.research_age_bypass) {
				if (profile.uuid.equals(((UserProfile) entity).uuid)) {
					return ResearchItemRegistry.getContents();
				}
			}
		}
		return getRawResearchItems(entity);
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to player and AI statistics.
	 * 
	 * ==================================================
	 */

	public static void addStatistic(String ID, long entityID, String entityType, double value) throws SQLException {
		WorldEconomyPlugin.runSQLasync("INSERT INTO statistics (statisticID, entityID, entityType, value) VALUES (\""
				+ ID + "\", " + entityID + ", \"" + entityType + "\", " + value + ")");
	}

	public static void addStatistic(StatisticalObject object, StatisticCategory category, long entityID,
			String entityType, double value) throws SQLException {
		addStatistic(object.getStatisticID() + "$" + category.ID, entityID, entityType, value);
	}

	public static void addAllStatistics(StatisticalObject object, long entityID, String entityType)
			throws SQLException {
		for (StatisticCategory category : StatisticCategoryRegistry.getContents()) {
			addStatistic(object, category, entityID, entityType, 0);
		}
	}

	public static double getStatistic(String ID, long entityID, String entityType) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM statistics WHERE statisticID = \"" + ID
				+ "\" AND entityID = " + entityID + " AND entityType = \"" + entityType + "\"");

		if (!r.next()) {
			return Double.NaN;
		}
		return r.getDouble("value");
	}

	public static void changeStatistic(String ID, long entityID, String entityType, double value) throws SQLException {
		WorldEconomyPlugin.runSQL("UPDATE statistics SET value = " + value + " WHERE statisticID = " + ID
				+ " AND entityID = " + entityID + " AND entityType = \"" + entityType + "\"");
	}

	public static void changeStatistic(StatisticalObject object, StatisticCategory category, long entityID,
			String entityType, double value) throws SQLException {
		changeStatistic(object.getStatisticID() + "$" + category.ID, entityID, entityType, value);
	}

	public static void incrementStatistic(StatisticalObject object, StatisticCategory category, long entityID,
			String entityType, double amount) throws SQLException {
		WorldEconomyPlugin.runSQL("UPDATE statistics SET value = value + " + amount + " WHERE statisticID = \""
				+ object.getStatisticID() + "$" + category.ID + "\" AND entityID = " + entityID + " AND entityType = \""
				+ entityType + "\"");
	}

	/*
	 * ==================================================
	 * 
	 * This section is dedicated to professions.
	 * 
	 * ==================================================
	 */

	public static Set<EmployeeProfession> getProfessions(long employeeID) throws SQLException {
		Set<EmployeeProfession> out = new TreeSet<EmployeeProfession>();
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM employee_professions WHERE employeeID = " + employeeID);
		while (r.next()) {
			try {
				out.add(EmployeeProfession.valueOf(r.getString("professionName")));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				WorldEconomyPlugin.plugin.getLogger().log(Level.WARNING, "Tried to get unkown profession \""
						+ r.getString("professionName")
						+ "\". Is the database outdated (have you changed the plugin version, changing the existing professions)?");
			}
		}
		return out;
	}

}
