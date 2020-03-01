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

	public static Map<String, String> getTagsAfter(Set<String> tags, String searched, String[] args) {
		Map<String, String> out = new HashMap<String, String>();
		boolean found = false;
		String[] split;

		for (String tag : tags) {
			if (tag.equals(searched)) {
				found = true;
			}
		}
		if (!found) {
			return null;
		}

		for (String tag : tags) {
			split = tag.split("[:]");
			for (String arg : args) {
				if (split[0].equals(searched + "_" + arg)) {
					out.put(arg, split[1]);
					// System.out.println("found " + arg + " = " + split[1]);
				}
			}
		}

		return out;
	}

	public static boolean in(int[] array, int value) {
		for (int obj : array) {
			if (obj == value) {
				return true;
			}
		}
		return false;
	}

	public static int indexOf(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
