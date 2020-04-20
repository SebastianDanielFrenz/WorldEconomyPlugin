package io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class WorldEconomyBlockPopulator extends BlockPopulator {

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		chunk.getBlock(1, 200, 1).setType(Material.BEDROCK);
	}

}
