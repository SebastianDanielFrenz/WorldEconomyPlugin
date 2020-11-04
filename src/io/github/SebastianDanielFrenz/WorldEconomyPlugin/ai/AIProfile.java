package io.github.SebastianDanielFrenz.WorldEconomyPlugin.ai;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.Inventory;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.DataBaseRepresentation;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.PlayingEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.Age;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entities.EntityAI;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.research.ResearchEntity;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.mail.MailboxOwner;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.professions.EmployeeProfession;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.World;

@DataBaseRepresentation
public class AIProfile extends PlayingEntity implements MailboxOwner, ResearchEntity {

	public org.bukkit.entity.Entity entity;
	public String username;
	public long bankingID;
	public long aiAsEmployerID;
	public long aiID;

	public long mailboxID;

	public AIProperties properties;

	/**
	 * 1/4 --> 6 hours per day (24/4)
	 */
	// public double work_per_day;

	public AIProfile(long aiID, String username, long bankingID, long employeeID, long aiAsEmployerID, long mailboxID,
			Set<EmployeeProfession> professions, double health, double maxHealth, double saturation, double happyness,
			boolean religious, double religious_satisfaction, double endurance, double max_endurance, boolean in_heaven,
			long heaven_time_end_millis, Age age) {
		super(employeeID, professions, health, maxHealth, saturation, happyness, religious, religious_satisfaction,
				endurance, max_endurance, in_heaven, heaven_time_end_millis, age);

		this.aiID = aiID;
		this.username = username;
		this.bankingID = bankingID;
		this.aiAsEmployerID = aiAsEmployerID;

		this.mailboxID = mailboxID;
	}

	@Override
	public long getMailboxID() {
		return mailboxID;
	}

	@Override
	public String getMailboxDisplayName() {
		return ChatColor.AQUA + username;
	}

	@Override
	public String getResearchEntityType() {
		return "ai";
	}

	@Override
	public long getResearchSpecifiyEntityID() {
		return aiID;
	}

	@Override
	public void kill() {

	}

	public void spawn(Location location) {
		if (entity != null) {
			throw new RuntimeException("AI representation villager already summened!");
		}

		// World nmsworld = ((CraftWorld) location.getWorld()).getHandle();
		// Entity entity;
		//
		// entity = new EntityAI(nmsworld);
		//
		// entity.setLocation(location.getX(), location.getY(), location.getZ(),
		// location.getYaw(), location.getPitch());
		//
		// nmsworld.addEntity(entity, SpawnReason.CUSTOM);
		//
		// uuid = entity.getUniqueID();

		entity = location.getWorld().spawnEntity(location, EntityType.VILLAGER);
	}

	@Override
	public Inventory getInventory() {
		// return ((Villager) Bukkit.getEntity(uuid)).getInventory();
		return ((Villager) entity).getInventory();
	}
}
