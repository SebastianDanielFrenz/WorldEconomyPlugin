package io.github.SebastianDanielFrenz.WorldEconomyPlugin.market;

import org.bukkit.World;

public abstract class SignData {

	public final long ID;
	public final int x;
	public final int y;
	public final int z;
	public final World world;
	public final String type;

	public SignData(long ID, int x, int y, int z, World world, String type) {
		this.ID = ID;
		this.type = type;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
