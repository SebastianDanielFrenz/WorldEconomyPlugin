package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WECP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CustomCommand extends CustomCommandGroupContent {

	public CustomCommand(CustomCommandGroup parent, String command, String[] perms) {
		super(parent, command);
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

	public abstract void run(CommandSender sender, Command cmd, String label, String[] args);

}
