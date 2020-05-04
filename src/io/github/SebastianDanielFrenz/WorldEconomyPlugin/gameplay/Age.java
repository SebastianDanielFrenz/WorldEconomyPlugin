package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

import org.bukkit.ChatColor;

public enum Age {

	EARLY_STONE_AGE(ChatColor.BLACK),
	MID_STONE_AGE(EARLY_STONE_AGE, ChatColor.DARK_GRAY),
	NEW_STONE_AGE(MID_STONE_AGE, ChatColor.GRAY),
	COPPER_AGE(NEW_STONE_AGE, ChatColor.DARK_PURPLE),
	IRON_AGE(COPPER_AGE, ChatColor.DARK_BLUE),
	EARLY_MIDDLE_AGES(IRON_AGE, ChatColor.BLUE),
	MIDDLE_AGES(EARLY_MIDDLE_AGES, ChatColor.AQUA),
	LATE_MIDDLE_AGES(MIDDLE_AGES, ChatColor.GREEN),

	UNDEFINED(Integer.MAX_VALUE, ChatColor.MAGIC);

	public final int index;
	public final ChatColor color;

	private Age(Age prev, ChatColor color) {
		index = prev.index + 1;
		this.color = color;
	}

	private Age(ChatColor color) {
		index = 0;
		this.color = color;
	}

	private Age(int stage, ChatColor color) {
		this.index = stage;
		this.color = color;
	}

}
