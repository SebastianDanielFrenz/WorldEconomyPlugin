package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SwordItemDetail extends MeleeWeaponItemDetail {

	private final double damage;

	public SwordItemDetail(double damage) {
		this.damage = damage;
	}

	@Override
	public void processEvent(EntityDamageByEntityEvent event) {
		// event.setDamage(damage);
		event.setDamage(1000);
	}

}
