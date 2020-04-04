package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;

public class CustomItemInteractionEventHandler implements Listener {

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		CustomItem customItem = CustomItem.getItem(event.getItem());
		if (customItem != null) {
			customItem.onPlayerInteractEvent(event);
		}
	}

}
