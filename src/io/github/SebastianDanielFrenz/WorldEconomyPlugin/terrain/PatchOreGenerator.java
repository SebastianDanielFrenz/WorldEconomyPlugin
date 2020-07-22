package io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain;

import java.sql.SQLException;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomOre;

public class PatchOreGenerator extends OreGenerator {

	public PatchOreGenerator(World world, Chunk chunk, CustomOre ore, int minTries, int maxTries, int minVolume,
			int maxVolume, OrePatchType orePatchType) {
		super(world, chunk, ore);

		this.minTries = minTries;
		this.maxTries = maxTries;
		this.maxVolume = maxVolume;
		this.minVolume = minVolume;
		this.orePatchType = orePatchType;
	}

	private int minTries;
	private int maxTries;
	private int minVolume;
	private int maxVolume;
	private OrePatchType orePatchType;

	@Override
	public void populate() {
		Random random = new Random(world.getSeed() + chunk.getX() + chunk.getZ() * 1875000l);
		int tries = random.nextInt(maxTries - minTries + 1) + minTries;
		int volume;
		int[] loc;

		if (orePatchType == OrePatchType.WORM) {
			for (int i = 0; i < tries; i++) {
				volume = random.nextInt(maxVolume - minVolume + 1) + minVolume;
				loc = new int[] { chunk.getX() * 16 + random.nextInt(16) + 1, random.nextInt(255),
						chunk.getZ() * 16 + random.nextInt(16) + 1 };

				try {
					CustomBlockType.placeBlock(world.getBlockAt(loc[0], loc[1], loc[2]), ore);
				} catch (InstantiationException | IllegalAccessException | SQLException e) {
					e.printStackTrace();
				}

				for (int oreI = 1; oreI < volume; oreI++) {
					switch (random.nextInt(6)) {
					case 0:
						loc = new int[] { loc[0] + 1, loc[1], loc[2] };
						break;
					case 1:
						loc = new int[] { loc[0] - 1, loc[1], loc[2] };
						break;
					case 2:
						loc = new int[] { loc[0], loc[1] + 1, loc[2] };
						break;
					case 3:
						loc = new int[] { loc[0], loc[1] - 1, loc[2] };
						break;
					case 4:
						loc = new int[] { loc[0], loc[1], loc[2] + 1 };
						break;
					case 5:
						loc = new int[] { loc[0], loc[1], loc[2] - 1 };
						break;
					}

					try {
						CustomBlockType.placeBlock(world.getBlockAt(loc[0], loc[1], loc[2]), ore);
					} catch (InstantiationException | IllegalAccessException | SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
