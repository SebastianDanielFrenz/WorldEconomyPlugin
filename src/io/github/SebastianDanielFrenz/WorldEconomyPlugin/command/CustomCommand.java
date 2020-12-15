package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public abstract class CustomCommand extends CustomCommandGroupContent {

	public CustomCommand(Plugin plugin, CustomCommandGroup parent, String command, String[] perms) {
		super(plugin, parent, command);
		this.perms = perms;
	}

	public CustomCommand(CustomCommandGroup parent, String command, String[] perms) {
		super(parent.plugin, parent, command);
		this.perms = perms;
	}

	public final String[] perms;

	public boolean hasPermission(CommandSender sender) {
		for (String perm : perms) {
			if (sender.hasPermission(perm)) {
				return true;
			}
		}
		return false;
	}

}
