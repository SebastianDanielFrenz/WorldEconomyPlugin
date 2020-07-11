package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.metadata.MetadataValue;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.WorldEconomyPlugin;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockAndesite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCoarseDirt;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockDiorite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockGranite;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockOakLeaves;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockCopperOre;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSand;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSandstoneTrigger;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockSmoothSandstone;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockTreeOakLeaves1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.BlockTreeOakLeaves2;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires.BlockEgyptianCampfireStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.campfires.BlockStoneAgeCampfire;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.block.blocks.machines.crafting_tables.BlockStoneAgeCraftingTable;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.item.VanillaItemIdentifier;

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
	public static final CustomBlockType SMOOTH_SANDSTONE = new BlockSmoothSandstone();
	public static final CustomBlockType OAK_LEAVES = new BlockOakLeaves();
	public static final CustomBlockType TREE_OAK_LEAVES1 = new BlockTreeOakLeaves1();
	public static final CustomBlockType TREE_OAK_LEAVES2 = new BlockTreeOakLeaves2();

	public static final CustomBlockType STONE_AGE_CRAFTING_TABLE = new BlockStoneAgeCraftingTable();
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
		register(SMOOTH_SANDSTONE);
		register(OAK_LEAVES);
		register(TREE_OAK_LEAVES1);
		register(TREE_OAK_LEAVES2);

		register(STONE_AGE_CRAFTING_TABLE);
		register(STONE_AGE_CAMPFIRE);
		register(EGYPTIAN_CAMPFIRE_STAGE1);
	}

	/**
	 * This function is run after all add-ons have registered their custom
	 * blocks.
	 */
	public static void check() {
		Map<VanillaItemIdentifier, List<CustomBlockType>> map = new TreeMap<VanillaItemIdentifier, List<CustomBlockType>>();
		boolean broken = false;

		for (CustomBlockType type : blocks) {
			if (type.vanilla) {
				if (map.get(new VanillaItemIdentifier(type.material, type.vanilla_data)) == null) {
					List<CustomBlockType> list = new ArrayList<CustomBlockType>(1);
					list.add(type);
					map.put(new VanillaItemIdentifier(type.material, type.vanilla_data), list);
				} else {
					map.get(new VanillaItemIdentifier(type.material, type.vanilla_data)).add(type);
					broken = true;
				}
			}
		}

		if (broken) {
			WorldEconomyPlugin.plugin.getLogger().severe(
					"The block registry is broken. There are multiple blocks registered as the same vanilla block!");
			WorldEconomyPlugin.plugin.getLogger().severe("Dumping custom block registry...");
			List<CustomBlockType> list;
			String msg;
			for (VanillaItemIdentifier identifier : map.keySet()) {
				list = map.get(identifier);
				if (list.size() > 1) {
					msg = list.size() + "x " + identifier.material.name() + "[" + identifier.data + "] (";
					for (int i = 0; i < list.size() - 1; i++) {
						msg += list.get(i).ID + "[" + list.get(i).getClass().getCanonicalName() + "]" + ", ";
					}
					msg += list.get(list.size() - 1).ID + "[" + list.get(list.size() - 1).getClass().getCanonicalName()
							+ "])";

					WorldEconomyPlugin.plugin.getLogger().warning(msg);
				} else {
					WorldEconomyPlugin.plugin.getLogger()
							.info("1x " + identifier.material.name() + "[" + identifier.data + "] (" + list.get(0).ID
									+ "[" + list.get(0).getClass().getCanonicalName() + "])");
				}
			}

			throw new RuntimeException(
					"Dublicate custom block entry for CustomBlockType[vanilla=true]. More details above.");
		}
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
