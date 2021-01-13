package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Permissions;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.Bank;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.banking.BankAccount;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public class CmdReg {

	public static final CustomCommandGroup WE = new CustomCommandGroup(WorldEconomyPlugin.plugin, null, "we");
	public static final CustomCommand WE__KILL = new CustomCommand(WorldEconomyPlugin.plugin, WE, "kill",
			Permissions.KILL) {
		@Override
		public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Lang.getErrorNotAPlayer(sender));
				return true;
			}
			try {
				WEDB.getUserProfile((Player) sender).kill();
			} catch (SQLException e) {
				e.printStackTrace();
				sender.sendMessage(Lang.getErrorInternal(sender));
			}
			return true;
		}

		@Override
		public List<String> onTabComplete(CommandSender sender, String[] args) {
			return null;
		}
	};
	public static final CustomCommand WE__RESTARTTHREADS = new CustomCommand(WorldEconomyPlugin.plugin, WE,
			"restartthreads", Permissions.RESTART_THREADS) {
		@Override
		public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
			WorldEconomyPlugin.stopThreads();
			WorldEconomyPlugin.startThreads();
			return true;
		}

		@Override
		public List<String> onTabComplete(CommandSender sender, String[] args) {
			return null;
		}
	};
	public static final CustomCommandGroup WE__REGISTER = new CustomCommandGroup(WorldEconomyPlugin.plugin, WE,
			"register");
	public static final CustomCommand WE__REGISTER__BANK = new CustomCommand(WorldEconomyPlugin.plugin, WE__REGISTER,
			"bank", Permissions.REGISTER_BANK_CMD, Age.LATE_MIDDLE_AGES) {
		@Override
		public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
			if (args.length > 2) {
				if (hasPermission(sender)) {
					try {
						WEDB.registerBank(args[0]);
						sender.sendMessage(Lang.getSuccess(sender, Lang.SUCCESS_REGISTER_BANK));
						return true;
					} catch (SQLException e) {
						e.printStackTrace();
						sender.sendMessage(Lang.getErrorInternal(sender));
						return true;
					}
				} else {
					return true;
				}
			} else {
				sender.sendMessage(WorldEconomyPlugin.PREFIX + "§4/we register bank <name>");
				return true;
			}
		}

		@Override
		public List<String> onTabComplete(CommandSender sender, String[] args) {
			return null;
		}
	};
	public static final CustomCommand WE__REGISTER__BANK_ACCOUNT = new CustomCommand(WorldEconomyPlugin.plugin,
			WE__REGISTER, "bank_account", Permissions.REGISTER_BANK_ACCOUNT_CMD, Age.LATE_MIDDLE_AGES) {

		@Override
		public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Lang.getErrorNotAPlayer(sender));
				return true;
			}
			try {
				Bank bank = WEDB.getBank(args[0]);
				if (bank == null) {
					sender.sendMessage(Lang.getBankDoesNotExist(sender, args[0]));
					return true;
				}
				WEDB.registerBankAccount(new BankAccount(0, (Player) sender, bank.ID, 0, args[1]));

				ItemStack creditCard = new ItemStack(Material.PAPER);
				ItemMeta itemMeta = creditCard.getItemMeta();

				List<String> lore = new ArrayList<String>();
				lore.add("Credit Card");
				lore.add(args[1]);
				itemMeta.setLore(lore);
				creditCard.setItemMeta(itemMeta);

				((Player) sender).getInventory().addItem(creditCard);

				sender.sendMessage(Lang.getRegisteredPlayerBankAccount(sender, args[1], bank.name));
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				sender.sendMessage(Lang.getErrorInternal(sender));
				return true;
			}
		}

		@Override
		public List<String> onTabComplete(CommandSender sender, String[] args) {
			String out = "";
			for (int i = 0; i < args.length; i++) {
				out += args[i];
				out += " ";
			}
			sender.sendMessage(out);
			return null;
		}
	};
	public static final CustomCommand WE__REGISTER__COMPANY = new CustomCommand(WorldEconomyPlugin.plugin, WE__REGISTER,
			"company", Permissions.REGISTER_COMPANY_CMD, Age.LATE_MIDDLE_AGES) {

		@Override
		public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Lang.getError(sender, Lang.getErrorNotAPlayer(sender)));
				return true;
			}
			if (args.length > 3) {
				try {
					String name = args[0];

					switch (args[1]) {
					case "corporation":
						sender.sendMessage(Lang.getRegisteredCorporation(sender, args[2],
								WEDB.registerCorporation(name, (Player) sender)));
						break;
					case "private":
						sender.sendMessage(Lang.getRegisteredPrivateCompany(sender, args[2],
								WEDB.registerPrivateCompany(name, (Player) sender)));
						break;
					default:
						sender.sendMessage(Lang.getInvalidCompanyType(sender, args[3]));
					}
					return true;

				} catch (SQLException e) {
					e.printStackTrace();
					sender.sendMessage(Lang.getErrorInternal(sender));
					return true;
				}
			} else {
				return false;
			}
		}

		@Override
		public List<String> onTabComplete(CommandSender sender, String[] args) {
			return null;
		}
	};

}
