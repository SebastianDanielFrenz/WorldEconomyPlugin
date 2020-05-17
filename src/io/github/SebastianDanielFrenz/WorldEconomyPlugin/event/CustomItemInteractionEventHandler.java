package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItem;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.CustomItemRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.MeleeWeaponItemDetail;

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
				// bows and other range weapons will cause damage via other
				// entities such as arrows or snow balls.
				MeleeWeaponItemDetail detail = customItem.getDetail(MeleeWeaponItemDetail.class);
				if (detail != null) {
					detail.processEvent(event);
				} else {
					event.setDamage(0);
					player.sendMessage("You have dealt 0 damage. Use your fist or a weapon to deal damage.");
				}
			} else {
				event.setDamage(1);
				event.getDamager().sendMessage("You have dealt 1 damage using your hand.");
			}
		}
	}

}
