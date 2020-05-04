package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.ItemDetailType;

public class CustomItemInteractionEventHandler implements Listener {

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		CustomItem customItem = CustomItem.getItem(event.getItem());
		if (customItem != null) {
			customItem.onPlayerInteractEvent(event);
		}
	}

	@EventHandler
	public void onPlayerHitEvent(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			ItemStack item = player.getInventory().getItem(player.getInventory().getHeldItemSlot());
			if (item != null) {
				CustomItem customItem = CustomItemRegistry.getItem(item);
				if (customItem.getDetail()) {
					
				}
			} else {
				event.setDamage(1);
			}
		}
	}

}
