package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.HashMap;
import java.util.Map;

public class Utils {

	public static Map<String, String> getSepData(String raw) {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] pair;
		for (String split : raw.split("[;]")) {
			pair = split.split("[=]");
			map.put(pair[0], pair[1]);
		}
		return map;
	}

}
