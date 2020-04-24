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
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCutSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSand;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSandstoneTrigger;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSmoothSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockTreeOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires.BlockEgyptianCampfireStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires.BlockStoneAgeCampfire;

public class CustomBlockTypeRegistry {

	private static List<CustomBlockType> blocks = new ArrayList<CustomBlockType>();

	public static void register(CustomBlockType block) {
		blocks.add(block);
	}

	public static final CustomBlockType COARSE_DIRT = new BlockCoarseDirt();
	public static final CustomBlockType SAND = new BlockSand();
	public static final CustomBlockType GRANITE = new BlockGranite();
	public static final CustomBlockType DIORITE = new BlockDiorite();
	public static final CustomBlockType ANDESITE = new BlockAndesite();

	public static final CustomBlockType SANDSTONE = new BlockSandstone();

	public static final CustomBlockType COPPER_ORE = new BlockCopperOre();

	public static final CustomBlockType SANDSTONE_TRIGGER = new BlockSandstoneTrigger();
	public static final CustomBlockType CUT_SANDSTONE = new BlockCutSandstone();
	public static final CustomBlockType SMOOTH_SANDSTONE = new BlockSmoothSandstone();
	public static final CustomBlockType OAK_LEAVES = new BlockOakLeaves();
	public static final CustomBlockType TREE_OAK_LEAVES = new BlockTreeOakLeaves();

	public static final CustomBlockType STONE_AGE_CAMPFIRE = new BlockStoneAgeCampfire();
	public static final CustomBlockType EGYPTIAN_CAMPFIRE_STAGE1 = new BlockEgyptianCampfireStage1();

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
		register(SMOOTH_SANDSTONE);
		register(OAK_LEAVES);
		register(TREE_OAK_LEAVES);

		register(STONE_AGE_CAMPFIRE);
		register(EGYPTIAN_CAMPFIRE_STAGE1);
	}

	public static List<CustomBlockType> getContents() {
		return blocks;
	}

	public static CustomBlockType getBlock(String ID) {
		for (CustomBlockType block : blocks) {
			if (block.ID.equalsIgnoreCase(ID)) {
				return block;
			}
		}
		return null;
	}

	public static CustomBlockType getBlock(Block block) {
		List<MetadataValue> metadata_values = block.getMetadata("customBlockType");

		CustomBlockType customBlock;

		if (metadata_values.size() == 0) {
			customBlock = CustomBlockType.getVanillaBlock(block);
		} else {
			customBlock = ((CustomBlockMetadataValue) metadata_values.get(0)).getBlock();
		}

		return customBlock;
	}

	public static CustomBlockType getBlock(Location location) {
		return getBlock(location.getBlock());
	}

}
