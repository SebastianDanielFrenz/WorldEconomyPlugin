package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entityspawner;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.CustomEntityType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.CustomEntityTypeRegistry;

public abstract class BackgroundEntitySpawner extends EntitySpawner {

	private Random random = new Random();

	protected CustomEntityType entityType;

	protected int min_distance;
	protected int max_distance;
	protected double maxPerChunk;

	public BackgroundEntitySpawner(World world, CustomEntityType entityType, int min_distance, int max_distance,
			double maxPerChunk) {
		super(world);
		this.min_distance = min_distance;
		this.max_distance = max_distance;
		this.maxPerChunk = maxPerChunk;
	}

	public abstract World[] getAllowedWorlds();

	@Override
	public void run() {
		for (World world : Bukkit.getWorlds()) {
			boolean contains = false;
			for (World world2 : getAllowedWorlds()) {
				if (world2 == world) {
					contains = true;
					break;
				}
			}

			if (contains) {
				Chunk[] chunks = world.getLoadedChunks();
				int count = 0;
				for (Chunk chunk : chunks) {
					for (Entity entity : chunk.getEntities()) {
						if (entityType.getCustomEntityName().equals(entity.getCustomName())) {
							count++;
						}
					}
				}

				double concentration = count / chunks.length;
				double chance = spawnChance(concentration);
				if (1 - random.nextDouble() <= chance) {
					// spawn
					Chunk chunk = chunks[random.nextInt(chunks.length)];
					int z = chunk.getZ() * 16 + random.nextInt(16) + 1;
					int x = chunk.getX() * 16 + random.nextInt(16) + 1;
					int y = world.getHighestBlockYAt(x, z) + 1;
					CustomEntityTypeRegistry.spawn(entityType, new Location(world, x, y, z));
				}
			}
		}
	}

	public abstract double spawnChance(double concentration);

}
