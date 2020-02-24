package io.github.SebastianDanielFrenz.WorldEconomyPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	public static boolean contains(List<String> list, String str) {
		for (String str2 : list) {
			if (str2.equals(str)) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(Set<String> list, String str) {
		for (String str2 : list) {
			if (str2.equals(str)) {
				return true;
			}
		}
		return false;
	}

}
