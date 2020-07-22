package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.drop.CustomBlockDropTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.terrain.OreGenerator;

public abstract class CustomOre extends CustomBlockType {

	public CustomOre(Plugin plugin, String ID, Material material, boolean vanilla, CustomBlockDropTable drop_table,
			Class<? extends CustomBlockData> blockDataType, int minY, int minCenterY, int maxCenterY, int maxY,
			double centerChance) {
		super(plugin, ID, material, vanilla, drop_table, blockDataType);

		this.minY = minY;
		this.maxY = maxY;
		this.maxCenterY = maxCenterY;
		this.minCenterY = minCenterY;
		this.centerChance = centerChance;
	}

	public final int minY;
	public final int minCenterY;
	public final int maxCenterY;
	public final int maxY;
	public final double centerChance;

	public double getChanceInterpolated(int y) {
		if (y < minY) {
			return 0;
		} else if (y < minCenterY) {
			return (0.000001 * centerChance) + (centerChance * 0.9999) / (minCenterY - minY) * (y - minY);
		} else if (y < maxCenterY) {
			return centerChance;
		} else if (y < maxY) {
			return (0.000001 * centerChance) + (centerChance * 0.9999) / (maxY - maxCenterY) * (y - maxCenterY);
		} else {
			return 0;
		}
	}

	public abstract OreGenerator getOreGenerator(World world, Chunk chunk);

}
