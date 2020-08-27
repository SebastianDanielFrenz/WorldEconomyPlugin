package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gui;

import java.sql.SQLException;

import org.bukkit.entity.Player;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;

public class LiveGUI extends WEGUI {

	public LiveGUI(WEGUI parent, GUIItem[] items, String title, Player player) {
		super(parent, items, title, player);
	}

	private long last_updated = WorldEconomyPlugin.tick_counter;

	public void updateInventoryView() {
		update();
		last_updated = WorldEconomyPlugin.tick_counter;

		try {
			items = processRequirements(items, WEDB.getUserProfile(player).getAge());
		} catch (SQLException e) {
			e.printStackTrace();
			setErrorGUI();
		}

		items = initializeItems(items);
	}

	public void update() {
	}

	public long getLastUpdate() {
		return WorldEconomyPlugin.tick_counter - last_updated;
	}

}
