package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class CustomCommandGroup extends CustomCommandGroupContent {

	public CustomCommandGroup(Plugin plugin, CustomCommandGroup parent, String command) {
		super(plugin, parent, command);
	}

	public List<CustomCommandGroupContent> children = new ArrayList<CustomCommandGroupContent>();

	public CustomCommandGroupContent getChild(String[] args, int index, int stop) {
		if (index == stop) {
			return this;
		}

		CustomCommandGroup group = null;
		CustomCommand cmd = null;
		for (CustomCommandGroupContent child : children) {
			if (child.command.equalsIgnoreCase(args[index])) {
				if (child instanceof CustomCommand) {
					if (stop == index + 1) {
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

		CustomCommandGroupContent content = group.getChild(args, index + 1, stop);
		if (content == null) {
			return cmd;
		}
		return content;

	}

	public CustomCommandGroupContent getChild(String[] args, int index) {
		return getChild(args, index, args.length);
	}

	/**
	 * a method reserved for root commands.
	 * 
	 * @param args
	 * @return
	 */
	public CustomCommandGroupContent getCommandProcessor(String[] args) {
		return getChild(args, 0);
	}

	public CustomCommandGroupContent getTabCompleteProcessor(String[] args) {
		return getChild(args, 0, args.length - 1);
	}

	public String getPrefix() {
		return parent == null ? "/" + command : parent.getPrefix() + " " + command;
	}

	@Override
	public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(WorldEconomyPlugin.PREFIX + "Subcommands for " + getPrefix() + ":");
		for (CustomCommandGroupContent child : children) {
			if (child instanceof CustomCommandGroup) {
				sender.sendMessage(WorldEconomyPlugin.PREFIX + getPrefix() + " " + child.command + " (super command)");
			} else if (child instanceof CustomCommand) {
				sender.sendMessage(WorldEconomyPlugin.PREFIX + getPrefix() + " " + child.command);
			}
		}
		return true;
	}

	private void insertRunnableSubcommands(List<CustomCommand> cmds) {
		for (CustomCommandGroupContent child : children) {
			if (child instanceof CustomCommandGroup) {
				((CustomCommandGroup) child).insertRunnableSubcommands(cmds);
			} else {
				cmds.add(((CustomCommand) child));
			}
		}
	}

	public List<CustomCommand> getRunnableSubcommands() {
		List<CustomCommand> out = new ArrayList<CustomCommand>();
		insertRunnableSubcommands(out);
		return out;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, String[] args) {
		List<String> out = new ArrayList<String>(children.size());
		for (CustomCommandGroupContent child : children) {
			if (child.isVisible(sender)) {
				out.add(child.command);
			}
		}
		return out;
	}

	public boolean hasVisibleChild(CommandSender sender) {
		for (CustomCommandGroupContent child : children) {
			if (child.isVisible(sender)) {
				return true;
			}
		}
		return false;
	}

}
