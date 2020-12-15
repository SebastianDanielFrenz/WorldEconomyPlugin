package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class CustomCommandGroup extends CustomCommandGroupContent {

	public CustomCommandGroup(CustomCommandGroup parent, String command) {
		super(parent.plugin, parent, command);
	}

	public CustomCommandGroup(Plugin plugin, CustomCommandGroup parent, String command) {
		super(plugin, parent, command);
	}

	public List<CustomCommandGroupContent> children = new ArrayList<CustomCommandGroupContent>();

	public CustomCommandGroupContent getChild(String[] args, int index) {
		if (index == args.length) {
			return this;
		}

		CustomCommandGroup group = null;
		CustomCommand cmd = null;
		for (CustomCommandGroupContent child : children) {
			if (child.command.equalsIgnoreCase(args[index])) {
				if (child instanceof CustomCommand) {
					if (args.length == index + 1) {
						return child;
					} else {
						cmd = (CustomCommand) child;
					}
				} else if (child instanceof CustomCommandGroup) {
					group = (CustomCommandGroup) child;
				}
			}
		}

		if (group == null) {
			return cmd;
		}

		CustomCommandGroupContent content = group.getChild(args, index + 1);
		if (content == null) {
			return cmd;
		}
		return content;

	}

	public CustomCommandGroupContent getCommandProcessor(String[] args) {
		return getChild(args, 1); // skip past the root
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
		String _command = "";
		for (int i = 0; i < args.length; i++) {
			_command += " " + args[i];
		}
		sender.sendMessage("Subcommands for" + _command);
		for (CustomCommandGroupContent child : children) {
			if (child instanceof CustomCommandGroup) {
				sender.sendMessage(_command + child.command + " (super command)");
			} else if (child instanceof CustomCommand) {
				sender.sendMessage(_command + child.command);
			}
		}
		return true;
	}

}
