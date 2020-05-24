package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.entity.entityspawner;

import org.bukkit.World;

public abstract class EntitySpawner {

	protected World world;

	public EntitySpawner(World world) {
		this.world = world;
	}

	public abstract void run();

}
