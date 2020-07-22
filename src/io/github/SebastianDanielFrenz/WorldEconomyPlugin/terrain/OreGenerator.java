package io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain;

import org.bukkit.Chunk;
import org.bukkit.World;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomOre;

public abstract class OreGenerator {

	public OreGenerator(World world, Chunk chunk, CustomOre ore) {
		this.chunk = chunk;
		this.world = world;
		this.ore = ore;
	}

	protected World world;
	protected Chunk chunk;
	protected CustomOre ore;

	public abstract void populate();

}
