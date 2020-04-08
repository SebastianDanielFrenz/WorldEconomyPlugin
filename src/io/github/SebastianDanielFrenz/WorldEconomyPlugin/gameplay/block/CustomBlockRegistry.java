package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.metadata.MetadataValue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockAndesite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCoarseDirt;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockDiorite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockGranite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSand;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSandstone;

public class CustomBlockRegistry {

	private static List<CustomBlock> blocks = new ArrayList<CustomBlock>();

	public static void register(CustomBlock block) {
		blocks.add(block);
	}

	public static final CustomBlock COARSE_DIRT = new BlockCoarseDirt();
	public static final CustomBlock SAND = new BlockSand();
	public static final CustomBlock GRANITE = new BlockGranite();
	public static final CustomBlock DIORITE = new BlockDiorite();
	public static final CustomBlock ANDESITE = new BlockAndesite();

	public static final CustomBlock SANDSTONE = new BlockSandstone();

	public static final CustomBlock COPPER_ORE = new BlockCopperOre();

	public static void init() {
		register(new BlockCoarseDirt());
		register(new BlockSand());
		register(new BlockGranite());
		register(new BlockDiorite());
		register(new BlockAndesite());

		register(new BlockSandstone());

		register(new BlockCopperOre());
	}

	public static List<CustomBlock> getContents() {
		return blocks;
	}

	public static CustomBlock getBlock(String ID) {
		for (CustomBlock block : blocks) {
			if (block.ID.equalsIgnoreCase(ID)) {
				return block;
			}
		}
		return null;
	}

	public static CustomBlock getBlock(Block block) {
		List<MetadataValue> metadata_values = block.getMetadata("customBlockType");

		CustomBlock customBlock;

		if (metadata_values.size() == 0) {
			customBlock = CustomBlock.getVanillaBlock(block);
		} else {
			customBlock = ((CustomBlockMetadataValue) metadata_values.get(0)).getBlock();
		}

		return customBlock;
	}

	public static CustomBlock getBlock(Location location) {
		return getBlock(location.getBlock());
	}

}
