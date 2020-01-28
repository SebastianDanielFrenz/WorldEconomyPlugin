package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public class WorldEconomyCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("This is the main command for the World Economy Plugin!");
			return true;
		} else {
			if (args[0].equalsIgnoreCase("register")) {
				if (args.length > 1) {
					if (args[1].equalsIgnoreCase("bank")) {
						if (args.length > 2) {
							try {
								WorldEconomyPlugin.registerBank(args[2]);
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully registered the bank \""
										+ args[2] + "\"!");

								return true;
							} catch (SQLException e) {
								e.printStackTrace();
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
								return true;
							}
						} else {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4/we register bank <name>");
							return true;
						}
					} else if (args[1].equalsIgnoreCase("bank_account")) {
						if (!(sender instanceof Player)) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX
									+ "§4You have to be a player in order to create a bank account!");
							return true;
						}
						try {
							Bank bank = WorldEconomyPlugin.getBank(args[2]);
							if (bank == null) {
								sender.sendMessage(
										WorldEconomyPlugin.PREFIX + "§4The bank \"" + args[2] + "\" does not exist!");
								return true;
							}
							WorldEconomyPlugin.registerUserBankAccount((Player) sender,
									new BankAccount((Player) sender, bank.ID, 0, args[3]));
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully created the bank account!");
							return true;
						} catch (SQLException e) {
							e.printStackTrace();
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
							return true;
						}
					} else if (args[1].equalsIgnoreCase("company")) {
						if (!(sender instanceof Player)) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX
									+ "§4You have to be a player in order to create and own a company!");
							return true;
						}
						if (args.length > 3) {
							try {
								String name = args[2];

								switch (args[3]) {
								case "corporation":
									sender.sendMessage(WorldEconomyPlugin.PREFIX
											+ "Successfully created the corporation with ID "
											+ WorldEconomyPlugin.registerCorporation(name, (Player) sender) + "!");
									break;
								case "private":
									sender.sendMessage(WorldEconomyPlugin.PREFIX
											+ "Successfully created the private company with ID "
											+ WorldEconomyPlugin.registerPrivateCompany(name, (Player) sender));
									break;
								default:
									sender.sendMessage(WorldEconomyPlugin.PREFIX
											+ "§4Invalid company type! §eType \"/we company types\" to see the available types.");
								}
								return true;
							} catch (SQLException e) {
								e.printStackTrace();
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
								return true;
							}
						} else {
							return false;
						}
					} else if (args[1].equalsIgnoreCase("supply_chest")) {
						if (args.length > 2) {
							Block block = ((Player) sender).rayTraceBlocks(5).getHitBlock();
							if (block.getType() == Material.CHEST) {
								try {
									Company company = WorldEconomyPlugin.getCompany(args[2]);

									if (company == null) {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The company \"" + args[2]
												+ "\" does not exist!");
										return true;
									}

									sender.sendMessage(WorldEconomyPlugin.PREFIX
											+ "Successfully registered supply chest with ID "
											+ WorldEconomyPlugin.registerSupplyChest(block.getLocation(), company.ID)
											+ "!");
									return true;
								} catch (SQLException e) {
									e.printStackTrace();
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
									return true;
								}
							} else {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "Please look at a chest.");
								return true;
							}
						} else {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
							return true;
						}
					} else if (args[1].equalsIgnoreCase("player")) {
						try {
							WorldEconomyPlugin.registerUserProfile((Player) sender);
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "Registered you!");
							return true;
						} catch (SQLException e) {
							e.printStackTrace();
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
							return true;
						}
					} else if (args[1].equalsIgnoreCase("product")) {
						if (!(sender instanceof Player)) {
							sender.sendMessage(
									WorldEconomyPlugin.PREFIX + "§4You have to be a player to register an item!");
							return true;
						}
						try {
							Player player = (Player) sender;

							PlayerInventory inv = player.getInventory();
							ItemStack itemStack = inv.getItemInMainHand();

							Company manifacturer = WorldEconomyPlugin.getCompany(args[2]);

							if (manifacturer == null) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The company \"" + args[2]
										+ "\" does not exist!");
								return true;
							}

							sender.sendMessage(WorldEconomyPlugin.PREFIX + "Registered product with ID "
									+ WorldEconomyPlugin.registerProduct(manifacturer.ID, args[3],
											Double.parseDouble(args[4]), itemStack)
									+ "!");
							return true;
						} catch (SQLException e) {
							e.printStackTrace();
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
							return true;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else if (args[0].equalsIgnoreCase("list")) {
				if (args.length == 1) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
					return true;
				} else {
					try {
						if (args[1].equalsIgnoreCase("banks")) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - name");

							ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks");
							while (r.next()) {
								sender.sendMessage(r.getLong("bankID") + " - " + r.getString("bankName"));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("companies")) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - name - type - employerID - bankingID");

							ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies");
							while (r.next()) {
								sender.sendMessage(r.getLong("companyID") + " - " + r.getString("companyName") + " - "
										+ r.getString("companyType") + " - " + r.getLong("companyEmployerID") + " - "
										+ r.getLong("companyBankingID"));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("user_profiles")) {
							sender.sendMessage(
									WorldEconomyPlugin.PREFIX + "UUID - name - employerID - employeeID - bankingID");

							ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles");
							while (r.next()) {
								sender.sendMessage(r.getString("playerUUID") + " - " + r.getString("username") + " - "
										+ r.getString("playerAsEmployerID") + " - " + r.getLong("employeeID") + " - "
										+ r.getLong("playerBankingID"));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("supply_chests")) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - companyID");

							ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM supply_chests");
							while (r.next()) {
								sender.sendMessage(r.getLong("chestID") + " - " + r.getString("chestOwnerCompanyID"));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("bank_accounts")) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX
									+ "ID - name - bankID - balance - customer - customerType");

							ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM bank_accounts");
							while (r.next()) {
								sender.sendMessage(r.getLong("bankAccountID") + " - " + r.getString("bankAccountName")
										+ " - " + r.getLong("bankID") + " - " + r.getLong("bankAccountBalance") + " - "
										+ r.getLong("customerBankingID") + " - " + r.getString("customerType"));
							}
							return true;
						} else if (args[1].equalsIgnoreCase("products")) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX
									+ "ID - manifacturerID - price - name - itemID - itemCount");
							ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM products");
							while (r.next()) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + r.getLong("productID") + " - "
										+ r.getLong("productManifacturerID") + " - " + r.getDouble("productPrice")
										+ " - " + r.getString("productName") + " - " + r.getString("productItemID")
										+ " - " + r.getInt("productItemAmount"));
							}
							return true;
						} else {
							return false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
						return true;
					}
				}
			} else if (args[0].equalsIgnoreCase("manage")) {
				if (args.length == 1) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
					return true;
				} else {
					if (args[1].equalsIgnoreCase("bank_account")) {
						if (args.length < 4) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
							return true;
						} else {
							try {
								BankAccount account = WorldEconomyPlugin.getBankAccount(
										WorldEconomyPlugin.getUserProfile((Player) sender).bankingID, args[2]);
								if (account == null) {
									sender.sendMessage(
											WorldEconomyPlugin.PREFIX + "§4That bank account does not exist!");
									return true;
								}

								if (args[3].equalsIgnoreCase("set")) {
									if (args.length >= 6) {
										if (args[4].equalsIgnoreCase("balance")) {
											WorldEconomyPlugin.runSQL("UPDATE bank_accounts SET bankAccountBalance = "
													+ args[5] + " WHERE bankAccountName = \"" + account.getName()
													+ "\" AND customerBankingID = " + account.getAccountHolderID());
											sender.sendMessage(
													WorldEconomyPlugin.PREFIX + "The balance of your account \""
															+ account.getName() + "\" was set to " + args[5] + "!");
											return true;
										} else {
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
											return false;
										}
									} else {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguements!");
										return true;
									}
								} else {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
									return false;
								}
							} catch (SQLException e) {
								e.printStackTrace();
								return true;
							}
						}
					} else {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
						return false;
					}
				}
			} else if (args[0].equalsIgnoreCase("SQL")) {
				if (args.length == 1) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
					return true;
				}
				if (args[1].equalsIgnoreCase("run")) {
					try {
						WorldEconomyPlugin.runSQL(args[2].replace('@', ' '));
						return true;
					} catch (SQLException e) {
						sender.sendMessage(e.getMessage());
						return true;
					}
				} else if (args[1].equalsIgnoreCase("clear")) {
					try {
						WorldEconomyPlugin.runSQL("DELETE FROM " + args[2]);
						return true;
					} catch (SQLException e) {
						sender.sendMessage(e.getMessage());
						return true;
					}
				} else {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
					return true;
				}
			} else {
				return false;
			}
		}
	}

}
