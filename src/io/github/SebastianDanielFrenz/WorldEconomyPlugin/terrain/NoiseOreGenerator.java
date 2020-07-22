package io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain;

import java.sql.SQLException;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WEDB;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.error.CustomBlockDataCreationException;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomBlockTypeRegistry;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.CustomOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blockdata.EmptyCustomBlockData;

public class NoiseOreGenerator extends OreGenerator {

	public NoiseOreGenerator(World world, Chunk chunk, CustomOre ore, double noiseMapScaling) {
		super(world, chunk, ore);

		this.world = world;
		this.chunk = chunk;
		this.ore = ore;

		this.noiseMapScaling = noiseMapScaling;
	}

	private World world;
	private Chunk chunk;
	private CustomOre ore;

	private double noiseMapScaling;

	@Override
	public void populate() {
		// 1875000 is the number of chunks on each axis in 30,000,000 blocks.

		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(
				new Random(world.getSeed() + CustomBlockTypeRegistry.getOreContents().indexOf(ore)), 8);
		// generator.setScale(0.005D);
		generator.setScale(0.005D);

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				// the lower the spotValue, the more likely stuff will generate
				double spotValue = (generator.noise(chunk.getX() * 16 + x, chunk.getZ() * 16 + z, 0.5D, 0.5D)) * 0.5
						+ 0.5;
				for (int y = ore.minY; y < ore.maxY + 1; y++) {
					if (ore.getChanceInterpolated(y) >= spotValue) {
						// try {
						// WEDB.registerCustomBlock(
						// new Location(chunk.getWorld(), chunk.getX() * 16 + x,
						// y, chunk.getZ() * 16 + z),
						// ore, new EmptyCustomBlockData());

						// } catch (SQLException |
						// CustomBlockDataCreationException e) {
						// WorldEconomyPlugin.plugin.getLogger().severe("Failed
						// to place ore " + ore.ID + " at "
						// + (chunk.getX() * 16 + x) + "," + y + "," +
						// (chunk.getZ() * 16 + z) + "!");
						// }

						chunk.getBlock(x, y, z).setType(Material.AIR);
					}
				}
			}
		}
	}

}
