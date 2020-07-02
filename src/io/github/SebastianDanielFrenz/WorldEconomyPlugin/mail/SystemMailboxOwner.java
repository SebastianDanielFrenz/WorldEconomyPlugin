package io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail;

import org.bukkit.ChatColor;

public class SystemMailboxOwner implements MailboxOwner {

	@Override
	public long getMailboxID() {
		return 0;
	}

	@Override
	public String getMailboxDisplayName() {
		return ChatColor.RED + "System";
	}

}
