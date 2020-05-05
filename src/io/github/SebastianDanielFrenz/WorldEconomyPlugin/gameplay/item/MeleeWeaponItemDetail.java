package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public abstract class MeleeWeaponItemDetail extends WeaponItemDetail {

	public abstract void processEvent(EntityDamageByEntityEvent event);

}
