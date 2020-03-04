package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CustomItems {
	public static ChatColor TIER1 = ChatColor.GREEN;
	public static ChatColor TIER2 = ChatColor.YELLOW;
	public static ChatColor TIER3 = ChatColor.GOLD;
	
	//Coal
	public static CustomItem COAL_ORE = new CustomItem(Material.COAL_ORE, TIER1 + "Coal");
	public static CustomItem COAL = new CustomItem(Material.COAL, TIER1 + "Coal");
	public static CustomItem COAL_DUST = new CustomItem(Material.COAL, TIER1 + "Coal Dust");
	public static CustomItem ASH = new CustomItem(Material.BONE_MEAL, TIER1 + "Ash");

	// Iron Items
	public static CustomItem IRON_ORE = new CustomItem(Material.IRON_ORE, TIER1 + "Iron Ore");
	public static CustomItem IRON_INGOT = new CustomItem(Material.IRON_INGOT);
	public static CustomItem IRON_PLATE = new CustomItem(Material.PAPER, TIER1 + "Iron Plate");
	public static CustomItem IRON_ROD = new CustomItem(Material.STICK, TIER1 + "Iron Rod");
	
	public static CustomItem IRON_PICKAXE = new CustomItem(Material.IRON_PICKAXE, TIER1 + "Iron Pickaxe");
	public static CustomItem IRON_HELMET = new CustomItem(Material.IRON_HELMET, TIER1 + "Iron Helmet");
	public static CustomItem IRON_CHESTPLATE = new CustomItem(Material.IRON_CHESTPLATE, TIER1 + "Iron Chestplate");
	public static CustomItem IRON_LEGGINGS = new CustomItem(Material.IRON_LEGGINGS, TIER1 + "Iron Leggings");
	public static CustomItem IRON_BOOTS = new CustomItem(Material.IRON_BOOTS, TIER1 + "Iron Boots");

	// Steel Items
	public static CustomItem STEEL_INGOT = new CustomItem(Material.IRON_INGOT, TIER3 + "Steel Ingot"); // enchanted
	public static CustomItem STEEL_PLATE = new CustomItem(Material.PAPER, TIER3 + "Steel Plate");// enchanted
	public static CustomItem STEEL_ROD = new CustomItem(Material.STICK, TIER3 + "Steel Rod"); // enchanted

	// Copper Items
	public static CustomItem COPPER_INGOT = new CustomItem(Material.GOLD_INGOT, TIER1 + "Copper Ingot");// enchanted
	public static CustomItem COPPER_PLATE = new CustomItem(Material.PAPER, TIER1 + "Copper Plate");// enchanted

	// bronze items
	public static CustomItem BRONZE_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Bronze Ingot");
	public static CustomItem BRONZE_PLATE = new CustomItem(Material.PAPER, TIER1 + "Bronze Plate");
	public static CustomItem BRONZE_ROD = new CustomItem(Material.STICK, TIER1 + "Bronze Rod");

	// aluminium
	public static CustomItem ALUMINUM_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Aluminum Ingot");
	public static CustomItem ALUMINUM_PLATE = new CustomItem(Material.PAPER, TIER1 + "Aluminum Plate");
	public static CustomItem ALUMINUM_ROD = new CustomItem(Material.STICK, TIER1 + "Aluminum Rod");

	// TIN
	public static CustomItem TIN_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Tin Ingot");
	public static CustomItem TIN_PLATE = new CustomItem(Material.PAPER, TIER1 + "Tin Plate");
	public static CustomItem TIN_ROD = new CustomItem(Material.STICK, TIER1 + "Tin Rod");

	// osmium
	public static CustomItem OSMIUM_INGOT = new CustomItem(Material.IRON_INGOT, TIER1 + "Osmium Ingot");
	public static CustomItem OSMIUM_PLATE = new CustomItem(Material.PAPER, TIER1 + "Osmium Plate");
	public static CustomItem OSMIUM_ROD = new CustomItem(Material.STICK, TIER1 + "Osmium Rod");
	
	//gold
	public static CustomItem GOLD_ORE = new CustomItem(Material.GOLD_ORE, TIER2 + "Gold ore");
	public static CustomItem GOLD_NUGGET = new CustomItem(Material.GOLD_NUGGET, TIER2 + "Gold nugget");
	public static CustomItem GOLD_INGOT = new CustomItem(Material.GOLD_INGOT, TIER2 + "Gold Ingot");
	public static CustomItem GOLD_ROD = new CustomItem(Material.STICK, TIER2 + "Gold Rod");
	
	
	public static CustomItem GOLD_CABLE = new CustomItem(Material.GOLD_NUGGET, TIER2 + "Gold Cable");
	

	// Silikon
	
	
	
	
	
	
	// Vanilla Items
	public static CustomItem STONE = new CustomItem(Material.STONE);
	public static CustomItem GRASS_BLOCK = new CustomItem(Material.GRASS_BLOCK);
	public static CustomItem DIRT = new CustomItem(Material.DIRT);
	public static CustomItem COBBLESTONE = new CustomItem(Material.COBBLESTONE);
	public static CustomItem OAK_PLANKS = new CustomItem(Material.OAK_PLANKS);
	public static CustomItem OAK_LOG = new CustomItem(Material.OAK_LOG);
	public static CustomItem COARSE_DIRT = new CustomItem(Material.COARSE_DIRT);

//		public static CustomItem OAK_SAPLING = new CustomItem(Material.OAK_SAPLING, TIER1 + "SAPLING");
//	public static CustomItem BEDROCK = new CustomItem(Material.BEDROCK, TIER1 + "BEDROCK");
//	public static CustomItem WATER = new CustomItem(Material.WATER, TIER1 + "WATER");
//	public static CustomItem LAVA = new CustomItem(Material.LAVA, TIER1 + "LAVA");
//	public static CustomItem SAND = new CustomItem(Material.SAND, TIER1 + "SAND");
//	public static CustomItem GRAVEL = new CustomItem(Material.GRAVEL, TIER1 + "GRAVEL");
//	public static CustomItem OAK_LOG = new CustomItem(Material.OAK_LOG, TIER1 + "LOG");
//	public static CustomItem LEAVES = new CustomItem(Material.LEAVES, TIER1 + "LEAVES");
//	public static CustomItem SPONGE = new CustomItem(Material.SPONGE, TIER1 + "SPONGE");
//	public static CustomItem GLASS = new CustomItem(Material.GLASS, TIER1 + "GLASS");
//	public static CustomItem LAPIS_ORE = new CustomItem(Material.LAPIS_ORE, TIER1 + "LAPIS_ORE");
//	public static CustomItem LAPIS_BLOCK = new CustomItem(Material.LAPIS_BLOCK, TIER1 + "LAPIS_BLOCK");
//	public static CustomItem DISPENSER = new CustomItem(Material.DISPENSER, TIER1 + "DISPENSER");
//	public static CustomItem SANDSTONE = new CustomItem(Material.SANDSTONE, TIER1 + "SANDSTONE");
//	public static CustomItem NOTE_BLOCK = new CustomItem(Material.NOTE_BLOCK, TIER1 + "NOTE_BLOCK");
//	public static CustomItem POWERED_RAIL = new CustomItem(Material.POWERED_RAIL, TIER1 + "POWERED_RAIL");
//	public static CustomItem DETECTOR_RAIL = new CustomItem(Material.DETECTOR_RAIL, TIER1 + "DETECTOR_RAIL");
//	public static CustomItem DEAD_BUSH = new CustomItem(Material.DEAD_BUSH, TIER1 + "DEAD_BUSH");
//	public static CustomItem BROWN_MUSHROOM = new CustomItem(Material.BROWN_MUSHROOM, TIER1 + "BROWN_MUSHROOM");
//	public static CustomItem RED_MUSHROOM = new CustomItem(Material.RED_MUSHROOM, TIER1 + "RED_MUSHROOM");
//	public static CustomItem BRICK = new CustomItem(Material.BRICK, TIER1 + "BRICK");
//	public static CustomItem TNT = new CustomItem(Material.TNT, TIER1 + "TNT");
//	public static CustomItem BOOKSHELF = new CustomItem(Material.BOOKSHELF, TIER1 + "BOOKSHELF");
//	public static CustomItem MOSSY_COBBLESTONE = new CustomItem(Material.MOSSY_COBBLESTONE, TIER1 + "MOSSY_COBBLESTONE");
//	public static CustomItem OBSIDIAN = new CustomItem(Material.OBSIDIAN, TIER1 + "OBSIDIAN");
//	public static CustomItem TORCH = new CustomItem(Material.TORCH, TIER1 + "TORCH");
//	public static CustomItem FIRE = new CustomItem(Material.FIRE, TIER1 + "FIRE");
//	public static CustomItem CHEST = new CustomItem(Material.CHEST, TIER1 + "CHEST");
//	public static CustomItem FURNACE = new CustomItem(Material.FURNACE, TIER1 + "FURNACE");
//	public static CustomItem OAK_DOOR = new CustomItem(Material.OAK_DOOR, TIER1 + "WOODEN_DOOR");
//	public static CustomItem LADDER = new CustomItem(Material.LADDER, TIER1 + "LADDER");
//	public static CustomItem RAILS = new CustomItem(Material.RAIL, TIER1 + "RAILS");
//	public static CustomItem COBBLESTONE_STAIRS = new CustomItem(Material.COBBLESTONE_STAIRS, TIER1 + "COBBLESTONE_STAIRS");
//	public static CustomItem LEVER = new CustomItem(Material.LEVER, TIER1 + "LEVER");
//	public static CustomItem REDSTONE_ORE = new CustomItem(Material.REDSTONE_ORE, TIER1 + "REDSTONE_ORE");
//	public static CustomItem STONE_BUTTON = new CustomItem(Material.STONE_BUTTON, TIER1 + "STONE_BUTTON");
//	public static CustomItem SNOW = new CustomItem(Material.SNOW, TIER1 + "SNOW");
//	public static CustomItem ICE = new CustomItem(Material.ICE, TIER1 + "ICE");
//	public static CustomItem SNOW_BLOCK = new CustomItem(Material.SNOW_BLOCK, TIER1 + "SNOW_BLOCK");
//	public static CustomItem CACTUS = new CustomItem(Material.CACTUS, TIER1 + "CACTUS");
//	public static CustomItem CLAY = new CustomItem(Material.CLAY, TIER1 + "CLAY");
//	public static CustomItem JUKEBOX = new CustomItem(Material.JUKEBOX, TIER1 + "JUKEBOX");
//	public static CustomItem OAK_FENCE = new CustomItem(Material.OAK_FENCE, TIER1 + "FENCE");
//	public static CustomItem PUMPKIN = new CustomItem(Material.PUMPKIN, TIER1 + "PUMPKIN");
//	public static CustomItem NETHERRACK = new CustomItem(Material.NETHERRACK, TIER1 + "NETHERRACK");
//	public static CustomItem SOUL_SAND = new CustomItem(Material.SOUL_SAND, TIER1 + "SOUL_SAND");
//	public static CustomItem GLOWSTONE = new CustomItem(Material.GLOWSTONE, TIER1 + "GLOWSTONE");
//	public static CustomItem JACK_O_LANTERN = new CustomItem(Material.JACK_O_LANTERN, TIER1 + "JACK_O_LANTERN");
//	public static CustomItem STONE_BRICK = new CustomItem(Material.STONE_BRICKS, TIER1 + "SMOOTH_BRICK");
//	public static CustomItem PUMPKIN_STEM = new CustomItem(Material.PUMPKIN_STEM, TIER1 + "PUMPKIN_STEM");
//	public static CustomItem MELON_STEM = new CustomItem(Material.MELON_STEM, TIER1 + "MELON_STEM");
//	public static CustomItem VINE = new CustomItem(Material.VINE, TIER1 + "VINE");
//	public static CustomItem BRICK_STAIRS = new CustomItem(Material.BRICK_STAIRS, TIER1 + "BRICK_STAIRS");
//	public static CustomItem SMOOTH_STAIRS = new CustomItem(Material.SMOOTH_STAIRS, TIER1 + "SMOOTH_STAIRS");
//	public static CustomItem MYCEL = new CustomItem(Material.MYCEL, TIER1 + "MYCEL");
//	public static CustomItem WATER_LILY = new CustomItem(Material.WATER_LILY, TIER1 + "WATER_LILY");
//	public static CustomItem NETHER_BRICK = new CustomItem(Material.NETHER_BRICK, TIER1 + "NETHER_BRICK");
//	public static CustomItem NETHER_FENCE = new CustomItem(Material.NETHER_FENCE, TIER1 + "NETHER_FENCE");
//	public static CustomItem NETHER_BRICK_STAIRS = new CustomItem(Material.NETHER_BRICK_STAIRS, TIER1 + "NETHER_BRICK_STAIRS");
//	public static CustomItem NETHER_WARTS = new CustomItem(Material.NETHER_WARTS, TIER1 + "NETHER_WARTS");
//	public static CustomItem ENCHANTMENT_TABLE = new CustomItem(Material.ENCHANTMENT_TABLE, TIER1 + "ENCHANTMENT_TABLE");
//	public static CustomItem BREWING_STAND = new CustomItem(Material.BREWING_STAND, TIER1 + "BREWING_STAND");
//	public static CustomItem CAULDRON = new CustomItem(Material.CAULDRON, TIER1 + "CAULDRON");
//	public static CustomItem ENDER_PORTAL = new CustomItem(Material.ENDER_PORTAL, TIER1 + "ENDER_PORTAL");
//	public static CustomItem ENDER_PORTAL_FRAME = new CustomItem(Material.ENDER_PORTAL_FRAME, TIER1 + "ENDER_PORTAL_FRAME");
//	public static CustomItem ENDER_STONE = new CustomItem(Material.ENDER_STONE, TIER1 + "ENDER_STONE");
//	public static CustomItem DRAGON_EGG = new CustomItem(Material.DRAGON_EGG, TIER1 + "DRAGON_EGG");
//	public static CustomItem REDSTONE_LAMP_OFF = new CustomItem(Material.REDSTONE_LAMP_OFF, TIER1 + "REDSTONE_LAMP_OFF");
//	public static CustomItem REDSTONE_LAMP_ON = new CustomItem(Material.REDSTONE_LAMP_ON, TIER1 + "REDSTONE_LAMP_ON");
//	public static CustomItem WOODEN_DOUBLE_STEP = new CustomItem(Material.WOODEN_DOUBLE_STEP, TIER1 + "WOODEN_DOUBLE_STEP");
//	public static CustomItem WOODEN_STEP = new CustomItem(Material.WOODEN_STEP, TIER1 + "WOODEN_STEP");
//	public static CustomItem COCOA = new CustomItem(Material.COCOA, TIER1 + "COCOA");
//	public static CustomItem SANDSTONE_STAIRS = new CustomItem(Material.SANDSTONE_STAIRS, TIER1 + "SANDSTONE_STAIRS");
//	public static CustomItem EMERALD_ORE = new CustomItem(Material.EMERALD_ORE, TIER1 + "EMERALD_ORE");
//	public static CustomItem ENDER_CHEST = new CustomItem(Material.ENDER_CHEST, TIER1 + "ENDER_CHEST");
//	public static CustomItem TRIPWIRE_HOOK = new CustomItem(Material.TRIPWIRE_HOOK, TIER1 + "TRIPWIRE_HOOK");
//	public static CustomItem TRIPWIRE = new CustomItem(Material.TRIPWIRE, TIER1 + "TRIPWIRE");
//	public static CustomItem EMERALD_BLOCK = new CustomItem(Material.EMERALD_BLOCK, TIER1 + "EMERALD_BLOCK");
//	public static CustomItem SPRUCE_WOODEN_STAIRS = new CustomItem(Material.SPRUCE_WOODEN_STAIRS, TIER1 + "SPRUCE_WOODEN_STAIRS");
//	public static CustomItem BIRCH_WOODEN_STAIRS = new CustomItem(Material.BIRCH_WOODEN_STAIRS, TIER1 + "BIRCH_WOODEN_STAIRS");
//	public static CustomItem JUNGLE_WOODEN_STAIRS = new CustomItem(Material.JUNGLE_WOODEN_STAIRS, TIER1 + "JUNGLE_WOODEN_STAIRS");
//	public static CustomItem COMMAND = new CustomItem(Material.COMMAND, TIER1 + "COMMAND");
//	public static CustomItem BEACON = new CustomItem(Material.BEACON, TIER1 + "BEACON");
//	public static CustomItem COBBLE_WALL = new CustomItem(Material.COBBLE_WALL, TIER1 + "COBBLE_WALL");
//	public static CustomItem FLOWER_POT = new CustomItem(Material.FLOWER_POT, TIER1 + "FLOWER_POT");
//	public static CustomItem CARROT = new CustomItem(Material.CARROT, TIER1 + "CARROT");
//	public static CustomItem POTATO = new CustomItem(Material.POTATO, TIER1 + "POTATO");
//	public static CustomItem WOODEN_BUTTON = new CustomItem(Material.WOODEN_BUTTON, TIER1 + "WOODEN_BUTTON");
//	public static CustomItem SKULL = new CustomItem(Material.SKULL, TIER1 + "SKULL");
//	public static CustomItem ANVIL = new CustomItem(Material.ANVIL, TIER1 + "ANVIL");
//	public static CustomItem TRAPPED_CHEST = new CustomItem(Material.TRAPPED_CHEST, TIER1 + "TRAPPED_CHEST");
//	public static CustomItem GOLDEN_PLATE = new CustomItem(Material.GOLDEN_PLATE, TIER1 + "GOLDEN_PLATE");
//	public static CustomItem IRON_PLATE = new CustomItem(Material.IRON_PLATE, TIER1 + "IRON_PLATE");
//	public static CustomItem REDSTONE_COMPARATOR_OFF = new CustomItem(Material.REDSTONE_COMPARATOR_OFF, TIER1 + "REDSTONE_COMPARATOR_OFF");
//	public static CustomItem REDSTONE_COMPARATOR_ON = new CustomItem(Material.REDSTONE_COMPARATOR_ON, TIER1 + "REDSTONE_COMPARATOR_ON");
//	public static CustomItem DAYLIGHT_DETECTOR = new CustomItem(Material.DAYLIGHT_DETECTOR, TIER1 + "DAYLIGHT_DETECTOR");
//	public static CustomItem REDSTONE_BLOCK = new CustomItem(Material.REDSTONE_BLOCK, TIER1 + "REDSTONE_BLOCK");
//	public static CustomItem QUARTZ_ORE = new CustomItem(Material.QUARTZ_ORE, TIER1 + "QUARTZ_ORE");
//	public static CustomItem HOPPER = new CustomItem(Material.HOPPER, TIER1 + "HOPPER");
//	public static CustomItem QUARTZ_BLOCK = new CustomItem(Material.QUARTZ_BLOCK, TIER1 + "QUARTZ_BLOCK");
//	public static CustomItem QUARTZ_STAIRS = new CustomItem(Material.QUARTZ_STAIRS, TIER1 + "QUARTZ_STAIRS");
//	public static CustomItem ACTIVATOR_RAIL = new CustomItem(Material.ACTIVATOR_RAIL, TIER1 + "ACTIVATOR_RAIL");
//	public static CustomItem DROPPER = new CustomItem(Material.DROPPER, TIER1 + "DROPPER");
//	public static CustomItem STAINED_CLAY = new CustomItem(Material.STAINED_CLAY, TIER1 + "STAINED_CLAY");
//	public static CustomItem STAINED_GLASS_PANE = new CustomItem(Material.STAINED_GLASS_PANE, TIER1 + "STAINED_GLASS_PANE");
//	public static CustomItem LEAVES_2 = new CustomItem(Material.LEAVES_2, TIER1 + "LEAVES_2");
//	public static CustomItem LOG_2 = new CustomItem(Material.LOG_2, TIER1 + "LOG_2");
//	public static CustomItem ACACIA_STAIRS = new CustomItem(Material.ACACIA_STAIRS, TIER1 + "ACACIA_STAIRS");
//	public static CustomItem DARK_OAK_STAIRS = new CustomItem(Material.DARK_OAK_STAIRS, TIER1 + "DARK_OAK_STAIRS");
//	public static CustomItem HAY_BLOCK = new CustomItem(Material.HAY_BLOCK, TIER1 + "HAY_BLOCK");
//	public static CustomItem TERRACOTTA = new CustomItem(Material.TERRACOTTA, TIER1 + "TERRACOTTA");
//	public static CustomItem COAL_BLOCK = new CustomItem(Material.COAL_BLOCK, TIER1 + "COAL_BLOCK");
//	public static CustomItem PACKED_ICE = new CustomItem(Material.PACKED_ICE, TIER1 + "PACKED_ICE");
//	public static CustomItem IRON_SHOVEL = new CustomItem(Material.IRON_SHOVEL, TIER1 + "IRON_SHOVEL");
//	public static CustomItem IRON_PICKAXE = new CustomItem(Material.IRON_PICKAXE, TIER1 + "IRON_PICKAXE");
//	public static CustomItem IRON_AXE = new CustomItem(Material.IRON_AXE, TIER1 + "IRON_AXE");
//	public static CustomItem FLINT_AND_STEEL = new CustomItem(Material.FLINT_AND_STEEL, TIER1 + "FLINT_AND_STEEL");
//	public static CustomItem APPLE = new CustomItem(Material.APPLE, TIER1 + "APPLE");
//	public static CustomItem BOW = new CustomItem(Material.BOW, TIER1 + "BOW");
//	public static CustomItem ARROW = new CustomItem(Material.ARROW, TIER1 + "ARROW");
//	public static CustomItem DIAMOND = new CustomItem(Material.DIAMOND, TIER1 + "DIAMOND");
//	public static CustomItem IRON_SWORD = new CustomItem(Material.IRON_SWORD, TIER1 + "IRON_SWORD");
//	public static CustomItem WOODEN_SWORD = new CustomItem(Material.WOODEN_SWORD, TIER1 + "WOODEN_SWORD");
//	public static CustomItem WOODEN_SHOVEL = new CustomItem(Material.WOODEN_SHOVEL, TIER1 + "WOODEN_SHOVEL");
//	public static CustomItem WOODEN_PICKAXE = new CustomItem(Material.WOODEN_PICKAXE, TIER1 + "WOODEN_PICKAXE");
//	public static CustomItem WOODEN_AXE = new CustomItem(Material.WOODEN_AXE, TIER1 + "WOODEN_AXE");
//	public static CustomItem STONE_SWORD = new CustomItem(Material.STONE_SWORD, TIER1 + "STONE_SWORD");
//	public static CustomItem STONE_SHOVEL = new CustomItem(Material.STONE_SHOVEL, TIER1 + "STONE_SHOVEL");
//	public static CustomItem STONE_PICKAXE = new CustomItem(Material.STONE_PICKAXE, TIER1 + "STONE_PICKAXE");
//	public static CustomItem STONE_AXE = new CustomItem(Material.STONE_AXE, TIER1 + "STONE_AXE");
//	public static CustomItem DIAMOND_SWORD = new CustomItem(Material.DIAMOND_SWORD, TIER1 + "DIAMOND_SWORD");
//	public static CustomItem DIAMOND_SHOVEL = new CustomItem(Material.DIAMOND_SHOVEL, TIER1 + "DIAMOND_SHOVEL");
//	public static CustomItem DIAMOND_PICKAXE = new CustomItem(Material.DIAMOND_PICKAXE, TIER1 + "DIAMOND_PICKAXE");
//	public static CustomItem DIAMOND_AXE = new CustomItem(Material.DIAMOND_AXE, TIER1 + "DIAMOND_AXE");
//	public static CustomItem STICK = new CustomItem(Material.STICK, TIER1 + "STICK");
//	public static CustomItem BOWL = new CustomItem(Material.BOWL, TIER1 + "BOWL");
//	public static CustomItem MUSHROOM_SOUP = new CustomItem(Material.MUSHROOM_STEW, TIER1 + "MUSHROOM_SOUP");
//	public static CustomItem GOLDEN_SWORD = new CustomItem(Material.GOLDEN_SWORD, TIER1 + "GOLDEN_SWORD");
//	public static CustomItem GOLDEN_SHOVEL = new CustomItem(Material.GOLDEN_SHOVEL, TIER1 + "GOLDEN_SHOVEL");
//	public static CustomItem GOLDEN_PICKAXE = new CustomItem(Material.GOLDEN_PICKAXE, TIER1 + "GOLDEN_PICKAXE");
//	public static CustomItem GOLDEN_AXE = new CustomItem(Material.GOLDEN_AXE, TIER1 + "GOLDEN_AXE");
//	public static CustomItem STRING = new CustomItem(Material.STRING, TIER1 + "STRING");
//	public static CustomItem FEATHER = new CustomItem(Material.FEATHER, TIER1 + "FEATHER");
//	public static CustomItem GUNPOWDER = new CustomItem(Material.GUNPOWDER, TIER1 + "GUNPOWDER");
//	public static CustomItem WOODEN_HOE = new CustomItem(Material.WOODEN_HOE, TIER1 + "WOODEN_HOE");
//	public static CustomItem STONE_HOE = new CustomItem(Material.STONE_HOE, TIER1 + "STONE_HOE");
//	public static CustomItem IRON_HOE = new CustomItem(Material.IRON_HOE, TIER1 + "IRON_HOE");
//	public static CustomItem DIAMOND_HOE = new CustomItem(Material.DIAMOND_HOE, TIER1 + "DIAMOND_HOE");
//	public static CustomItem GOLDEN_HOE = new CustomItem(Material.GOLDEN_HOE, TIER1 + "GOLDEN_HOE");
//	public static CustomItem WHEAT_SEEDS = new CustomItem(Material.WHEAT_SEEDS, TIER1 + "SEEDS");
//	public static CustomItem WHEAT = new CustomItem(Material.WHEAT, TIER1 + "WHEAT");
//	public static CustomItem BREAD = new CustomItem(Material.BREAD, TIER1 + "BREAD");
//	public static CustomItem LEATHER_HELMET = new CustomItem(Material.LEATHER_HELMET, TIER1 + "LEATHER_HELMET");
//	public static CustomItem LEATHER_CHESTPLATE = new CustomItem(Material.LEATHER_CHESTPLATE, TIER1 + "LEATHER_CHESTPLATE");
//	public static CustomItem LEATHER_LEGGINGS = new CustomItem(Material.LEATHER_LEGGINGS, TIER1 + "LEATHER_LEGGINGS");
//	public static CustomItem LEATHER_BOOTS = new CustomItem(Material.LEATHER_BOOTS, TIER1 + "LEATHER_BOOTS");
//	public static CustomItem CHAINMAIL_HELMET = new CustomItem(Material.CHAINMAIL_HELMET, TIER1 + "CHAINMAIL_HELMET");
//	public static CustomItem CHAINMAIL_CHESTPLATE = new CustomItem(Material.CHAINMAIL_CHESTPLATE, TIER1 + "CHAINMAIL_CHESTPLATE");
//	public static CustomItem CHAINMAIL_LEGGINGS = new CustomItem(Material.CHAINMAIL_LEGGINGS, TIER1 + "CHAINMAIL_LEGGINGS");
//	public static CustomItem CHAINMAIL_BOOTS = new CustomItem(Material.CHAINMAIL_BOOTS, TIER1 + "CHAINMAIL_BOOTS");
//	public static CustomItem IRON_HELMET = new CustomItem(Material.IRON_HELMET, TIER1 + "IRON_HELMET");
//	public static CustomItem IRON_CHESTPLATE = new CustomItem(Material.IRON_CHESTPLATE, TIER1 + "IRON_CHESTPLATE");
//	public static CustomItem IRON_LEGGINGS = new CustomItem(Material.IRON_LEGGINGS, TIER1 + "IRON_LEGGINGS");
//	public static CustomItem IRON_BOOTS = new CustomItem(Material.IRON_BOOTS, TIER1 + "IRON_BOOTS");
//	public static CustomItem DIAMOND_HELMET = new CustomItem(Material.DIAMOND_HELMET, TIER1 + "DIAMOND_HELMET");
//	public static CustomItem DIAMOND_CHESTPLATE = new CustomItem(Material.DIAMOND_CHESTPLATE, TIER1 + "DIAMOND_CHESTPLATE");
//	public static CustomItem DIAMOND_LEGGINGS = new CustomItem(Material.DIAMOND_LEGGINGS, TIER1 + "DIAMOND_LEGGINGS");
//	public static CustomItem DIAMOND_BOOTS = new CustomItem(Material.DIAMOND_BOOTS, TIER1 + "DIAMOND_BOOTS");
//	public static CustomItem GOLDEN_HELMET = new CustomItem(Material.GOLDEN_HELMET, TIER1 + "GOLDEN_HELMET");
//	public static CustomItem GOLDEN_CHESTPLATE = new CustomItem(Material.GOLDEN_CHESTPLATE, TIER1 + "GOLDEN_CHESTPLATE");
//	public static CustomItem GOLDEN_LEGGINGS = new CustomItem(Material.GOLDEN_LEGGINGS, TIER1 + "GOLDEN_LEGGINGS");
//	public static CustomItem GOLDEN_BOOTS = new CustomItem(Material.GOLDEN_BOOTS, TIER1 + "GOLDEN_BOOTS");
//	public static CustomItem FLINT = new CustomItem(Material.FLINT, TIER1 + "FLINT");
//	public static CustomItem PORK = new CustomItem(Material.PORK, TIER1 + "PORK");
//	public static CustomItem GRILLED_PORK = new CustomItem(Material.GRILLED_PORK, TIER1 + "GRILLED_PORK");
//	public static CustomItem PAINTING = new CustomItem(Material.PAINTING, TIER1 + "PAINTING");
//	public static CustomItem GOLDEN_APPLE = new CustomItem(Material.GOLDEN_APPLE, TIER1 + "GOLDEN_APPLE");
//	public static CustomItem SIGN = new CustomItem(Material.SIGN, TIER1 + "SIGN");
//	public static CustomItem WOODEN_DOOR = new CustomItem(Material.WOODEN_DOOR, TIER1 + "WOODEN_DOOR");
//	public static CustomItem BUCKET = new CustomItem(Material.BUCKET, TIER1 + "BUCKET");
//	public static CustomItem WATER_BUCKET = new CustomItem(Material.WATER_BUCKET, TIER1 + "WATER_BUCKET");
//	public static CustomItem LAVA_BUCKET = new CustomItem(Material.LAVA_BUCKET, TIER1 + "LAVA_BUCKET");
//	public static CustomItem MINECART = new CustomItem(Material.MINECART, TIER1 + "MINECART");
//	public static CustomItem SADDLE = new CustomItem(Material.SADDLE, TIER1 + "SADDLE");
//	public static CustomItem IRON_DOOR = new CustomItem(Material.IRON_DOOR, TIER1 + "IRON_DOOR");
//	public static CustomItem REDSTONE = new CustomItem(Material.REDSTONE, TIER1 + "REDSTONE");
//	public static CustomItem SNOW_BALL = new CustomItem(Material.SNOW_BALL, TIER1 + "SNOW_BALL");
//	public static CustomItem BOAT = new CustomItem(Material.BOAT, TIER1 + "BOAT");
//	public static CustomItem LEATHER = new CustomItem(Material.LEATHER, TIER1 + "LEATHER");
//	public static CustomItem MILK_BUCKET = new CustomItem(Material.MILK_BUCKET, TIER1 + "MILK_BUCKET");
//	public static CustomItem CLAY_BRICK = new CustomItem(Material.CLAY_BRICK, TIER1 + "CLAY_BRICK");
//	public static CustomItem CLAY_BALL = new CustomItem(Material.CLAY_BALL, TIER1 + "CLAY_BALL");
//	public static CustomItem SUGAR_CANE = new CustomItem(Material.SUGAR_CANE, TIER1 + "SUGAR_CANE");
//	public static CustomItem PAPER = new CustomItem(Material.PAPER, TIER1 + "PAPER");
//	public static CustomItem BOOK = new CustomItem(Material.BOOK, TIER1 + "BOOK");
//	public static CustomItem SLIME_BALL = new CustomItem(Material.SLIME_BALL, TIER1 + "SLIME_BALL");
//	public static CustomItem STORAGE_MINECART = new CustomItem(Material.STORAGE_MINECART, TIER1 + "STORAGE_MINECART");
//	public static CustomItem POWERED_MINECART = new CustomItem(Material.POWERED_MINECART, TIER1 + "POWERED_MINECART");
//	public static CustomItem EGG = new CustomItem(Material.EGG, TIER1 + "EGG");
//	public static CustomItem COMPASS = new CustomItem(Material.COMPASS, TIER1 + "COMPASS");
//	public static CustomItem FISHING_ROD = new CustomItem(Material.FISHING_ROD, TIER1 + "FISHING_ROD");
//	public static CustomItem WATCH = new CustomItem(Material.WATCH, TIER1 + "WATCH");
//	public static CustomItem GLOWSTONE_DUST = new CustomItem(Material.GLOWSTONE_DUST, TIER1 + "GLOWSTONE_DUST");
//	public static CustomItem RAW_FISH = new CustomItem(Material.RAW_FISH, TIER1 + "RAW_FISH");
//	public static CustomItem COOKED_FISH = new CustomItem(Material.COOKED_FISH, TIER1 + "COOKED_FISH");
//	public static CustomItem INK_SACK = new CustomItem(Material.INK_SACK, TIER1 + "INK_SACK");
//	public static CustomItem BONE = new CustomItem(Material.BONE, TIER1 + "BONE");
//	public static CustomItem SUGAR = new CustomItem(Material.SUGAR, TIER1 + "SUGAR");
//	public static CustomItem CAKE = new CustomItem(Material.CAKE, TIER1 + "CAKE");
//	public static CustomItem BED = new CustomItem(Material.BED, TIER1 + "BED");
//	public static CustomItem DIODE = new CustomItem(Material.DIODE, TIER1 + "DIODE");
//	public static CustomItem COOKIE = new CustomItem(Material.COOKIE, TIER1 + "COOKIE");
//	public static CustomItem MAP = new CustomItem(Material.MAP, TIER1 + "MAP");
//	public static CustomItem SHEARS = new CustomItem(Material.SHEARS, TIER1 + "SHEARS");
//	public static CustomItem MELON = new CustomItem(Material.MELON, TIER1 + "MELON");
//	public static CustomItem PUMPKIN_SEEDS = new CustomItem(Material.PUMPKIN_SEEDS, TIER1 + "PUMPKIN_SEEDS");
//	public static CustomItem MELON_SEEDS = new CustomItem(Material.MELON_SEEDS, TIER1 + "MELON_SEEDS");
//	public static CustomItem RAW_BEEF = new CustomItem(Material.RAW_BEEF, TIER1 + "RAW_BEEF");
//	public static CustomItem COOKED_BEEF = new CustomItem(Material.COOKED_BEEF, TIER1 + "COOKED_BEEF");
//	public static CustomItem RAW_CHICKEN = new CustomItem(Material.RAW_CHICKEN, TIER1 + "RAW_CHICKEN");
//	public static CustomItem COOKED_CHICKEN = new CustomItem(Material.COOKED_CHICKEN, TIER1 + "COOKED_CHICKEN");
//	public static CustomItem ROTTEN_FLESH = new CustomItem(Material.ROTTEN_FLESH, TIER1 + "ROTTEN_FLESH");
//	public static CustomItem ENDER_PEARL = new CustomItem(Material.ENDER_PEARL, TIER1 + "ENDER_PEARL");
//	public static CustomItem BLAZE_ROD = new CustomItem(Material.BLAZE_ROD, TIER1 + "BLAZE_ROD");
//	public static CustomItem GHAST_TEAR = new CustomItem(Material.GHAST_TEAR, TIER1 + "GHAST_TEAR");
//	public static CustomItem GOLDEN_NUGGET = new CustomItem(Material.GOLDEN_NUGGET, TIER1 + "GOLDEN_NUGGET");
//	public static CustomItem NETHER_STALK = new CustomItem(Material.NETHER_STALK, TIER1 + "NETHER_STALK");
//	public static CustomItem POTION = new CustomItem(Material.POTION, TIER1 + "POTION");
//	public static CustomItem GLASS_BOTTLE = new CustomItem(Material.GLASS_BOTTLE, TIER1 + "GLASS_BOTTLE");
//	public static CustomItem SPIDER_EYE = new CustomItem(Material.SPIDER_EYE, TIER1 + "SPIDER_EYE");
//	public static CustomItem FERMENTED_SPIDER_EYE = new CustomItem(Material.FERMENTED_SPIDER_EYE, TIER1 + "FERMENTED_SPIDER_EYE");
//	public static CustomItem BLAZE_POWDER = new CustomItem(Material.BLAZE_POWDER, TIER1 + "BLAZE_POWDER");
//	public static CustomItem MAGMA_CREAM = new CustomItem(Material.MAGMA_CREAM, TIER1 + "MAGMA_CREAM");
//	public static CustomItem BREWING_STAND_ITEM = new CustomItem(Material.BREWING_STAND_ITEM, TIER1 + "BREWING_STAND_ITEM");
//	public static CustomItem CAULDRON_ITEM = new CustomItem(Material.CAULDRON_ITEM, TIER1 + "CAULDRON_ITEM");
//	public static CustomItem EYE_OF_ENDER = new CustomItem(Material.EYE_OF_ENDER, TIER1 + "EYE_OF_ENDER");
//	public static CustomItem SPECKLED_MELON = new CustomItem(Material.SPECKLED_MELON, TIER1 + "SPECKLED_MELON");
//	public static CustomItem MONSTER_EGG = new CustomItem(Material.MONSTER_EGG, TIER1 + "MONSTER_EGG");
//	public static CustomItem EXP_BOTTLE = new CustomItem(Material.EXP_BOTTLE, TIER1 + "EXP_BOTTLE");
//	public static CustomItem FIREBALL = new CustomItem(Material.FIREBALL, TIER1 + "FIREBALL");
//	public static CustomItem BOOK_AND_QUILL = new CustomItem(Material.BOOK_AND_QUILL, TIER1 + "BOOK_AND_QUILL");
//	public static CustomItem WRITTEN_BOOK = new CustomItem(Material.WRITTEN_BOOK, TIER1 + "WRITTEN_BOOK");
//	public static CustomItem EMERALD = new CustomItem(Material.EMERALD, TIER1 + "EMERALD");
//	public static CustomItem ITEM_FRAME = new CustomItem(Material.ITEM_FRAME, TIER1 + "ITEM_FRAME");
//	public static CustomItem FLOWER_POT_ITEM = new CustomItem(Material.FLOWER_POT_ITEM, TIER1 + "FLOWER_POT_ITEM");
//	public static CustomItem CARROT_ITEM = new CustomItem(Material.CARROT_ITEM, TIER1 + "CARROT_ITEM");
//	public static CustomItem POTATO_ITEM = new CustomItem(Material.POTATO_ITEM, TIER1 + "POTATO_ITEM");
//	public static CustomItem BAKED_POTATO = new CustomItem(Material.BAKED_POTATO, TIER1 + "BAKED_POTATO");
//	public static CustomItem POISONOUS_POTATO = new CustomItem(Material.POISONOUS_POTATO, TIER1 + "POISONOUS_POTATO");
//	public static CustomItem EMPTY_MAP = new CustomItem(Material.EMPTY_MAP, TIER1 + "EMPTY_MAP");
//	public static CustomItem GOLDEN_CARROT = new CustomItem(Material.GOLDEN_CARROT, TIER1 + "GOLDEN_CARROT");
//	public static CustomItem SKULL_ITEM = new CustomItem(Material.SKULL_ITEM, TIER1 + "SKULL_ITEM");
//	public static CustomItem CARROT_STICK = new CustomItem(Material.CARROT_STICK, TIER1 + "CARROT_STICK");
//	public static CustomItem NETHER_STAR = new CustomItem(Material.NETHER_STAR, TIER1 + "NETHER_STAR");
//	public static CustomItem PUMPKIN_PIE = new CustomItem(Material.PUMPKIN_PIE, TIER1 + "PUMPKIN_PIE");
//	public static CustomItem FIREWORK = new CustomItem(Material.FIREWORK, TIER1 + "FIREWORK");
//	public static CustomItem FIREWORK_CHARGE = new CustomItem(Material.FIREWORK_CHARGE, TIER1 + "FIREWORK_CHARGE");
//	public static CustomItem ENCHANTED_BOOK = new CustomItem(Material.ENCHANTED_BOOK, TIER1 + "ENCHANTED_BOOK");
//	public static CustomItem REDSTONE_COMPARATOR = new CustomItem(Material.REDSTONE_COMPARATOR, TIER1 + "REDSTONE_COMPARATOR");
//	public static CustomItem NETHER_BRICK_ITEM = new CustomItem(Material.NETHER_BRICK_ITEM, TIER1 + "NETHER_BRICK_ITEM");
//	public static CustomItem QUARTZ = new CustomItem(Material.QUARTZ, TIER1 + "QUARTZ");
//	public static CustomItem EXPLOSIVE_MINECART = new CustomItem(Material.EXPLOSIVE_MINECART, TIER1 + "EXPLOSIVE_MINECART");
//	public static CustomItem HOPPER_MINECART = new CustomItem(Material.HOPPER_MINECART, TIER1 + "HOPPER_MINECART");
//	public static CustomItem IRON_BARDING = new CustomItem(Material.IRON_BARDING, TIER1 + "IRON_BARDING");
//	public static CustomItem GOLDEN_BARDING = new CustomItem(Material.GOLDEN_BARDING, TIER1 + "GOLDEN_BARDING");
//	public static CustomItem DIAMOND_BARDING = new CustomItem(Material.DIAMOND_BARDING, TIER1 + "DIAMOND_BARDING");
//	public static CustomItem LEASH = new CustomItem(Material.LEASH, TIER1 + "LEASH");
//	public static CustomItem NAME_TAG = new CustomItem(Material.NAME_TAG, TIER1 + "NAME_TAG");


}
