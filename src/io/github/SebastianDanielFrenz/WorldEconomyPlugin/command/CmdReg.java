package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.api.WorldEconomyExtensionManager;

public class CmdReg {

	public static final CustomCommandGroup WE = new CustomCommandGroup(WorldEconomyPlugin.plugin, null, "we");
	public static final CustomCommandGroup WE__MANAGE = new CustomCommandGroup(WE, "manage");
	public static final CustomCommand WE__MANAGE__TEST = new CustomCommand(WE__MANAGE, "test", new String[] {}) {

		@Override
		public boolean run(CommandSender sender, Command cmd, String label, String[] args) {
			sender.sendMessage("hello world!");
			return true;
		}
	};

	public static final CustomCommandGroupContent[] all = new CustomCommandGroupContent[] { WE, WE__MANAGE,
			WE__MANAGE__TEST };

	public static void init() {
		for (CustomCommandGroupContent content : all) {
			if (content instanceof CustomCommand) {
				WorldEconomyExtensionManager.registerCommand((CustomCommand) content);
			} else {
				if (content.parent == null) {
					WorldEconomyExtensionManager.registerCommandRoot((CustomCommandGroup) content);
				} else {
					WorldEconomyExtensionManager.registerCommand((CustomCommandGroup) content);
				}
			}
		}
	}

}
