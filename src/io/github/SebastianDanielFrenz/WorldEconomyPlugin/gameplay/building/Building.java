package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;

public abstract class Building {

	public abstract void place(Location center, CustomBlockType pathway) throws Exception;
	
	public abstract int getSize();

	public static final int SMALL = 9;
	public static final int MEDIUM = 20;
	public static final int LARGE = 42;
	public static final int VERY_LARGE = 86;
	public static final int EXTREMLY_LARGE = 174;
	public static final int HUGE = 350;

}
