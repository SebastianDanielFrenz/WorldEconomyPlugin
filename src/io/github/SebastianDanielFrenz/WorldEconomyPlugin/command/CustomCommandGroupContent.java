package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public abstract class CustomCommandGroupContent {

	public CustomCommandGroupContent(Plugin plugin, CustomCommandGroup parent, String command) {
		this.parent = parent;
		this.command = command;
		this.plugin = plugin;
	}

	public final Plugin plugin;
	public final CustomCommandGroup parent;
	public final String command;

	public abstract boolean run(CommandSender sender, Command cmd, String label, String[] args);

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

}
