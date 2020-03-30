package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import java.util.ArrayList;
import java.util.List;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockAndesite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCoarseDirt;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockDiorite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockGranite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSand;

public class CustomBlockRegistry {

	private static List<CustomBlock> blocks = new ArrayList<CustomBlock>();

	public static void register(CustomBlock block) {
		blocks.add(block);
	}

	public static final CustomBlock coarse_dirt = new BlockCoarseDirt();
	public static final CustomBlock sand = new BlockSand();
	public static final CustomBlock granite = new BlockGranite();
	public static final CustomBlock diorite = new BlockDiorite();
	public static final CustomBlock andesite = new BlockAndesite();

	public static final CustomBlock copper_ore = new BlockCopperOre();

	public static void init() {
		register(new BlockCoarseDirt());
		register(new BlockSand());
		register(new BlockGranite());
		register(new BlockDiorite());
		register(new BlockAndesite());

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

}
