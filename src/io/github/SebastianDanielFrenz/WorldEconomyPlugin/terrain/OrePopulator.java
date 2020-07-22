package io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomOre;

public class OrePopulator extends BlockPopulator {

	@Override
	public void populate(World world, Random random, Chunk chunk) {
		for (CustomOre ore : CustomBlockTypeRegistry.getOreContents()) {
			OreGenerator generator = ore.getOreGenerator(world, chunk);
			generator.populate();
		}
	}

}
