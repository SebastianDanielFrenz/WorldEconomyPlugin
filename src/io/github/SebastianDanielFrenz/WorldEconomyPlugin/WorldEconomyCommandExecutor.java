package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.credit.Credit;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.chatdialogs.SetblockChatDialog;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.contracting.Contract;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockData;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building.BuildingIO;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building.TownGenerator;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.CustomEntityTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemStack;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui.guis.MainMenu;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.Mail;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailSubsystem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.Task;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.multithreading.tasking.TaskProcessor;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.resources.ItemTransactionManager;

public class WorldEconomyCommandExecutor implements CommandExecutor {

	public static boolean hasPermission(CommandSender sender, String[] permissions, Age min_age, boolean show) {
		if (sender instanceof Player) {
			try {
				if (WEDB.getUserProfile((Player) sender).age.index < min_age.index) {
					if (show) {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You are trying to access the future. Good luck with that.");
					}
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured while checking your permissions.");
				return false;
			}
		}

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

	public static boolean hasPermission(CommandSender sender, String[] permissions, Age age) {
		return hasPermission(sender, permissions, age, true);
	}

	public static boolean hasPermission(CommandSender sender, String[] permissions) {
		return hasPermission(sender, permissions, true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4This is a GUI! Try again as a player.");
				return true;
			}

			new MainMenu().openInventory((Player) sender);

			return true;
		} else {
			if (args[0].equalsIgnoreCase("restartthreads")) {
				WorldEconomyPlugin.stopThreads();
				WorldEconomyPlugin.startThreads();
				return true;
			} else if (args[0].equalsIgnoreCase("register")) {
				if (args.length > 1) {
					if (args[1].equalsIgnoreCase("bank")) {
						if (args.length > 2) {
							if (hasPermission(sender, Permissions.REGISTER_BANK_CMD, Age.LATE_MIDDLE_AGES)) {
								try {
									WEDB.registerBank(args[2]);
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully registered the bank \"" + args[2] + "\"!");

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
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You have to be a player in order to create a bank account!");
							return true;
						}
						try {
							if (hasPermission(sender, Permissions.REGISTER_BANK_ACCOUNT_CMD, Age.LATE_MIDDLE_AGES)) {
								Bank bank = WEDB.getBank(args[2]);
								if (bank == null) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The bank \"" + args[2] + "\" does not exist!");
									return true;
								}
								WEDB.registerBankAccount(new BankAccount(0, (Player) sender, bank.ID, 0, args[3]));

								ItemStack creditCard = new ItemStack(Material.PAPER);
								ItemMeta itemMeta = creditCard.getItemMeta();

								List<String> lore = new ArrayList<String>();
								lore.add("Credit Card");
								lore.add(args[3]);
								itemMeta.setLore(lore);
								creditCard.setItemMeta(itemMeta);

								((Player) sender).getInventory().addItem(creditCard);

								sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully created the bank account!");
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
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You have to be a player in order to create and own a company!");
							return true;
						}
						if (args.length > 3) {
							try {
								if (hasPermission(sender, Permissions.REGISTER_COMPANY_CMD, Age.LATE_MIDDLE_AGES)) {
									String name = args[2];

									switch (args[3]) {
									case "corporation":
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully created the corporation with ID "
												+ WEDB.registerCorporation(name, (Player) sender) + "!");
										break;
									case "private":
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully created the private company with ID "
												+ WEDB.registerPrivateCompany(name, (Player) sender));
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
							if (hasPermission(sender, Permissions.REGISTER_SUPPLY_CHEST_CMD, Age.LATE_MIDDLE_AGES)) {
								Set<Material> searched_types = new TreeSet<Material>();
								searched_types.add(Material.CHEST);

								List<Block> blocks = ((Player) sender).getLineOfSight(searched_types, 5);
								if (blocks.size() > 0) {
									Block block = blocks.get(0);

									if (block.getType() == Material.CHEST) {
										try {
											Company company = WEDB.getCompany(args[2]);

											if (company == null) {
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The company \"" + args[2] + "\" does not exist!");
												return true;
											}

											sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully registered supply chest with ID "
													+ WEDB.registerSupplyChest(block.getLocation(), company.ID) + "!");
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
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "Please move closer.");
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
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You have to be a player to register an item!");
							return true;
						}
						try {
							if (hasPermission(sender, Permissions.REGISTER_PRODUCT_CMD, Age.LATE_MIDDLE_AGES)) {
								Player player = (Player) sender;

								PlayerInventory inv = player.getInventory();
								ItemStack itemStack = inv.getItemInMainHand();

								Company manifacturer = WEDB.getCompany(args[2]);

								if (manifacturer == null) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The company \"" + args[2] + "\" does not exist!");
									return true;
								}

								sender.sendMessage(WorldEconomyPlugin.PREFIX + "Registered product with ID "
										+ WEDB.registerProduct(manifacturer.ID, args[3], Double.parseDouble(args[4]), itemStack) + "!");
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
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - name - capital");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM banks");
								while (r.next()) {
									sender.sendMessage(r.getLong("bankID") + " - " + r.getString("bankName") + " - " + r.getDouble("bankCapital"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("companies")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - name - type - employerID - bankingID");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM companies");
								while (r.next()) {
									sender.sendMessage(
											r.getLong("companyID") + " - " + r.getString("companyName") + " - " + r.getString("companyType") + " - "
													+ r.getLong("companyEmployerID") + " - " + r.getLong("companyBankingID"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("user_profiles")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "UUID - name - employerID - employeeID - bankingID");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM user_profiles");
								while (r.next()) {
									sender.sendMessage(
											r.getString("playerUUID") + " - " + r.getString("username") + " - " + r.getString("playerAsEmployerID")
													+ " - " + r.getLong("employeeID") + " - " + r.getLong("playerBankingID"));
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
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - name - bankID - balance - customer - customerType");

								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM bank_accounts");
								while (r.next()) {
									sender.sendMessage(r.getLong("bankAccountID") + " - " + r.getString("bankAccountName") + " - "
											+ r.getLong("bankID") + " - " + r.getLong("bankAccountBalance") + " - " + r.getLong("customerBankingID")
											+ " - " + r.getString("customerType"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("products")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - manifacturerID - price - name - itemID - itemCount");
								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM products");
								while (r.next()) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + r.getLong("productID") + " - " + r.getLong("productManifacturerID")
											+ " - " + r.getDouble("productPrice") + " - " + r.getString("productName") + " - "
											+ r.getString("productItemID") + " - " + r.getInt("productItemAmount"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("chests")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - type - x - y - z - world");
								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM chests");
								while (r.next()) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + r.getLong("chestID") + " - " + r.getString("chestType") + " - "
											+ r.getInt("chestX") + " - " + r.getInt("chestY") + " - " + r.getInt("chestZ") + " - "
											+ r.getString("chestWorld"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("employee_matching")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - employee - employer - contractID");
								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM employee_matching");
								while (r.next()) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + r.getLong("employee_matchingID") + " - " + r.getLong("employeeID")
											+ " - " + r.getLong("employerID") + " - " + r.getLong("contractID"));
								}
								return true;
							} else if (args[1].equalsIgnoreCase("mails")) {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - sender - reciever - message");
								ResultSet r = WorldEconomyPlugin.runSQLquery("SELECT * FROM mails");
								while (r.next()) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + r.getLong("mailID") + " - " + r.getLong("senderMailboxID") + " - "
											+ r.getLong("mailboxID") + " - " + r.getLong("message"));
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
								BankAccount account = WEDB.getBankAccount(WEDB.getUserProfile((Player) sender).bankingID, args[2]);
								if (account == null) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4That bank account does not exist!");
									return true;
								}

								if (args[3].equalsIgnoreCase("set")) {
									if (args.length >= 6) {
										if (args[4].equalsIgnoreCase("balance")) {
											if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_BALANCE)) {
												WEDB.setBankAccountBalance(account, Double.parseDouble(args[5]));
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "The balance of your account \"" + account.getName()
														+ "\" was set to " + args[5] + "!");
											}
											return true;
										} else if (args[4].equalsIgnoreCase("name")) {
											if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_NAME_CMD)) {
												WEDB.setBankAccountName(account, args[5]);
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "Your bank account's name was changed from " + args[2]
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
								} else if (args[3].equalsIgnoreCase("credit")) {
									if (args.length == 4) {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
										return true;
									}

									if (args[4].equalsIgnoreCase("take")) {
										if (args.length >= 7) {
											if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_CREDIT_TAKE_CMD, Age.LATE_MIDDLE_AGES)) {
												if (sender instanceof Player) {
													Bank bank = WEDB.getBank(args[5]);
													try {
														double amount = Double.parseDouble(args[6]);
														Player player = (Player) sender;
														WorldEconomyProfile profile = WEDB.getUserProfile(player);

														WEDB.takeCredit(new Credit(0, profile.bankingID, bank.ID, amount, 1.0, 10 * 60,
																player.getStatistic(Statistic.PLAY_ONE_TICK), account.getID()), account);
														return true;
													} catch (NumberFormatException e) {
														sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid number!");
														return true;
													}
												} else {
													sender.sendMessage(WorldEconomyPlugin.PREFIX
															+ "The only banking entities that can run commands and take credits are players!");
													return true;
												}
											} else {
												return true;
											}
										} else {
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
											return true;
										}
									} else {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
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
								Company company = WEDB.getCompany(args[2]);
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

										if (hasPermission(sender, Permissions.MANAGE_COMPANY_BANK_ACCOUNTS_REGISTER_CMD, Age.LATE_MIDDLE_AGES)) {
											Bank bank = WEDB.getBank(args[5]);
											if (bank == null) {
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The bank \"" + args[2] + "\" does not exist!");
												return true;
											}
											WEDB.registerBankAccount(new BankAccount(0, company, bank.ID, 0, args[6]));
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "Registered company bank account \"" + args[5] + "\"!");
										}
										return true;
									} else {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
										return true;
									}
								} else if (args[3].equalsIgnoreCase("list")) {
									if (args.length == 4) {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
										return true;
									}

									if (args[4].equalsIgnoreCase("employees")) {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "Listing the employees of " + args[2] + ":");
										ResultSet r = WorldEconomyPlugin.runSQLquery(
												"SELECT employee_matching.employeeID, employees.employeeType, employee_matching.contractID FROM "
														+ "(employee_matching LEFT JOIN employees ON employees.employeeID = employee_matching.employeeID) WHERE employee_matching.employerID = "
														+ company.companyEmployerID);

										sender.sendMessage("employee details - contract details");

										ResultSet r_inner_employee;
										String line;
										while (r.next()) {
											line = "";

											if (r.getString("employeeType").equals("player")) {
												r_inner_employee = WorldEconomyPlugin.runSQLquery(
														"SELECT username FROM user_profiles WHERE employeeID = " + r.getLong("employeeID"));
												line = "player: " + r_inner_employee.getString("username");
											} else {
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid employee type!");
												continue;
											}

											line += " - ";
											Contract contract = WEDB.getContract(r.getLong("contractID"));
											line += contract.getType() + " - ";

											if (contract instanceof ContractEmploymentDefault) {
												line += "salary: " + String.valueOf(((ContractEmploymentDefault) contract).salary);
												line += "; last salary: " + String.valueOf(((ContractEmploymentDefault) contract).last_salary);
											} else {
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid contract type!");
												continue;
											}
											sender.sendMessage(line);
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
									if (hasPermission(sender, Permissions.MANAGE_COMPANY_EMPLOY, Age.LATE_MIDDLE_AGES)) {
										String employeeType = args[4];
										String employeeName = args[5];
										long salary = Long.parseLong(args[6]);

										if (employeeType.equals("player")) {
											WorldEconomyProfile profile = WEDB.getUserProfile(Bukkit.getOfflinePlayer(employeeName));
											if (profile == null) {
												sender.sendMessage(
														WorldEconomyPlugin.PREFIX + "§4The player \"" + employeeName + "\" is not registered!");
												return true;
											}

											int minutes_played = ((Player) sender).getStatistic(Statistic.PLAY_ONE_TICK);

											ContractEmploymentDefault contract = new ContractEmploymentDefault(0, salary, minutes_played);
											long contractID = WEDB.registerContract(contract);

											WEDB.registerEmployment(company.companyEmployerID, profile.employeeID, contractID);

											return true;
										} else {
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid employee type \"" + employeeType + "\"!");
											return true;
										}
									} else {
										return true;
									}

								} else if (args[3].equalsIgnoreCase("mail")) {
									if (args.length == 4) {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
										return true;
									}
									if (args[4].equalsIgnoreCase("read")) {
										if (hasPermission(sender, Permissions.MANAGE_COMPANY_MAIL_READ, Age.LATE_MIDDLE_AGES)) {
											List<Mail> mails = WEDB.getMails(company, 10);
											sender.sendMessage(
													WorldEconomyPlugin.PREFIX + "Listing at most 10 of " + company.companyName + "'s mails:");
											for (int i = 0; i < 10 || i < mails.size(); i++) {
												sender.sendMessage("[" + mails.get(i).ID + "]: Mail from " + mails.get(i).senderMailboxID + ":");
												sender.sendMessage(mails.get(i).message);
											}
											return true;
										} else {
											return true;
										}
									} else if (args[4].equalsIgnoreCase("remove")) {
										if (args.length == 5) {
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
											return true;
										}
										if (hasPermission(sender, Permissions.MANAGE_COMPANY_MAIL_REMOVE, Age.LATE_MIDDLE_AGES)) {
											WEDB.removeMail(Long.parseLong(args[5]), company.mailboxID);
											return true;
										} else {
											return true;
										}
									} else {
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
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
			} else if (args[0].equalsIgnoreCase("mail")) {
				if (args.length == 1) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
					return true;
				}
				if (args[1].equalsIgnoreCase("read")) {
					if (hasPermission(sender, Permissions.MAIL_READ)) {
						MailSubsystem.showPlayerInbox((Player) sender);
						return true;
					} else {
						return true;
					}
				} else if (args[1].equalsIgnoreCase("remove")) {
					if (args.length == 2) {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
						return true;
					}
					if (hasPermission(sender, Permissions.MAIL_REMOVE)) {
						try {
							WEDB.removeMail(Long.parseLong(args[2]), WEDB.getMailboxID((Player) sender));
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "Removed mail with ID " + args[2] + "!");
							return true;
						} catch (NumberFormatException e) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "Invalid ID!");
							return true;
						} catch (SQLException e) {
							e.printStackTrace();
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "An internal error occured!");
							return true;
						}
					} else {
						return true;
					}
				} else {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("give")) {
				if (args.length == 1) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
					return true;
				} else {
					if (sender instanceof Player) {
						if (hasPermission(sender, Permissions.ITEM_GIVE_CMD)) {
							CustomItemStack stack;
							CustomItem item = CustomItemRegistry.getItem(args[1]);

							if (args.length == 2) {
								stack = new CustomItemStack(item, 1);
							} else if (args.length == 3) {
								try {
									int count = Integer.parseInt(args[2]);
									stack = new CustomItemStack(item, count);
								} catch (NumberFormatException e) {
									sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid amount!");
									return true;
								}
							} else {
								sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Item data in /we give not implemented yet!");
								return true;
							}

							ItemTransactionManager.give(((Player) sender).getInventory(), stack);
							return true;
						} else {
							return true;
						}
					} else {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You need to be a player in order to give yourself items!");
						return true;
					}
				}
			}

			else if (args[0].equalsIgnoreCase("reset")) {
				if (sender instanceof ConsoleCommandSender) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "Resetting...");
					Bukkit.broadcastMessage(WorldEconomyPlugin.PREFIX + "§4§kxxx §4§lWARNING! RESETTING THE ENTIRE ECONOMY! §kxxx");
					try {
						WorldEconomyPlugin.resetDB();
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "Done!");
						Bukkit.broadcastMessage(WorldEconomyPlugin.PREFIX
								+ "§4§lPlease rejoin the server in order to be registered for the economy! Automatic kick in 15 seconds!");
						for (Player player : WorldEconomyPlugin.plugin.getServer().getOnlinePlayers()) {
							Bukkit.getScheduler().runTaskLater(WorldEconomyPlugin.plugin, new Runnable() {
								@Override
								public void run() {
									player.kickPlayer(WorldEconomyPlugin.PREFIX + "Please relog in order for the economy to register you!");
								}
							}, 20 * 15);
						}
					} catch (SQLException | IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
					return true;
				} else {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4For security reasons, you need to use the server console to run this command!");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("setblock")) {
				if (sender instanceof ConsoleCommandSender) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You cannot run this command on the console!");
					return true;
				} else {
					if (hasPermission(sender, Permissions.BLOCK_SET_CMD)) {
						if (args.length == 1) {
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
							return true;
						} else {
							String ID = args[1];

							TaskProcessor.registerTask(new Task() {

								private boolean finished = false;

								@Override
								public void work() {
									CustomBlockType block = CustomBlockTypeRegistry.getBlock(ID);
									CustomBlockData data;

									if (args.length > 2) {
										Constructor<? extends CustomBlockData> dataConstructor;
										try {
											dataConstructor = block.blockDataType.getConstructor(String.class);
										} catch (NoSuchMethodException e) {
											e.printStackTrace();
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The block's (" + block.ID + ") blockdata type ("
													+ block.blockDataType.getCanonicalName() + ") does not have the constructor "
													+ block.blockDataType.getName() + "(String)!");
											finished = true;
											return;
										} catch (SecurityException e) {
											e.printStackTrace();
											sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4The block's (" + block.ID + ") blockdata type ("
													+ block.blockDataType.getCanonicalName() + ") does not have the constructor "
													+ block.blockDataType.getName()
													+ "(String) with modifier public. The constructor could not be accessed!");
											finished = true;
											return;
										}

										if (args[2].equals("#")) {
											if (!(sender instanceof Player)) {
												sender.sendMessage(WorldEconomyPlugin.PREFIX
														+ "§4This command causes a chat dialog to open. You can only use those as a player!");
												finished = true;
												return;
											}

											new SetblockChatDialog((Player) sender, block, dataConstructor);
											finished = true;
											return;
										} else {
											try {
												data = dataConstructor.newInstance(args[2]);
											} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
													| InvocationTargetException e) {
												sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occurred!");
												finished = true;
												return;
											}
										}
									} else {
										try {
											data = block.blockDataType.newInstance();
										} catch (InstantiationException | IllegalAccessException e) {
											e.printStackTrace();
											sender.sendMessage(WorldEconomyPlugin.PREFIX
													+ "§4An internal error occured while creating the blockdata with an empty constructor!");
											finished = true;
											return;
										}
									}

									try {
										if (sender instanceof Player) {
											CustomBlockType.placeBlock(((Player) sender).getLocation(), block, data);
											finished = true;
											return;
										} else {
											CustomBlockType.placeBlock(((BlockCommandSender) sender).getBlock().getLocation(), block, data);
											finished = true;
											return;
										}
									} catch (SQLException e) {
										e.printStackTrace();
										sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
										finished = true;
										return;
									}
								}

								@Override
								public boolean startOnShutdown() {
									return false;
								}

								@Override
								public void init() {
								}

								@Override
								public boolean hasFinished() {
									return finished;
								}

								@Override
								public int getPriority() {
									return 1000;
								}

								@Override
								public void discard() {
								}

								@Override
								public boolean continueOnShutdown() {
									return false;
								}
							});

							return true;

						}
					} else {
						return true;
					}
				}
			} else if (args[0].equalsIgnoreCase("schem")) {
				if (args.length == 1) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
					return true;
				} else if (args[1].equalsIgnoreCase("save")) {
					if (args.length < 9) {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
						return true;
					}
					if (!(sender instanceof Player)) {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You have to be a player to run this command!");
						return true;
					}
					if (hasPermission(sender, Permissions.SCHEM_SAVE)) {
						try {
							BuildingIO.save(args[2], ((Player) sender).getLocation(),
									new Location(((Player) sender).getWorld(), Integer.parseInt(args[3]), Integer.parseInt(args[4]),
											Integer.parseInt(args[5])),
									new Location(((Player) sender).getWorld(), Integer.parseInt(args[6]), Integer.parseInt(args[7]),
											Integer.parseInt(args[8])));
						} catch (NumberFormatException | IOException e) {
							e.printStackTrace();
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
						}
						return true;
					} else {
						return true;
					}
				} else if (args[1].equalsIgnoreCase("load")) {
					if (args.length < 3) {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Not enough arguments!");
						return true;
					}
					if (!(sender instanceof Player)) {
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4You have to be a player to run this command!");
						return true;
					}
					if (hasPermission(sender, Permissions.SCHEM_LOAD)) {
						try {
							BuildingIO.load(args[2], ((Player) sender).getLocation());
						} catch (InstantiationException | IllegalAccessException | IOException | SQLException e) {
							e.printStackTrace();
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error ocurred!");
						}
						return true;
					} else {
						return true;
					}
				} else {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4Invalid subcommand!");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("generate")) {
				try {
					TownGenerator.generate(((Player) sender).getLocation());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			} else if (args[0].equalsIgnoreCase("debug")) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					//
					// World nmsworld = ((CraftWorld)
					// player.getWorld()).getHandle();
					// EntityAI copEntity = new EntityAI(nmsworld);
					// Location spawnLocation = player.getLocation();
					//
					// copEntity.setLocation(spawnLocation.getX(),
					// spawnLocation.getY(), spawnLocation.getZ(),
					// spawnLocation.getYaw(), spawnLocation.getPitch());
					// nmsworld.addEntity(copEntity, SpawnReason.CUSTOM);

					CustomEntityTypeRegistry.spawn(CustomEntityTypeRegistry.BABY_DEER, player.getLocation());
				}
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(WorldEconomyPlugin.PREFIX + "Displaying help for /we commands:");
				if (hasPermission(sender, Permissions.REGISTER_BANK_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register bank <name>");
				}
				if (hasPermission(sender, Permissions.REGISTER_BANK_ACCOUNT_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register bank_account <bank name> <bank account name>");
				}
				if (hasPermission(sender, Permissions.REGISTER_COMPANY_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register company <name> <company type>");
				}
				if (hasPermission(sender, Permissions.REGISTER_SUPPLY_CHEST_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register supply_chest <owner company name>");
				}
				if (hasPermission(sender, Permissions.REGISTER_PRODUCT_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we register product <manifacturing company name> <product name> <MSRP>");
				}
				if (hasPermission(sender, Permissions.LIST, false)) {
					sender.sendMessage(
							WorldEconomyPlugin.PREFIX + "/we list <banks/companies/user_profiles/supply_chests/bank_accounts/products/chests>");
				}
				if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_BALANCE, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we manage bank_account <bank account name> set balance <balance>");
				}
				if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_NAME_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we manage bank_account <bank account name> set name <new bank account name>");
				}
				if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_CREDIT_TAKE_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we manage bank_account <bank account name> credit take <bank> <amount>");
				}
				if (hasPermission(sender, Permissions.MANAGE_COMPANY_BANK_ACCOUNTS_REGISTER_CMD, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(
							WorldEconomyPlugin.PREFIX + "/we manage company <company name> register bank_account <bank name> <bank account name>");
				}
				if (hasPermission(sender, Permissions.MANAGE_COMPANY_EMPLOY, Age.LATE_MIDDLE_AGES, false)) {
					sender.sendMessage(
							WorldEconomyPlugin.PREFIX + "/we manage company <company name> employ <employee type> <employee name> <salary>");
				}
				if (hasPermission(sender, Permissions.MAIL_READ, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we mail read");
				}
				if (hasPermission(sender, Permissions.MAIL_REMOVE, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we mail remove <ID>");
				}
				if (hasPermission(sender, Permissions.ITEM_GIVE_CMD, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we give <item> [<amount>] [<data>]");
				}
				if (hasPermission(sender, Permissions.BLOCK_SET_CMD, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we setblock <block> <# for dialog|data>");
				}
				if (hasPermission(sender, Permissions.SCHEM_SAVE, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we schem save <filename> <x> <y> <z> <x2> <y2> <z2>");
				}
				if (hasPermission(sender, Permissions.SCHEM_LOAD, false)) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + "/we schem load <filename>");
				}
				return true;
			} else {
				return false;
			}
		}
	}

}
