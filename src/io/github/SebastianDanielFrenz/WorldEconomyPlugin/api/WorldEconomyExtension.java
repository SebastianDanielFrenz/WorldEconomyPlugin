package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class WorldEconomyExtension extends JavaPlugin {

	public WorldEconomyExtension() {
		WorldEconomyExtensionRegistry.register(this);
		getLogger().info("Bukkit is constructing the plugin...");
	}

	public abstract void constructionEvent();

	/**
	 * In case any of the returned dependencies are found, they will run their
	 * constructionEvent() before this extension's.
	 * 
	 * @return
	 */
	public abstract Class<? extends WorldEconomyExtension>[] getDependencies();

}