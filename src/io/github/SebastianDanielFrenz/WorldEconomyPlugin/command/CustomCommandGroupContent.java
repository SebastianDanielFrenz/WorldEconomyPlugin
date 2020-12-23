package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public abstract class CustomCommandGroupContent {

	public CustomCommandGroupContent(Plugin plugin, CustomCommandGroup parent, String command) {
		this.parent = parent;
		this.command = command;
		this.plugin = plugin;
		if (parent != null) {
			parameter_offset = parent.parameter_offset + 1;
			parent.children.add(this);
		} else {
			parameter_offset = 1;
		}
	}

	public final Plugin plugin;
	public final CustomCommandGroup parent;
	public final String command;
	public final int parameter_offset;

	public abstract boolean run(CommandSender sender, Command cmd, String label, String[] args);

	/**
	 * Reads the full command string as args[] and outputs Tab suggestions.
	 * 
	 * @param sender
	 * @param args
	 * @return
	 */
	public abstract List<String> onTabComplete(CommandSender sender, String[] args);

	protected void addCommandToList(List<String> list) {
		if (parent != null) {
			parent.addCommandToList(list);
		}
		list.add(command);
	}

	public List<String> getCompleteCommand() {
		List<String> out = new ArrayList<String>(5);
		addCommandToList(out);
		return out;
	}

	public boolean isVisible(CommandSender sender) {
		return this instanceof CustomCommand ? ((CustomCommand) this).hasPermission(sender)
				: ((CustomCommandGroup) this).hasVisibleChild(sender);
	}

}
