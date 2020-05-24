package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entities.EntityTypeBabyDeer;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.World;

public class CustomEntityTypeRegistry {

	private static List<CustomEntityType> entities = new ArrayList<CustomEntityType>();

	public static void register(CustomEntityType entity) {
		if (entity instanceof LivingEntity) {
			// integrity check
			for (CustomEntityType entity2 : entities) {
				if (entity2.getClass() == entity.getClass()) {
					WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
							"Registering already registered custom entity type with class "
									+ entity.getClass().getCanonicalName() + "!");
					return;
				}
				if (entity2.getEntityID().equals(entity.getEntityID())) {
					WorldEconomyPlugin.plugin.getLogger().log(Level.SEVERE,
							"Registering already registered custom entity with ID " + entity.getEntityID() + "!");
					return;
				}
			}
			// addition
			entities.add(entity);
		} else {
			throw new RuntimeException("Tried to register CustomEntity " + entity.getClass().getCanonicalName()
					+ ", but it is not a LivingEntity!");
		}
	}

	public static CustomEntityType getCustomEntity(String ID) {
		for (CustomEntityType entity : entities) {
			if (entity.getEntityID().equals(ID)) {
				return entity;
			}
		}
		return null;
	}

	public static CustomEntityType getCustomEntityByName(String name) {
		for (CustomEntityType entity : entities) {
			if (entity.getCustomEntityName().equals(name)) {
				return entity;
			}
		}
		return null;
	}

	public static void spawn(String ID, Location location) {
		spawn(getCustomEntity(ID), location);
	}

	public static void spawn(CustomEntityType type, Location location) {
		World nmsworld = ((CraftWorld) location.getWorld()).getHandle();
		Entity entity;
		try {
			Constructor<? extends Entity> constructor = type.getCustomEntityClass().getConstructor(World.class);
			try {
				entity = constructor.newInstance(nmsworld);

				entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(),
						location.getPitch());

				nmsworld.addEntity(entity, SpawnReason.CUSTOM);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new RuntimeException("error occured while constructing custom entity of class "
						+ type.getCustomEntityClass().getCanonicalName() + "!");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("missing or invisible constructur "
					+ type.getCustomEntityClass().getCanonicalName() + "(" + World.class.getCanonicalName() + ")!");
		}
	}

	public static final CustomEntityType BABY_DEER = new EntityTypeBabyDeer();

	public static void init() {
		register(BABY_DEER);
	}

}
