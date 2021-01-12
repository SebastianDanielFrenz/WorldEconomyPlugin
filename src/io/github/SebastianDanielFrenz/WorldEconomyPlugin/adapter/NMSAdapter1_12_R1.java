package io.github.SebastianDanielFrenz.WorldEconomyPlugin.adapter;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

import net.minecraft.server.v1_12_R1.ChunkSection;
import net.minecraft.server.v1_12_R1.IBlockData;

public class NMSAdapter1_12_R1 extends NMSAdapter {

	public void setBlockInNativeDataPalette(World world, int x, int y, int z, int blockId, byte data,
			boolean applyPhysics) {
		net.minecraft.server.v1_12_R1.World nmsWorld = ((CraftWorld) world).getHandle();
		net.minecraft.server.v1_12_R1.Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
		IBlockData ibd = net.minecraft.server.v1_12_R1.Block.getByCombinedId(blockId + (data << 12));

		ChunkSection cs = nmsChunk.getSections()[y >> 4];
		if (cs == nmsChunk.a()) {
			cs = new ChunkSection(y >> 4 << 4, true);
			nmsChunk.getSections()[y >> 4] = cs;
		}

		if (applyPhysics)
			cs.getBlocks().setBlock(x & 15, y & 15, z & 15, ibd);
		else
			cs.getBlocks().b(x & 15, y & 15, z & 15, ibd);
	}

	@Override
	public void placeBlock(Block block, int typeID, byte data, boolean applyPhysics) {
		setBlockInNativeDataPalette(block.getWorld(), block.getX(), block.getY(), block.getZ(), typeID, data,
				applyPhysics);
	}

	@Override
	public void placeBlock(World world, int x, int y, int z, int typeID, byte data, boolean applyPhysics) {
		setBlockInNativeDataPalette(world, x, y, z, typeID, data, applyPhysics);
	}

}
