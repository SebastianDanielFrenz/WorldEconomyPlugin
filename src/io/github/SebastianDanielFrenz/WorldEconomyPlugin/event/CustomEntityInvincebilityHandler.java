package io.github.SebastianDanielFrenz.WorldEconomyPlugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entities.EntityAI;

public class CustomEntityInvincebilityHandler implements Listener {

	@EventHandler
	public void onDamageEvent(EntityDamageEvent event) {
		if (event.getEntity() instanceof EntityAI) {
			event.setCancelled(true);
			System.out.println("prevented damage to AI " + event.getEntity() + "!");
		}
	}

}
