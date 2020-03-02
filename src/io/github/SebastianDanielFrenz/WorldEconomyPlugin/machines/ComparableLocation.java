package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines;

import org.bukkit.Location;
import org.bukkit.World;

public class ComparableLocation implements Comparable<ComparableLocation> {

	public ComparableLocation(World world, int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}

	private World world;
	private int x;
	private int y;
	private int z;

	@Override
	public int compareTo(ComparableLocation o) {
		return (world == o.world && o.x == x && o.y == y && o.z == z) ? 0 : 1;
	}

	public Location toLocation() {
		return new Location(world, x, y, z);
	}
}
