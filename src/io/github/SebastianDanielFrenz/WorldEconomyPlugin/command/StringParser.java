package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command;

public class StringParser {

	public static String parseCommandArgumentString(String raw) {
		return raw.replace("\\\"", "\"").replace("\\\\", "\\");
	}

}
