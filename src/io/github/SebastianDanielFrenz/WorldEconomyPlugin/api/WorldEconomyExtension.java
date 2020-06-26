package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class WorldEconomyExtension extends JavaPlugin {

	public WorldEconomyExtension() {
		WorldEconomyExtensionRegistry.register(this);
		getLogger().info("Bukkit is constructing the plugin...");
	}

	public abstract void constructionEvent();

}