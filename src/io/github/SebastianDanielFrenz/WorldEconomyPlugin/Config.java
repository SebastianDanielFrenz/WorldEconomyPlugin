package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private static FileConfiguration cfg;

	public static void setup() {
		cfg = WorldEconomyPlugin.plugin.getConfig();
		// This implies that WorldEconomyPlugin.plugin has already been
		// initialized; the setup() function should never be at the beginning of
		// onEnable()!
		cfg.addDefault("DB.connection.type", "sqlite");
		cfg.addDefault("DB.connection.host", "localhost");
		cfg.addDefault("DB.connection.port", 3306);
		cfg.addDefault("DB.connection.database", "WorldEconomyPlugin");
		cfg.addDefault("DB.connection.user", "root");
		cfg.addDefault("DB.connection.password", "password");

		cfg.options().copyDefaults(true);
		WorldEconomyPlugin.plugin.saveConfig();
	}

	public static Object getRawValue(String key) {
		return WorldEconomyPlugin.plugin.getConfig().get(key);
	}

	public static SQLConnectionType getSQLConnectionType() {
		return SQLConnectionType.valueOf((String) cfg.get("DB.connection.type"));
	}

	public static String getSQLHost() {
		return (String) cfg.get("DB.connection.host");
	}

	public static int getSQLPort() {
		return (int) cfg.get("DB.connection.port");
	}

	public static String getSQLDataBase() {
		return (String) cfg.get("DB.connection.database");
	}

	public static String getSQLUser() {
		return (String) cfg.get("DB.connection.user");
	}

	public static String getSQLPassword() {
		return (String) cfg.get("DB.connection.password");
	}

}
