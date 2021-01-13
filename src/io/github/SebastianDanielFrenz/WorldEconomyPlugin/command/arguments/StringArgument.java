package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.arguments;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.CommandArgument;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.StringParser;

public class StringArgument extends CommandArgument {
	
	public StringArgument(String raw) {
		super(raw);
	}
	
	private String value;

	@Override
	public void parse() throws Exception {
		if (raw.startsWith("\"")) {
			if (!raw.endsWith("\"")) {
				throw new Exception("Expected \" at the end of "+raw);
			}
			value = raw.substring(1,raw.length()-1);
			if (value.contains("\"")) {
				throw new Exception("Unexpected \" in string "+raw);
			}
			value = StringParser.parseCommandArgumentString(value);
		}
		else if (raw.startsWith("{")) {
			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(raw);
			String text = (String) root.get("text");
			if (text == null) {
				throw new Exception("JSON does not define \"text\": "+raw);
			}
			value = text;
		}
	}
	
	

}
