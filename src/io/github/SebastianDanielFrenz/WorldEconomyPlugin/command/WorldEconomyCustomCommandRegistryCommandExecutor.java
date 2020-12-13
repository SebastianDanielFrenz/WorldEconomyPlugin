package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WECP.CustomCommandGroup;

public class WorldEconomyCustomCommandRegistryCommandExecutor implements CommandExecutor {

	private static Map<String, CustomCommandGroup> root = new TreeMap<String, CustomCommandGroup>();

	public final String base_cmd;

	public WorldEconomyCustomCommandRegistryCommandExecutor(String base_cmd) {
		this.base_cmd = base_cmd;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ()
	}

}
