package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay;

import org.bukkit.Location;
import org.bukkit.World;

public class ComparableLocation implements Comparable<ComparableLocation> {

	public ComparableLocation(World world, int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}

	public ComparableLocation(Location location) {
		x = location.getBlockX();
		y = location.getBlockY();
		z = location.getBlockZ();
		world = location.getWorld();
	}

	public final World world;
	public final int x;
	public final int y;
	public final int z;

	@Override
	public int compareTo(ComparableLocation o) {
		return (world == o.world && o.x == x && o.y == y && o.z == z) ? 0 : 1;
	}

	public Location toLocation() {
		return new Location(world, x, y, z);
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public String toString() {
		return world.getName() + "," + x + "," + y + "," + z;
	}

	public boolean equals(ComparableLocation o) {
		return world == o.world && o.x == x && o.y == y && o.z == z;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ComparableLocation) {
			return equals((ComparableLocation) obj);
		} else {
			return super.equals(obj);
		}
	}
}
