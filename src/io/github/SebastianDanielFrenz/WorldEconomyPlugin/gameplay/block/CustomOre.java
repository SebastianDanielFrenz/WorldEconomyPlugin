package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import org.bukkit.Material;

public abstract class CustomOre extends CustomBlockType {

	public CustomOre(String ID, Material material, boolean vanilla, CustomBlockDropTable drop_table, Class<? extends CustomBlockData> blockDataType) {
		super(ID, material, vanilla, drop_table, blockDataType);
	}

	private double adjusted_spawn_rate;

	/**
	 * An adjusted spawn rate of 1 would mean 100% of the world's stone would be
	 * turned into the ore.
	 * 
	 * @return
	 */
	public double getAdjustedSpawnRate() {
		return adjusted_spawn_rate;
	}

	public void setAdjustedSpawnRate(double rate) {
		adjusted_spawn_rate = rate;
	}

	/**
	 * This method should return a weight based on other ore's weights. This is
	 * never directly used to determine ore spawning; it is only used to
	 * calculate the actual spawn rate while relating all other weights.<br>
	 * <br>
	 * For example:<br>
	 * Coal Ore -> 8<br>
	 * Iron Ore --> 1<br>
	 * <br>
	 * This would mean that iron ore spawns 1/8 as much as coal ore. Their total
	 * weight of 1+8=9 could for example mean 1% of stone. That would mean
	 * 0.01*1/9 for iron and 0.01*8/9 for coal.
	 * 
	 * @return
	 */
	public abstract double getRawSpawnRate();

	/**
	 * <b style="color:red">Please use <b>getAdjustedSpawnRate</b> to calculate
	 * the spawn rate, not getRawSpawnRate!</b>
	 * 
	 * @param height
	 * @return
	 */
	public abstract double getProcessedSpawnRate(int height);

	public abstract int getSpawnAmount(int height);

	public static double getChanceInterpolated(int minY, int minCenterY, int maxCenterY, int maxY, double centerChance, int y) {
		if (y < minY) {
			return 0;
		} else if (y < minCenterY) {
			return (-y + minCenterY) * centerChance / (minCenterY - minY);
		} else if (y < maxCenterY) {
			return centerChance;
		} else if (y < maxY) {
			return (maxY - y) * centerChance / (maxY - maxCenterY);
		} else {
			return 0;
		}
	}

}
