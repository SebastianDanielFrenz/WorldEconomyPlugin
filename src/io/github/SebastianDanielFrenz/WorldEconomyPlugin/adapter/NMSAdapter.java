package io.github.SebastianDanielFrenz.WorldEconomyPlugin.adapter;

import org.bukkit.World;
import org.bukkit.block.Block;

public abstract class NMSAdapter {

	public abstract void placeBlock(Block block, int typeID, byte data, boolean applyPhysics);
	
	public abstract void placeBlock(World world, int x, int y, int z, int typeID, byte data, boolean applyPhysics);

}
