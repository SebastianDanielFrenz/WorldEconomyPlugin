package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.util.Map;
import java.util.TreeMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldEconomyCustomCommandRegistryCommandExecutor implements CommandExecutor {

	public static Map<String, CustomCommandGroup> root = new TreeMap<String, CustomCommandGroup>();

	public final String base_cmd;

	public WorldEconomyCustomCommandRegistryCommandExecutor(String base_cmd) {
		this.base_cmd = base_cmd;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		CustomCommandGroupContent content = root.get(base_cmd).getCommandProcessor(args);
		if (content == null) {
			sender.sendMessage("command not found!");
			return true;
		}
		return content.run(sender, cmd, label, args);
	}

}
