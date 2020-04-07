package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.building;

import org.bukkit.Location;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;

public abstract class Building {

	public abstract Building getNextLevel();

	public abstract Age getNextLevelAge();

	public abstract String getPath();
	
	public void place(Location center) {
		
	}

}
