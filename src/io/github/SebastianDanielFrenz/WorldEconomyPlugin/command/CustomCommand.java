package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.sql.SQLException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public abstract class CustomCommand extends CustomCommandGroupContent {

	public CustomCommand(Plugin plugin, CustomCommandGroup parent, String command, String[] perms) {
		this(plugin, parent, command, perms, null);
	}

	public CustomCommand(Plugin plugin, CustomCommandGroup parent, String command, String[] perms, Age min_age) {
		super(plugin, parent, command);
		this.perms = perms;
		this.min_age = min_age;
	}

	public final String[] perms;
	public final Age min_age;

	public boolean hasRawPermission(CommandSender sender) {
		if (sender.isOp()) {
			return true;
		}
		if (sender.hasPermission(plugin.getName() + ".*")) {
			return true;
		}

		for (String perm : perms) {
			if (sender.hasPermission(perm)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasAge(CommandSender sender) {
		if (min_age != null && sender instanceof Player) {
			try {
				if (WEDB.getUserProfile(((Player) sender)).getAge().index >= min_age.index) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				sender.sendMessage(Lang.getErrorInternal(sender));
				return false;
			}
		} else {
			return true;
		}
	}

	public boolean hasPermission(CommandSender sender) {
		return hasRawPermission(sender) ? hasAge(sender) : false;
	}

}
