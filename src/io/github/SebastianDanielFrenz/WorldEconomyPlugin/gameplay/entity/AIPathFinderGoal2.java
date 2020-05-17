package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity;

import org.bukkit.Location;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityArmorStand;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.Navigation;
import net.minecraft.server.v1_12_R1.NavigationAbstract;
import net.minecraft.server.v1_12_R1.PathEntity;
import net.minecraft.server.v1_12_R1.PathfinderGoal;

public class AIPathFinderGoal2 extends PathfinderGoal {
	private EntityCreature entitycreature;

	public AIPathFinderGoal2(EntityCreature entitycreature) {
		this.entitycreature = entitycreature;
	}

	@Override
	public boolean a() {

		return true;
	}

	EntityArmorStand armorStand;

	@Override
	public void c() {

		// System.out.println("Path finder c()");

		if (armorStand != null) {
			armorStand.killEntity();
		}
		// armorStand = new EntityVillager(entitycreature.world);
		armorStand = new EntityArmorStand(entitycreature.world);
		armorStand.setInvisible(true);
		armorStand.setNoGravity(false);
		entitycreature.world.addEntity(armorStand);
		entitycreature.setGoalTarget(armorStand, TargetReason.CUSTOM, false);

	}

	/**
	 * This method is called every tick
	 */
	@Override
	public void e() {
		// if (this.entitycreature.getGoalTarget() == null ||
		// !this.entitycreature.getGoalTarget().isAlive()
		// || (this.entitycreature.getGoalTarget() != null
		// && entitycreature.getGoalTarget() instanceof EntityPlayer &&
		// WantedManager
		// .getWantedLevel((Player)
		// entitycreature.getGoalTarget().getBukkitEntity()) == 0)) {
		// System.out.println("path finder e()");

		if (entitycreature.getGoalTarget() == null || !this.entitycreature.getGoalTarget().isAlive()) {
			System.out.println("no traget; running c()!");
			c(); // this should be called when the target needs to be redefined.
		}

		/*
		 * if (this.entitycreature.getGoalTarget() == null) { c(); }
		 * 
		 * 
		 * else if(entitycreature.getGoalTarget() instanceof EntityPlayer &&
		 * WantedManager.getWantedLevel((Player)
		 * entitycreature.getGoalTarget().getBukkitEntity())== 0) { c(); }else {
		 * c(); }
		 */
	}

	@Override
	public void d() {
		// SplatCraft.debug("d call");

	}

	/*
	 * @Override public void b() { SplatCraft.debug("b call"); }
	 */
}