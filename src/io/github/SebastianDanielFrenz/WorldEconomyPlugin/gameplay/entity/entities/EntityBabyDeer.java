
package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entities;

import org.bukkit.entity.Cow;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.CustomEntityTypeRegistry;
import net.minecraft.server.v1_12_R1.EntityCow;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.IRangedEntity;
import net.minecraft.server.v1_12_R1.World;

public class EntityBabyDeer extends EntityCow implements IRangedEntity {

	@SuppressWarnings("deprecation")
	public EntityBabyDeer(World world) {
		super(world);

		Cow craftVillager = (Cow) this.getBukkitEntity();

		craftVillager.setMaxHealth(10);
		craftVillager.setHealth(10);
		this.setCustomName(CustomEntityTypeRegistry.BABY_DEER.getCustomEntityName());
		this.setCustomNameVisible(true);
		this.setInvisible(false);

	}
	// this.setEquipment(EnumItemSlot.CHEST, new
	// ItemStack(Items.DIAMOND_CHESTPLATE));

	// @Override
	/*
	 * protected void initAttributes() { super.initAttributes();
	 * 
	 * if (wantedlevel == 1) {
	 * getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(1.0D);
	 * 
	 * // getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.
	 * 23000000417232513D);
	 * getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(3.0D); }
	 * else if (level == 2) {
	 * getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.24D);
	 * getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(3.0D); }
	 * else if (level == 3) {
	 * getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25D);
	 * getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(4.0D); }
	 * else if (level == 4) {
	 * getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.26D);
	 * getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(5.0D); }
	 * else if (level == 5) {
	 * getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.28D);
	 * getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(5.0D); }
	 * else if (level == 6) {
	 * getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3D);
	 * getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(6.0D); }
	 * 
	 * // getAttributeInstance(GenericAttributes.h).setValue(2.0D); //
	 * getAttributeMap().b(a).setValue(this.random.nextDouble() *
	 * 0.10000000149011612D); }
	 */

	// @Override
	// protected void r() {
	// this.goalSelector.a(0, new PathfinderGoalFloat(this));
	// this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this,
	// EntityZombie.class, 8.0F, 0.6D, 0.6D));
	// this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this,
	// EntityEvoker.class, 12.0F, 0.8D, 0.8D));
	// this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this,
	// EntityVindicator.class, 8.0F, 0.8D, 0.8D));
	// this.goalSelector.a(1, new PathfinderGoalAvoidTarget<>(this,
	// EntityVex.class, 8.0F, 0.6D, 0.6D));
	// this.goalSelector.a(1, new PathfinderGoalTradeWithPlayer(this));
	// this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
	// this.goalSelector.a(2, new PathfinderGoalMoveIndoors(this));
	// this.goalSelector.a(3, new PathfinderGoalRestrictOpenDoor(this));
	// this.goalSelector.a(4, new PathfinderGoalOpenDoor(this, true));
	// this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this,
	// 0.6D));
	// this.goalSelector.a(6, new PathfinderGoalMakeLove(this));
	// this.goalSelector.a(7, new PathfinderGoalTakeFlower(this));
	// this.goalSelector.a(9, new PathfinderGoalInteract(this, (Class)
	// EntityHuman.class, 3.0F, 1.0F));
	// this.goalSelector.a(9, new PathfinderGoalInteractVillagers(this));
	// this.goalSelector.a(9, new PathfinderGoalRandomStrollLand(this,
	// 0.6D));
	// this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)
	// EntityInsentient.class, 8.0F));

	// }

	public void a(EntityLiving entityliving, float f) {
		// entity shoots gun
		System.out.println("a() in EntityAI!");
	}

	/*
	 * this method most likely is run every tick.
	 */
	@Override
	public void p(boolean arg0) {
		System.out.println("p() call");
	}

}