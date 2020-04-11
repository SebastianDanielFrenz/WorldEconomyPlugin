package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.metadata.MetadataValue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockAndesite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCampfire;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCoarseDirt;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockDiorite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockEgyptianCampfireStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockGranite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCutSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSand;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSandstoneTrigger;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSmoothSandstone;

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

	public static final CustomBlock SANDSTONE_TRIGGER = new BlockSandstoneTrigger();
	public static final CustomBlock CUT_SANDSTONE = new BlockCutSandstone();
	public static final CustomBlock CAMPFIRE = new BlockCampfire();
	public static final CustomBlock SMOOTH_SANDSTONE = new BlockSmoothSandstone();
	public static final CustomBlock OAK_LEAVES = new BlockOakLeaves();

	public static final CustomBlock EGYPTIAN_CAMPFIRE_STAGE1 = new BlockEgyptianCampfireStage1();

	public static void init() {
		register(COARSE_DIRT);
		register(SAND);
		register(GRANITE);
		register(DIORITE);
		register(ANDESITE);

		register(SANDSTONE);

		register(COPPER_ORE);

		register(SANDSTONE_TRIGGER);
		register(CUT_SANDSTONE);
		register(CAMPFIRE);
		register(SMOOTH_SANDSTONE);
		register(OAK_LEAVES);

		register(EGYPTIAN_CAMPFIRE_STAGE1);
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
