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
		cfg.addDefault("performace.background_thread_count", 6);
		cfg.addDefault("performance.idle_wait_millis", 10);
		cfg.addDefault("performance.AI_count", 10);
		cfg.addDefault("performance.pending_task_limit", 1000);
		cfg.addDefault("debug.overload_warning", true);
		cfg.addDefault("debug.overload_warning.interval", 1);
		cfg.addDefault("debug.overload_warning.light", 0.01);
		cfg.addDefault("debug.overload_warning.middle", 0.1);
		cfg.addDefault("debug.overload_warning.hard", 1);
		cfg.addDefault("gameplay.heaven_time", 5.0);
		cfg.addDefault("gameplay.server_language", "en_us");
		cfg.addDefault("gameplay.year_real_time_hours", 7 * 24);
		cfg.addDefault("debug.performance_monitoring_period", 10);

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
		return cfg.getInt("DB.connection.port");
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

	public static int getBackGroundThreadCount() {
		return cfg.getInt("performace.background_thread_count");
	}

	public static long getIdleWaitMillis() {
		return (long) cfg.getLong("performance.idle_wait_millis");
	}

	public static int getAICount() {
		return (int) cfg.getLong("performance.AI_count");
	}

	public static boolean doOverloadWarnings() {
		return cfg.getBoolean("debug.overload_warning");
	}

	public static int getPendingTaskLimit() {
		return cfg.getInt("performance.pending_task_limit");
	}

	public static int getOverloadWarningInterval() {
		return cfg.getInt("debug.overload_warning_interval");
	}

	public static double getHeavenTime() {
		return cfg.getDouble("gameplay.heaven_time");
	}

	public static long getHeavenTimeMillis() {
		return (long) (getHeavenTime() * 1000);
	}

	public static int getPerformanceMonitoringPeriod() {
		return cfg.getInt("debug.performance_monitoring_period");
	}

	public static String getServerLanguage() {
		return cfg.getString("gameplay.server_language");
	}

	public static double getYearRealTimeHours() {
		return cfg.getDouble("gameplay.year_real_time_hours");
	}

}
