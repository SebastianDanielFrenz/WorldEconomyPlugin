package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

import java.util.ArrayList;
import java.util.List;

public class AIPropertyRegistry {

	private static List<AIProperty> properties = new ArrayList<AIProperty>();

	public static void registerProperty(AIProperty property) {
		properties.add(property);
	}

	public static List<AIProperty> getProperties() {
		return properties;
	}

}
