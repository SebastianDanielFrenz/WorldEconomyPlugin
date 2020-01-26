package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
						try {
							WorldEconomyPlugin.registerUserBankAccount((Player) sender,
									new BankAccount((Player) sender, Long.parseLong(args[2]), 0, args[3]));
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "Successfully created the bank account!");
							return true;
						} catch (SQLException e) {
							e.printStackTrace();
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
							return true;
						}
					} else if (args[1].equalsIgnoreCase("company")) {
						if (args.length > 3) {
							try {
								sender.sendMessage(
										WorldEconomyPlugin.PREFIX + "Successfully created the company with ID "
												+ WorldEconomyPlugin.registerCompany(args[2], args[3]) + "!");
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
							sender.sendMessage(WorldEconomyPlugin.PREFIX + "ID - name - type - employerID- bankingID");

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
						} else {
							return false;
						}
					} catch (SQLException e) {
						e.printStackTrace();
						sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4An internal error occured!");
						return true;
					}
				}
			} else if (args[0].equalsIgnoreCase("run")) {
				try {
					WorldEconomyPlugin.runSQL(args[1].replace('@', ' '));
				} catch (SQLException e) {
					sender.sendMessage(e.getMessage());
				}

				return true;
			} else {
				return false;
			}
		}
	}

}
