package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Age {

	EARLY_STONE_AGE(ChatColor.BLACK, Material.STONE, 0),
	MID_STONE_AGE(EARLY_STONE_AGE, ChatColor.DARK_GRAY, Material.STONE, 1),
	NEW_STONE_AGE(MID_STONE_AGE, ChatColor.GRAY, Material.COBBLESTONE, 0),
	COPPER_AGE(NEW_STONE_AGE, ChatColor.DARK_PURPLE, Material.BRICK, 0),
	IRON_AGE(COPPER_AGE, ChatColor.DARK_BLUE, Material.IRON_INGOT, 0),
	EARLY_MIDDLE_AGES(IRON_AGE, ChatColor.BLUE, Material.LOG, 0),
	MIDDLE_AGES(EARLY_MIDDLE_AGES, ChatColor.AQUA, Material.WOOD, 0),
	LATE_MIDDLE_AGES(MIDDLE_AGES, ChatColor.GREEN, Material.BOOK, 0),

	UNDEFINED(Integer.MAX_VALUE, ChatColor.MAGIC, Material.BARRIER, 0);

	public final int index;
	public final ChatColor color;
	public final Material representation;
	/**
	 * item damage of the representing item.
	 */
	public final int repr_dmg;

	private Age(Age prev, ChatColor color, Material representation, int repr_dmg) {
		index = prev.index + 1;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
	}

	private Age(ChatColor color, Material representation, int repr_dmg) {
		index = 0;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
	}

	private Age(int stage, ChatColor color, Material representation, int repr_dmg) {
		this.index = stage;
		this.color = color;
		this.representation = representation;
		this.repr_dmg = repr_dmg;
	}

}
