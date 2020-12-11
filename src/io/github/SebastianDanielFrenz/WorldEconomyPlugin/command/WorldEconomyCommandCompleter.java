package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.sql.SQLException;
import java.util.ArrayList;
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
			if (sender instanceof Player) {
				out.add("");
			}
			if (hasPermission(sender, Permissions.RESTART_THREADS)) {
				out.add("restartthreads");
			}
			if (hasPermission(sender, Permissions.REGISTER_BANK_ACCOUNT_CMD)) {
				out.add("register bank_account");
			}
			if (hasPermission(sender, Permissions.REGISTER_BANK_CMD)) {
				out.add("register bank");
			}
			if (hasPermission(sender, Permissions.REGISTER_COMPANY_CMD)) {
				out.add("register company");
			}
			if (hasPermission(sender, Permissions.REGISTER_PRODUCT_CMD)) {
				out.add("register product");
			}
			if (hasPermission(sender, Permissions.REGISTER_SUPPLY_CHEST_CMD)) {
				out.add("register supply_chest");
			}
			// /we manage - next cmd to implement
		} else if (args.length == 1) {

		}

		return out;
	}

}
