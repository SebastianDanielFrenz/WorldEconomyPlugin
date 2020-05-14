package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity;

import org.bukkit.Location;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
//import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PathfinderGoal;

public class StalkerPathFinderGoal extends PathfinderGoal {

	private EntityCreature entitycreature;

	public StalkerPathFinderGoal(EntityCreature entitycreature) {
		this.entitycreature = entitycreature;
	}

	@Override
	public boolean a() {

		return true;
	}

	@Override
	public void c() {

		EntityLiving goal = null;
		double nearest = 30;

		for (Entity h : entitycreature.world.entityList) {
			// System.out.println("FE: " + h);
			if (h instanceof EntityPlayer) {
				Location fl = h.getBukkitEntity().getLocation();
				Location fs = entitycreature.getBukkitEntity().getLocation();
				// System.out.println("Set wplayer goal!");

				double thisDistance = fl.distance(fs);

				if (thisDistance < nearest) {
					goal = (EntityLiving) h;
					nearest = thisDistance;
				}
			}
		}
		if (goal == null || nearest > 30) {
			// Utils.debug("Could not find unwanted player within range!");
			// this.entitycreature.getBukkitEntity().remove();
			System.out.println("nothing to do!");
		} else {
			this.entitycreature.setGoalTarget(goal, TargetReason.CUSTOM, false);
			// Utils.debug("Found target within range!");
		}

	}

	@Override
	public void e() {
		// if (this.entitycreature.getGoalTarget() == null ||
		// !this.entitycreature.getGoalTarget().isAlive()
		// || (this.entitycreature.getGoalTarget() != null
		// && entitycreature.getGoalTarget() instanceof EntityPlayer &&
		// WantedManager
		// .getWantedLevel((Player)
		// entitycreature.getGoalTarget().getBukkitEntity()) == 0)) {
		if (entitycreature.getGoalTarget() == null || !this.entitycreature.getGoalTarget().isAlive()) {

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