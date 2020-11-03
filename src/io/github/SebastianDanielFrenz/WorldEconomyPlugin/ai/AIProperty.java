package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

import org.bukkit.plugin.Plugin;

public abstract class AIProperty {

	private int index;
	private Plugin plugin;

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}
