package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Employee;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.EmployeePlayer;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.Mail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailSubsystem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.Product;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.ShopSignData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.SignData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.market.SupplyChestData;

public class WEDB {

	/**
	 * Devs: Please update getMailboxOwner(long mailboxID) along with this
	 * method.
	 * 
	 * @param player
	 * @return
	 * @throws SQLException
	 */
	public static WorldEconomyProfile getUserProfile(OfflinePlayer player) throws SQLException {
		ResultSet r = WorldEconomyPlugin
				.runSQLquery("SELECT * FROM user_profiles WHERE playerUUID = \"" + player.getUniqueId() + "\"");

		if (r.next()) {
			return new WorldEconomyProfile(player.getUniqueId(), r.getLong("employeeID"),
					r.getLong("playerAsEmployerID"), r.getString("username"), r.getLong("playerBankingID"),
					r.getLong("mailboxID"));
		} else {
			return null;
		}
	}

	public static List<WorldEconomyProfile> getAllUserProfiles() throws SQLException {
		List<WorldEconomyProfile> out = new ArrayList<WorldEconomyProfile>();
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles");
		while (r.next()) {
			out.add(new WorldEconomyProfile(UUID.fromString(r.getString("playerUUID")), r.getLong("employeeID"),
					r.getLong("playerAsEmployerID"), r.getString("username"), r.getLong("playerBankingID"),
					r.getLong("mailboxID")));
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
				.runSQL("INSERT INTO user_profiles (playerUUID, employeeID, playerAsEmployerID, playerBankingID, username, mailboxID)"
						+ " VALUES (\"" + player.getUniqueId().toString() + "\", " + ID + ", "
						+ getNextEnumerator("employerID") + ", " + getNextEnumerator("bankingID") + ", \""
						+ player.getName() + "\", " + registerBaseMailbox("player") + ")");

		moveEnumerator("employerID");
		moveEnumerator("bankingID");
	}

	public static void registerBankAccount(BankAccount account) throws SQLException {
		WorldEconomyPlugin
				.runSQL("INSERT INTO bank_accounts (bankAccountBalance, bankID, bankAccountName, customerBankingID, customerType) VALUES ("
						+ account.getBalance() + ", " + account.getBankID() + ", \"" + account.getName() + "\", "
						+ account.getAccountHolderID() + ", \"" + account.getType() + "\")");
	}

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

	public static List<BankAccount> getAllBankAccounts(Player player) throws SQLException {
		List<BankAccount> out = new ArrayList<BankAccount>();
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM bank_accounts");
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

	public static void setBankAccountName(BankAccount account, String name) throws SQLException {
		WorldEconomyPlugin.runSQL(
				"UPDATE bank_accounts SET bankAccountName = \"" + name + "\" WHERE bankAccountID = " + account.getID());
	}

	public static void registerBank(String name) throws SQLException {
		WorldEconomyPlugin.runSQL(
				"INSERT INTO banks (bankID, bankName) VALUES (" + getNextEnumerator("bankID") + ", \"" + name + "\")");
		moveEnumerator("bankID");
	}

	public static Bank getBank(String name) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks WHERE bankName = \"" + name + "\"");
		if (!r.next()) {
			return null;
		} else {
			return new Bank(r.getLong("bankID"), r.getString("bankName"));
		}
	}

	public static Bank getBank(long bankID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks WHERE bankID = " + bankID);
		if (!r.next()) {
			return null;
		}
		return new Bank(bankID, r.getString("bankName"));
	}

	public static List<Bank> getAllBanks() throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks");
		List<Bank> out = new ArrayList<Bank>();

		while (r.next()) {
			out.add(new Bank(r.getLong("bankID"), r.getString("bankName")));
		}
		return out;
	}

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

	public static long registerCorporation(String name, long CEO_employeeID) throws SQLException {
		long companyID = getNextEnumerator("companyID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO companies (companyID, companyName, companyType, companyEmployerID, companyBankingID) VALUES ("
						+ companyID + ", \"" + name + "\", \"corporation\", " + registerEmployer("company") + ", "
						+ getNextEnumerator("bankingID") + ")");

		WorldEconomyPlugin.runSQL("INSERT INTO companies_corporations (companyID, CEO_employeeID) VALUES (" + companyID
				+ ", " + CEO_employeeID + ")");

		moveEnumerator("companyID");
		moveEnumerator("bankingID");

		return companyID;
	}

	public static long registerCorporation(String name, OfflinePlayer CEO) throws SQLException {
		return registerCorporation(name, getUserProfile(CEO).employeeID);
	}

	public static long registerPrivateCompany(String name, OfflinePlayer owner) throws SQLException {
		long companyID = getNextEnumerator("companyID");

		WorldEconomyPlugin
				.runSQL("INSERT INTO companies (companyID, companyName, companyType, companyEmployerID, companyBankingID) VALUES ("
						+ companyID + ", \"" + name + "\", \"private\", " + registerEmployer("company") + ", "
						+ getNextEnumerator("bankingID") + ")");

		WorldEconomyPlugin.runSQL("INSERT INTO companies_private (companyID, ownerEmployeeID) VALUES (" + companyID
				+ ", " + getUserProfile(owner).employeeID + ")");

		moveEnumerator("companyID");
		moveEnumerator("bankingID");

		return companyID;
	}

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
			default:
				throw new RuntimeException("Invalid company type \"" + type + "\"!");
			}
		} else {
			return null;
		}
	}

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

	public static Employee getEmployee(long ID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM employees WHERE employeeID = " + ID);
		if (r.getString("employeeType").equals("player")) {
			ResultSet r2 = WorldEconomyPlugin
					.runSQLquery("SELECT playerUUID FROM user_profiles WHERE employeeID = " + ID);
			return new EmployeePlayer(ID, UUID.fromString(r2.getString("playerUUID")));
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
				r.getLong("employeeID"), r.getLong("aiAsEmployerID"), r.getLong("mailboxID"));
	}

	public static List<AIProfile> getAllAIs() throws SQLException {
		List<AIProfile> out = new ArrayList<AIProfile>();
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM ai_profiles");

		while (r.next()) {
			out.add(new AIProfile(r.getLong("aiID"), r.getString("username"), r.getLong("aiBankingID"),
					r.getLong("employeeID"), r.getLong("aiAsEmployerID"), r.getLong("mailboxID")));
		}
		return out;
	}

	public static long registerMailbox(Company company) throws SQLException {
		return registerBaseMailbox("company");
	}

	public static long registerMailbox(WorldEconomyProfile userProfile) throws SQLException {
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
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT mailboxID FROM companies WHERE companyID = " + company.ID);
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

	public static WorldEconomyProfile getMailboxOwnerAsUserProfile(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles WHERE mailboxID = " + mailboxID);

		if (r.next()) {
			return new WorldEconomyProfile(UUID.fromString(r.getString("playerUUID")), r.getInt("employeeID"),
					r.getInt("playerAsEmployerID"), r.getString("username"), r.getInt("playerBankingID"), mailboxID);
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
				r.getLong("employeeID"), r.getLong("aiAsEmployerID"), r.getLong("mailboxID"));
	}

	public static long getMailboxOwnerAsCompanyID(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT companyID FROM companies WHERE mailboxID = " + mailboxID);
		if (!r.next()) {
			return 0;
		}
		return r.getLong("companyID");
	}

	public static Company getMailboxOwnerAsCompany(long mailboxID) throws SQLException {
		ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT companyID FROM companies WHERE mailboxID = " + mailboxID);
		if (!r.next()) {
			return null;
		}
		return getCompany(r.getLong("companyID"));
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
		default:
			throw new RuntimeException("The mailbox owner's type is invalid (\"" + type + "\"!");
		}
	}

}
