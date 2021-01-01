package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Lang;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class WEPCmdRegExecutor implements CommandExecutor, TabCompleter {

	public static Map<String, CustomCommandGroup> root = new TreeMap<String, CustomCommandGroup>();

	public final String base_cmd;

	public WEPCmdRegExecutor(String base_cmd) {
		this.base_cmd = base_cmd;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		CustomCommandGroupContent content = root.get(base_cmd).getCommandProcessor(args);
		if (content == null) {
			sender.sendMessage(WorldEconomyPlugin.PREFIX + "Command not found!");
			return true;
		}
		if (content instanceof CustomCommand) {
			if (!((CustomCommand) content).hasAge(sender)) {
				sender.sendMessage(Lang.getErrorAccessingFuture(sender));
				return true;
			} else if (!((CustomCommand) content).hasRawPermission(sender)) {
				sender.sendMessage(Lang.getErrorInsufficientPermission(sender));
				for (String perm : ((CustomCommand) content).perms) {
					sender.sendMessage(WorldEconomyPlugin.PREFIX + " - WorldEconomy." + perm);
				}
			}
		}

		String[] _args = new String[] {};

		return content.run(sender, cmd, label, args);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> out = new ArrayList<String>(5);
		CustomCommandGroupContent content = root.get(base_cmd).getTabCompleteProcessor(args);
		if (content == null) {
			return out;
		} else {
			List<String> tmp = content.onTabComplete(sender, args);
			if (tmp != null) {
				out.addAll(tmp);
			}
		}
		return out;
	}

}
