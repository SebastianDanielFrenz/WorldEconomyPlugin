package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

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
					}
					else if (args[1].equalsIgnoreCase("supply_chest")) {
						if (args.length > 2) {
							Block block = ((Player)sender).rayTraceBlocks(5).getHitBlock();
							if (block.getType() == Material.CHEST) {
								WorldEconomyPlugin.getCompany(args[2]);
								WorldEconomyPlugin.registerSupplyChest(block.getLocation(), companyID);
							}
							else {
								
							}
							sender.sendMessage(WorldEconomyPlugin.PREFIX+"Successfully registered supply chest with ID "+WorldEconomyPlugin.registerSupplyChest(location, companyID));
						}
					}
					else {
						return false;
					}
				} else {
					return false;
				}
			} else if (args[0].equalsIgnoreCase("run")) {
				String query = "";
				for (int i = 1; i < args.length - 1; i++) {
					query = args[i] + " ";
				}
				if (args.length >= 2) {
					query += args[args.length - 1];
				}

				try {
					WorldEconomyPlugin.runSQL(query);
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
