package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.LivingEntity;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
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
	public void onEntityDamageEvent(EntityDamageEvent raw_event) {
		try {
			Bukkit.getLogger().info(raw_event.getEventName() + ": " + raw_event.getEntity().getType()
					+ " damaged (cause=" + raw_event.getCause().name() + ")");

			if (raw_event instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) raw_event;

				if (event.getDamager() instanceof Player) {
					ItemStack stack = ((Player) event.getDamager()).getInventory().getItemInMainHand();
					if (stack.getType() == Material.WOOD_AXE || stack.getType() == Material.WOOD_SPADE
							|| stack.getType() == Material.WOOD_PICKAXE || stack.getType() == Material.WOOD_HOE
							|| stack.getType() == Material.WOOD_SWORD || stack.getType() == Material.IRON_AXE
							|| stack.getType() == Material.IRON_SPADE || stack.getType() == Material.IRON_PICKAXE
							|| stack.getType() == Material.IRON_HOE || stack.getType() == Material.IRON_SWORD
							|| stack.getType() == Material.STONE_AXE || stack.getType() == Material.STONE_SPADE
							|| stack.getType() == Material.STONE_PICKAXE || stack.getType() == Material.STONE_HOE
							|| stack.getType() == Material.STONE_SWORD || stack.getType() == Material.DIAMOND_AXE
							|| stack.getType() == Material.DIAMOND_SPADE || stack.getType() == Material.DIAMOND_PICKAXE
							|| stack.getType() == Material.DIAMOND_HOE || stack.getType() == Material.DIAMOND_SWORD
							|| stack.getType() == Material.GOLD_AXE || stack.getType() == Material.GOLD_SPADE
							|| stack.getType() == Material.GOLD_PICKAXE || stack.getType() == Material.GOLD_HOE
							|| stack.getType() == Material.GOLD_SWORD) {
						stack.setDurability((short) 0);
					}
				}

				Bukkit.getLogger()
						.info("Entity " + event.getEntity().getType() + " damaged by " + event.getDamager().getType());

				if (event.getDamager() instanceof Player) {
					Player player = (Player) event.getDamager();
					ItemStack item = player.getInventory().getItem(player.getInventory().getHeldItemSlot());
					if (item != null) {
						CustomItem customItem = CustomItemRegistry.getItem(item);

						((EntityDamageByEntityEvent) raw_event).getDamager().sendMessage(String.valueOf(customItem));

						// bows and other range weapons will cause damage via
						// other
						// entities such as arrows or snow balls.
						MeleeWeaponItemDetail detail = customItem.getDetail(MeleeWeaponItemDetail.class);
						if (detail != null) {
							detail.processEvent(event);
							player.sendMessage("You have dealt " + event.getDamage() + " damage using "
									+ customItem.item_name + ".");
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

			// no matter why the entity dies...

			if (raw_event.getEntity() instanceof Player) {
				if (((Player) raw_event.getEntity()).getHealth() - raw_event.getDamage() <= 0) {
					raw_event.setCancelled(true);
					try {
						WEDB.getUserProfile((Player) raw_event.getEntity()).kill();
					} catch (SQLException e) {
						e.printStackTrace();

						raw_event.getEntity()
								.sendMessage("something went wrong; the gods do not allow you to enter heaven!");
					}
				}
			}
		} catch (Exception e) {
			raw_event.setCancelled(true);
		}
	}

}
