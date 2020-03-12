package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.items;

import org.bukkit.ChatColor;

public enum Tier {

	TIER1(ChatColor.GREEN), TIER2(ChatColor.YELLOW), TIER3(ChatColor.GOLD);

	private Tier(ChatColor color) {
		this.color = color;
	}

	public final ChatColor color;

	public String toString() {
		return color.toString();
	}

}
