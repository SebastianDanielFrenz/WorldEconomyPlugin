package io.github.SebastianDanielFrenz.WorldEconomyPlugin.api;

import java.util.ArrayList;
import java.util.List;

public class WorldEconomyExtensionRegistry {
	
	private static List<WorldEconomyExtension> extensions = new ArrayList<WorldEconomyExtension>();
	
	public static void register(WorldEconomyExtension extension) {
		extensions.add(extension);
	}

	public static List<WorldEconomyExtension> getExtensions() {
		return extensions;
	}

}
