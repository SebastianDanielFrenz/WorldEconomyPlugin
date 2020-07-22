package io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomOre;

public class RarePatchOreGenerator extends PatchOreGenerator {

	public RarePatchOreGenerator(World world, Chunk chunk, CustomOre ore, double chance, int minVolume, int maxVolume,
			OrePatchType orePatchType) {
		super(world, chunk, ore, 1, 1, minVolume, maxVolume, orePatchType);

		this.chance = chance;
	}

	private double chance;

	@Override
	public void populate() {
		Random random = new Random(world.getSeed() + chunk.getX() + chunk.getZ() * 1875000l);
		if (random.nextDouble() < chance) {
			super.populate();
		}
	}

}
