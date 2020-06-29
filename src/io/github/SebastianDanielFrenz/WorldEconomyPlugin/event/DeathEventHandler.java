package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;

public class DeathEventHandler implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		try {
			WEDB.getUserProfile(event.getEntity()).kill();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
