package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class WorldEconomyExtension extends JavaPlugin {

	public WorldEconomyExtension() {
		init();

		WorldEconomyExtensionRegistry.register(this);
	}

	public abstract void constructionEvent();

	public abstract void init();

}