package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items.CustomItem;

public class ItemPickupIntegrationEventHandler implements Listener {

	@EventHandler
	public void onPlayerItemPickupEvent(PlayerItemConsumeEvent event) {
		ItemStack item = event.getItem();
		CustomItem.convertVanillaItemStack(item);
	}

}
