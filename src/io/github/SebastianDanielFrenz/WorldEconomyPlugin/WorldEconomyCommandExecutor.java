package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;

public class WorldEconomyCommandExecutor implements CommandExecutor {

	public static boolean hasPermission(CommandSender sender, String[] permissions, boolean show) {
		for (String perm : permissions) {
			if (sender.hasPermission("WorldEconomy." + perm)) {
				return true;
			}
		}
		if (sender.isOp() || sender.hasPermission("WorldEconomy.*")) {
			return true;
		}
		if (show) {
			sender.sendMessage(WorldEconomyPlugin.PREFIX
					+ "§4You do not have sufficient permission. In order to do this, you need at least one of the following permissions:");
			for (String perm : permissions) {
				sender.sendMessage(WorldEconomyPlugin.PREFIX + " - WorldEconomy." + perm);
			}
		}
		return false;
	}

	public static boolean hasPermission(CommandSender sender, String[] permissions) {
		return hasPermission(sender, permissions, true);
	}

	@SuppressWarnings("deprecation")
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
							if (hasPermission(sender, Permissions.REGISTER_BANK)) {
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
							if (hasPermission(sender, Permissions.REGISTER_BANK_ACCOUNT)) {
								Bank bank = WorldEconomyPlugin.getBank(args[2]);
								if (bank == null) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The bank \"" + args[2]
											+ "\" does not exist!");
									return true;
								}
								WorldEconomyPlugin
										.registerBankAccount(new BankAccount(0, (Player) sender, bank.ID, 0, args[3]));

								ItemStack creditCard = new ItemStack(Material.PAPER);
								ItemMeta itemMeta = creditCard.getItemMeta();

								List<String> lore = new ArrayList<String>();
								lore.add("Credit Card");
								lore.add(args[3]);
								itemMeta.setLore(lore);
								creditCard.setItemMeta(itemMeta);

								((Player) sender).getInventory().addItem(creditCard);

								sender.sendMessage(
										WorldEconomyPlugin.PREFIX + "Successfully created the bank account!");
								return true;
							} else {
								return true;
							}
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
								if (hasPermission(sender, Permissions.REGISTER_COMPANY)) {
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
								} else {
									return true;
								}
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
							if (hasPermission(sender, Permissions.REGISTER_SUPPLY_CHEST)) {
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
												+ "Successfully registered supply chest with ID " + WorldEconomyPlugin
														.registerSupplyChest(block.getLocation(), company.ID)
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
								return true;
							}
						} else {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
							return true;
						}
					} else if (args[1].equalsIgnoreCase("product")) {
						if (!(sender instanceof Player)) {
							sender.sendMessage(
									WorldEconomyPlugin.PREFIX + "§4You have to be a player to register an item!");
							return true;
						}
						try {
							if (hasPermission(sender, Permissions.REGISTER_PRODUCT)) {
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
							} else {
								return true;
							}
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
					if (hasPermission(sender, Permissions.LIST)) {
						try {
							if (args[1].equalsIgnoreCase("banks")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - name");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks");
								while (r.next()) {
									sender.sendMessage(r.getLong("bankID") + " - " + r.getString("bankName"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("companies")) {
								sender.sendMessage(
										WorldEconomyPlugin.PREFIX + "ID - name - type - employerID - bankingID");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies");
								while (r.next()) {
									sender.sendMessage(r.getLong("companyID") + " - " + r.getString("companyName")
											+ " - " + r.getString("companyType") + " - "
											+ r.getLong("companyEmployerID") + " - " + r.getLong("companyBankingID"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("user_profiles")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX
										+ "UUID - name - employerID - employeeID - bankingID");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles");
								while (r.next()) {
									sender.sendMessage(r.getString("playerUUID") + " - " + r.getString("username")
											+ " - " + r.getString("playerAsEmployerID") + " - "
											+ r.getLong("employeeID") + " - " + r.getLong("playerBankingID"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("supply_chests")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - companyID");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM supply_chests");
								while (r.next()) {
									sender.sendMessage(
											r.getLong("chestID") + " - " + r.getString("chestOwnerCompanyID"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("bank_accounts")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX
										+ "ID - name - bankID - balance - customer - customerType");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM bank_accounts");
								while (r.next()) {
									sender.sendMessage(r.getLong("bankAccountID") + " - "
											+ r.getString("bankAccountName") + " - " + r.getLong("bankID") + " - "
											+ r.getLong("bankAccountBalance") + " - " + r.getLong("customerBankingID")
											+ " - " + r.getString("customerType"));
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
							} else if (args[1].equalsIgnoreCase("chests")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - type - x - y - z - world");
								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM chests");
								while (r.next()) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + r.getLong("chestID") + " - "
											+ r.getString("chestType") + " - " + r.getInt("chestX") + " - "
											+ r.getInt("chestY") + " - " + r.getInt("chestZ") + " - "
											+ r.getString("chestWorld"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("employee_matching")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - employee - employer - contractID");
								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM employee_matching");
								while (r.next()) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + r.getLong("employee_matchingID")
											+ " - " + r.getLong("employeeID") + " - " + r.getLong("employerID") + " - "
											+ r.getLong("contractID"));
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
					return true;
				}
			} else if (args[0].equalsIgnoreCase("manage")) {
				if (args.length == 1) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
					return true;
				} else {
					if (args[1].equalsIgnoreCase("bank_account")) {
						if (args.length == 3) {
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
											if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_BALANCE)) {
												WorldEconomyPlugin.setBankAccountBalance(account,
														Double.parseDouble(args[5]));
												sender.sendMessage(
														WorldEconomyPlugin.PREFIX + "The balance of your account \""
																+ account.getName() + "\" was set to " + args[5] + "!");
											}
											return true;
										} else if (args[4].equalsIgnoreCase("name")) {
											if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_NAME)) {
												WorldEconomyPlugin.setBankAccountName(account, args[5]);
												sender.sendMessage(WorldEconomyPlugin.PREFIX
														+ "Your bank account's name was changed from " + args[2]
														+ " to " + args[5] + "!");
											}
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
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
								e.printStackTrace();
								return true;
							}
						}
					} else if (args[1].equalsIgnoreCase("company")) {
						if (args.length == 3) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
							return true;
						} else {
							try {
								Company company = WorldEconomyPlugin.getCompany(args[2]);
								if (company == null) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4That company does not exist!");
									return true;
								}

								if (args[3].equalsIgnoreCase("register")) {
									if (args.length == 4) {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
										return true;
									}

									if (args[4].equalsIgnoreCase("bank_account")) {
										if (args.length < 7) {
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
											return true;
										}

										if (hasPermission(sender, Permissions.MANAGE_COMPANY_BANK_ACCOUNTS_REGISTER)) {
											Bank bank = WorldEconomyPlugin.getBank(args[5]);
											if (bank == null) {
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The bank \"" + args[2]
														+ "\" does not exist!");
												return true;
											}
											WorldEconomyPlugin.registerBankAccount(
													new BankAccount(0, company, bank.ID, 0, args[6]));
											sender.sendMessage(WorldEconomyPlugin.PREFIX
													+ "Registered company bank account \"" + args[5] + "\"!");
										}
										return true;
									} else {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
										return true;
									}
								} else if (args[3].equalsIgnoreCase("employ")) {
									if (args.length < 7) {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
										return true;
									}
									if (hasPermission(sender, Permissions.MANAGE_COMPANY_EMPLOY)) {
										String employeeType = args[4];
										String employeeName = args[5];
										long contractID = Long.parseLong(args[6]);

										if (employeeType.equals("player")) {
											WorldEconomyProfile profile = WorldEconomyPlugin
													.getUserProfile(Bukkit.getOfflinePlayer(employeeName));
											if (profile == null) {
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The player \""
														+ employeeName + "\" is not registered!");
												return true;
											}
											WorldEconomyPlugin.registerEmployment(company.companyEmployerID,
													profile.employeeID, contractID);
											return true;
										} else {
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid employee type \""
													+ employeeType + "\"!");
											return true;
										}
									} else {
										return true;
									}

								} else {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
									return true;
								}
							} catch (SQLException e) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
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
			} else if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(WorldEconomyPlugin.PREFIX + "Displaying help for /we commands:");
				if (hasPermission(sender, Permissions.REGISTER_BANK, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register bank <name>");
				}
				if (hasPermission(sender, Permissions.REGISTER_BANK_ACCOUNT, false)) {
					sender.sendMessage(
							WorldEconomyPlugin.PREFIX + "/we register bank_account <bank name> <bank account name>");
				}
				if (hasPermission(sender, Permissions.REGISTER_COMPANY, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register company <name> <company type>");
				}
				if (hasPermission(sender, Permissions.REGISTER_SUPPLY_CHEST, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register supply_chest <owner company name>");
				}
				if (hasPermission(sender, Permissions.REGISTER_PRODUCT, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX
							+ "/we register product <manifacturing company name> <product name> <MSRP>");
				}
				if (hasPermission(sender, Permissions.LIST, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX
							+ "/we list <banks/companies/user_profiles/supply_chests/bank_accounts/products/chests>");
				}
				if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_BALANCE, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX
							+ "/we manage bank_account <bank account name> set balance <balance>");
				}
				if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_NAME, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX
							+ "/we manage bank_account <bank account name> set name <new bank account name>");
				}
				if (hasPermission(sender, Permissions.MANAGE_COMPANY_BANK_ACCOUNTS_REGISTER, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX
							+ "/we manage company <company name> register bank_account <bank name> <bank account name>");
				}
				if (hasPermission(sender, Permissions.MANAGE_COMPANY_EMPLOY, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX
							+ "/we manage company <company name> employ <employee type> <employee name>");
				}
				return true;
			} else {
				return false;
			}
		}
	}

}
