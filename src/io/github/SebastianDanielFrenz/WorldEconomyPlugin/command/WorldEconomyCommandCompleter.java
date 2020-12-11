package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Permissions;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public class WorldEconomyCommandCompleter implements TabCompleter {

	public WorldEconomyCommandCompleter() {
	}

	public static boolean hasPermission(CommandSender sender, String[] permissions, Age min_age) {
		return WorldEconomyCommandExecutor.hasPermission(sender, permissions, min_age, false);
	}

	public static boolean hasPermission(CommandSender sender, String[] permissions) {
		return WorldEconomyCommandExecutor.hasPermission(sender, permissions, false);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> out = new ArrayList<String>();

		if (args.length == 1) {
			if (hasPermission(sender, Permissions.RESTART_THREADS)) {
				out.add("restartthreads");
			}
			if (hasPermission(sender, Permissions.REGISTER_BANK_ACCOUNT_CMD)
					|| hasPermission(sender, Permissions.REGISTER_BANK_CMD)
					|| hasPermission(sender, Permissions.REGISTER_COMPANY_CMD)
					|| hasPermission(sender, Permissions.REGISTER_PRODUCT_CMD)
					|| hasPermission(sender, Permissions.REGISTER_SUPPLY_CHEST_CMD)) {
				out.add("register");
			}
			if (hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_BALANCE)
					|| hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_CREDIT_TAKE_CMD)
					|| hasPermission(sender, Permissions.MANAGE_BANK_ACCOUNT_NAME_CMD)
					|| hasPermission(sender, Permissions.MANAGE_COMPANY_BANK_ACCOUNTS_REGISTER_CMD)
					|| hasPermission(sender, Permissions.MANAGE_COMPANY_EMPLOY)
					|| hasPermission(sender, Permissions.MANAGE_COMPANY_MAIL_READ)
					|| hasPermission(sender, Permissions.MANAGE_COMPANY_MAIL_REMOVE)) {
				out.add("manage");
			}
			if (hasPermission(sender, Permissions.MAIL_READ) || hasPermission(sender, Permissions.MAIL_REMOVE)) {
				out.add("mail");
			}
			if (hasPermission(sender, Permissions.ITEM_GIVE_CMD)) {
				out.add("give");
			}
			if (hasPermission(sender, Permissions.ADMIN_ACCESS)) {
				out.add("reset");

			}
			if (hasPermission(sender, Permissions.BLOCK_SET_CMD)) {
				out.add("setblock");
			}
			// /we mail - next cmd to implement
		} else if (args.length == 2) {

		}

		Collections.sort(out);

		return out;
	}

}
