package io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.arguments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.command.CommandArgument;

public class PlayerSelectionArgument extends CommandArgument {

	public PlayerSelectionArgument(String raw) {
		super(raw);
	}

	private List<Player> value;

	@SuppressWarnings("deprecation")
	@Override
	public void parse() throws Exception {
		if (raw.startsWith("{")) {
			JSONParser parser = new JSONParser();
			Object rroot = parser.parse(raw);
			value = new ArrayList<Player>();
			if (rroot instanceof JSONArray) {
				for (Object player : ((JSONArray) rroot)) {
					value.add(Bukkit.getPlayer((String) player));
				}
			} else {
				JSONObject root = (JSONObject) rroot;
				JSONArray targets = (JSONArray) root.get("targets");
				for (Object player : ((JSONArray) targets)) {
					value.add(Bukkit.getPlayer((String) player));
				}
			}
		} else if (raw.startsWith("@")) {
			if (raw.equals("@a")) {
				value = new ArrayList<Player>();
				for (Player player : Bukkit.getOnlinePlayers()) {
					value.add(player);
				}
			} else if (raw.equals("@p")) {
				value = new ArrayList<Player>(1);
				// nearest player
			} else if (raw.equals("@r")) {
				value = new ArrayList<Player>(1);
				int random = new Random().nextInt(Bukkit.getOnlinePlayers().size());
				int i;
				Iterator<? extends Player> it = Bukkit.getOnlinePlayers().iterator();
				for (i = 0; i < random; i++) {
					it.next();
				}
				value.add(it.next());
			} else {
				throw new Exception("Invalid player selection \"" + raw + "\"!");
			}
		} else {
			Player player = Bukkit.getPlayer(raw);
			if (player == null) {
				if (Bukkit.getOfflinePlayer(raw) == null) {
					throw new Exception("The player \"" + raw + "\" does not exist!");
				}
				throw new Exception("The player " + raw + " is not online!");
			}
			value = new ArrayList<Player>(1);
			value.add(player);
		}
	}

}
