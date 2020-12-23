package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Config;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.Version;

public abstract class WorldEconomyExtension extends JavaPlugin {

	private final Version processed_version;
	public final WorldEconomyExtensionManager manager;

	public WorldEconomyExtension() {
		processed_version = Version.parseVersion(getDescription().getVersion());
		WorldEconomyExtensionRegistry.register(this);
		getLogger().info("Bukkit is constructing the plugin...");
		manager = new WorldEconomyExtensionManager(this);
	}

	public abstract void constructionEvent();

	/**
	 * In case any of the returned dependencies are found, they will run their
	 * constructionEvent() before this extension's.
	 * 
	 * @return
	 */
	public abstract Class<? extends WorldEconomyExtension>[] getDependencies();

	public String getServerLanguage() {
		return Config.getServerLanguage();
	}

	public Version getVersion() {
		return processed_version;
	}

	public String getID() {
		return getName().toLowerCase();
	}

}