package io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces.BasicBlastFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage2;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage3;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves.BasicSieveStage1;

public enum CustomItem {

	COAL_ORE(Material.COAL_ORE, Tiers.TIER1 + "Coal"),
	COAL(Material.COAL, Tiers.TIER1 + "Coal"),
	CHARCOAL(Material.CHARCOAL, Tiers.TIER1 + "Charcoal"),
	COAL_DUST(Material.COAL, Tiers.TIER1 + "Coal Dust"),
	ASH(Material.BONE_MEAL, Tiers.TIER1 + "Ash"),

	// Iron Items
	IRON_ORE(Material.IRON_ORE, Tiers.TIER1 + "Iron Ore"),
	IRON_INGOT(Material.IRON_INGOT),
	IRON_PLATE(Material.PAPER, Tiers.TIER1 + "Iron Plate"),
	IRON_ROD(Material.STICK, Tiers.TIER1 + "Iron Rod"),

	IRON_PICKAXE(Material.IRON_PICKAXE, Tiers.TIER1 + "Iron Pickaxe"),
	IRON_HELMET(Material.IRON_HELMET, Tiers.TIER1 + "Iron Helmet"),
	IRON_CHESTPLATE(Material.IRON_CHESTPLATE, Tiers.TIER1 + "Iron Chestplate"),
	IRON_LEGGINGS(Material.IRON_LEGGINGS, Tiers.TIER1 + "Iron Leggings"),
	IRON_BOOTS(Material.IRON_BOOTS, Tiers.TIER1 + "Iron Boots"),

	// Steel Items
	STEEL_INGOT(Material.IRON_INGOT, Tiers.TIER3 + "Steel Ingot"), // enchanted
	STEEL_PLATE(Material.PAPER, Tiers.TIER3 + "Steel Plate"), // enchanted
	STEEL_ROD(Material.STICK, Tiers.TIER3 + "Steel Rod"), // enchanted

	// Copper Items
	COPPER_INGOT(Material.GOLD_INGOT, Tiers.TIER1 + "Copper Ingot"), // enchanted
	COPPER_PLATE(Material.PAPER, Tiers.TIER1 + "Copper Plate"), // enchanted

	// bronze items
	BRONZE_INGOT(Material.IRON_INGOT, Tiers.TIER1 + "Bronze Ingot"),
	BRONZE_PLATE(Material.PAPER, Tiers.TIER1 + "Bronze Plate"),
	BRONZE_ROD(Material.STICK, Tiers.TIER1 + "Bronze Rod"),

	// aluminum
	ALUMINUM_INGOT(Material.IRON_INGOT, Tiers.TIER1 + "Aluminum Ingot"),
	ALUMINUM_PLATE(Material.PAPER, Tiers.TIER1 + "Aluminum Plate"),
	ALUMINUM_ROD(Material.STICK, Tiers.TIER1 + "Aluminum Rod"),

	// TIN
	TIN_INGOT(Material.IRON_INGOT, Tiers.TIER1 + "Tin Ingot"),
	TIN_PLATE(Material.PAPER, Tiers.TIER1 + "Tin Plate"),
	TIN_ROD(Material.STICK, Tiers.TIER1 + "Tin Rod"),

	// osmium
	OSMIUM_INGOT(Material.IRON_INGOT, Tiers.TIER1 + "Osmium Ingot"),
	OSMIUM_PLATE(Material.PAPER, Tiers.TIER1 + "Osmium Plate"),
	OSMIUM_ROD(Material.STICK, Tiers.TIER1 + "Osmium Rod"),

	// gold
	GOLD_ORE(Material.GOLD_ORE, Tiers.TIER2 + "Gold ore"),
	GOLD_NUGGET(Material.GOLD_NUGGET, Tiers.TIER2 + "Gold nugget"),
	GOLD_INGOT(Material.GOLD_INGOT, Tiers.TIER2 + "Gold Ingot"),
	GOLD_ROD(Material.STICK, Tiers.TIER2 + "Gold Rod"),

	GOLD_CABLE(Material.GOLD_NUGGET, Tiers.TIER2 + "Gold Cable"),

	// silicon

	// fluids
	OIL_BUCKET(Material.LAVA_BUCKET, Tiers.TIER1 + "Oil Bucket"),

	// buckets
	BUCKET(Material.BUCKET, Tiers.TIER1 + "Bucket"),
	WATER_BUCKET(Material.WATER_BUCKET, Tiers.TIER1 + "Water Bucket"),

	// clay buckets
	CLAY_BUCKET(Material.BUCKET, Tiers.TIER1 + "Clay Bucket"),
	WATER_CLAY_BUCKET(Material.WATER_BUCKET, Tiers.TIER1 + "Water Clay Bucket"),

	// machines
	// furnaces
	BASIC_FURNACE_STAGE1(new BasicFurnaceStage1(null).getKategory().display, Tiers.TIER1 + "Basic Furnace Stage 1"),
	BASIC_FURNACE_STAGE2(new BasicFurnaceStage2(null).getKategory().display, Tiers.TIER1 + "Basic Furnace Stage 2"),
	BASIC_FURNACE_STAGE3(new BasicFurnaceStage3(null).getKategory().display, Tiers.TIER1 + "Basic Furnace Stage 3"),
	// sieves
	BASIC_SIEVE_STAGE1(new BasicSieveStage1(null).getKategory().display, Tiers.TIER1 + "Basic Sieve Stage 1"),
	// blast furnaces
	BASIC_BLAST_FURNACE_STAGE1(new BasicBlastFurnaceStage1(null).getKategory().display, Tiers.TIER1 + "Basic Blast Furnace Stage 1"),

	// Vanilla Items
	STONE(Material.STONE),
	GRASS_BLOCK(Material.GRASS_BLOCK),
	DIRT(Material.DIRT),
	COBBLESTONE(Material.COBBLESTONE),
	OAK_PLANKS(Material.OAK_PLANKS),
	OAK_LOG(Material.OAK_LOG),
	COARSE_DIRT(Material.COARSE_DIRT),

	;

	private CustomItem(Material base, String name) {
		base_material = base;
		item_name = name;
	}

	private CustomItem(Material base) {
		base_material = base;
		item_name = null;
	}

	public final Material base_material;
	public final String item_name;

	public boolean matches(ItemStack itemStack) {
		return itemStack == null ? false
				: (itemStack.getType() == base_material
						&& (itemStack.hasItemMeta() ? itemStack.getItemMeta().getDisplayName().equals(item_name) : item_name == null));
	}

	public ItemStack toItemStack() {
		if (item_name == null) {
			return new ItemStack(base_material);
		} else {
			ItemStack out = new ItemStack(base_material);
			ItemMeta meta = out.getItemMeta();
			meta.setDisplayName(item_name);
			out.setItemMeta(meta);
			return out;
		}
	}

	// Coal

	// OAK_SAPLING(Material.OAK_SAPLING, Tiers.TIER1 + "SAPLING");
	// BEDROCK(Material.BEDROCK, Tiers.TIER1 + "BEDROCK");
	// WATER(Material.WATER, Tiers.TIER1 + "WATER");
	// LAVA(Material.LAVA, Tiers.TIER1 + "LAVA");
	// SAND(Material.SAND, Tiers.TIER1 + "SAND");
	// GRAVEL(Material.GRAVEL, Tiers.TIER1 + "GRAVEL");
	// OAK_LOG(Material.OAK_LOG, Tiers.TIER1 + "LOG");
	// LEAVES(Material.LEAVES, Tiers.TIER1 + "LEAVES");
	// SPONGE(Material.SPONGE, Tiers.TIER1 + "SPONGE");
	// GLASS(Material.GLASS, Tiers.TIER1 + "GLASS");
	// LAPIS_ORE(Material.LAPIS_ORE, Tiers.TIER1 + "LAPIS_ORE");
	// LAPIS_BLOCK(Material.LAPIS_BLOCK, Tiers.TIER1 + "LAPIS_BLOCK");
	// DISPENSER(Material.DISPENSER, Tiers.TIER1 + "DISPENSER");
	// SANDSTONE(Material.SANDSTONE, Tiers.TIER1 + "SANDSTONE");
	// NOTE_BLOCK(Material.NOTE_BLOCK, Tiers.TIER1 + "NOTE_BLOCK");
	// POWERED_RAIL(Material.POWERED_RAIL, Tiers.TIER1 + "POWERED_RAIL");
	// DETECTOR_RAIL(Material.DETECTOR_RAIL, Tiers.TIER1 + "DETECTOR_RAIL");
	// DEAD_BUSH(Material.DEAD_BUSH, Tiers.TIER1 + "DEAD_BUSH");
	// BROWN_MUSHROOM(Material.BROWN_MUSHROOM, Tiers.TIER1 + "BROWN_MUSHROOM");
	// RED_MUSHROOM(Material.RED_MUSHROOM, Tiers.TIER1 + "RED_MUSHROOM");
	// BRICK(Material.BRICK, Tiers.TIER1 + "BRICK");
	// TNT(Material.TNT, Tiers.TIER1 + "TNT");
	// BOOKSHELF(Material.BOOKSHELF, Tiers.TIER1 + "BOOKSHELF");
	// MOSSY_COBBLESTONE(Material.MOSSY_COBBLESTONE, Tiers.TIER1 +
	// "MOSSY_COBBLESTONE");
	// OBSIDIAN(Material.OBSIDIAN, Tiers.TIER1 + "OBSIDIAN");
	// TORCH(Material.TORCH, Tiers.TIER1 + "TORCH");
	// FIRE(Material.FIRE, Tiers.TIER1 + "FIRE");
	// CHEST(Material.CHEST, Tiers.TIER1 + "CHEST");
	// FURNACE(Material.FURNACE, Tiers.TIER1 + "FURNACE");
	// OAK_DOOR(Material.OAK_DOOR, Tiers.TIER1 + "WOODEN_DOOR");
	// LADDER(Material.LADDER, Tiers.TIER1 + "LADDER");
	// RAILS(Material.RAIL, Tiers.TIER1 + "RAILS");
	// COBBLESTONE_STAIRS(Material.COBBLESTONE_STAIRS, Tiers.TIER1 +
	// "COBBLESTONE_STAIRS");
	// LEVER(Material.LEVER, Tiers.TIER1 + "LEVER");
	// REDSTONE_ORE(Material.REDSTONE_ORE, Tiers.TIER1 + "REDSTONE_ORE");
	// STONE_BUTTON(Material.STONE_BUTTON, Tiers.TIER1 + "STONE_BUTTON");
	// SNOW(Material.SNOW, Tiers.TIER1 + "SNOW");
	// ICE(Material.ICE, Tiers.TIER1 + "ICE");
	// SNOW_BLOCK(Material.SNOW_BLOCK, Tiers.TIER1 + "SNOW_BLOCK");
	// CACTUS(Material.CACTUS, Tiers.TIER1 + "CACTUS");
	// CLAY(Material.CLAY, Tiers.TIER1 + "CLAY");
	// JUKEBOX(Material.JUKEBOX, Tiers.TIER1 + "JUKEBOX");
	// OAK_FENCE(Material.OAK_FENCE, Tiers.TIER1 + "FENCE");
	// PUMPKIN(Material.PUMPKIN, Tiers.TIER1 + "PUMPKIN");
	// NETHERRACK(Material.NETHERRACK, Tiers.TIER1 + "NETHERRACK");
	// SOUL_SAND(Material.SOUL_SAND, Tiers.TIER1 + "SOUL_SAND");
	// GLOWSTONE(Material.GLOWSTONE, Tiers.TIER1 + "GLOWSTONE");
	// JACK_O_LANTERN(Material.JACK_O_LANTERN, Tiers.TIER1 + "JACK_O_LANTERN");
	// STONE_BRICK(Material.STONE_BRICKS, Tiers.TIER1 + "SMOOTH_BRICK");
	// PUMPKIN_STEM(Material.PUMPKIN_STEM, Tiers.TIER1 + "PUMPKIN_STEM");
	// MELON_STEM(Material.MELON_STEM, Tiers.TIER1 + "MELON_STEM");
	// VINE(Material.VINE, Tiers.TIER1 + "VINE");
	// BRICK_STAIRS(Material.BRICK_STAIRS, Tiers.TIER1 + "BRICK_STAIRS");
	// SMOOTH_STAIRS(Material.SMOOTH_STAIRS, Tiers.TIER1 + "SMOOTH_STAIRS");
	// MYCEL(Material.MYCEL, Tiers.TIER1 + "MYCEL");
	// WATER_LILY(Material.WATER_LILY, Tiers.TIER1 + "WATER_LILY");
	// NETHER_BRICK(Material.NETHER_BRICK, Tiers.TIER1 + "NETHER_BRICK");
	// NETHER_FENCE(Material.NETHER_FENCE, Tiers.TIER1 + "NETHER_FENCE");
	// NETHER_BRICK_STAIRS(Material.NETHER_BRICK_STAIRS, Tiers.TIER1 +
	// "NETHER_BRICK_STAIRS");
	// NETHER_WARTS(Material.NETHER_WARTS, Tiers.TIER1 + "NETHER_WARTS");
	// ENCHANTMENT_TABLE(Material.ENCHANTMENT_TABLE, Tiers.TIER1 +
	// "ENCHANTMENT_TABLE");
	// BREWING_STAND(Material.BREWING_STAND, Tiers.TIER1 + "BREWING_STAND");
	// CAULDRON(Material.CAULDRON, Tiers.TIER1 + "CAULDRON");
	// ENDER_PORTAL(Material.ENDER_PORTAL, Tiers.TIER1 + "ENDER_PORTAL");
	// ENDER_PORTAL_FRAME(Material.ENDER_PORTAL_FRAME, Tiers.TIER1 +
	// "ENDER_PORTAL_FRAME");
	// ENDER_STONE(Material.ENDER_STONE, Tiers.TIER1 + "ENDER_STONE");
	// DRAGON_EGG(Material.DRAGON_EGG, Tiers.TIER1 + "DRAGON_EGG");
	// REDSTONE_LAMP_OFF(Material.REDSTONE_LAMP_OFF, Tiers.TIER1 +
	// "REDSTONE_LAMP_OFF");
	// REDSTONE_LAMP_ON(Material.REDSTONE_LAMP_ON, Tiers.TIER1 +
	// "REDSTONE_LAMP_ON");
	// WOODEN_DOUBLE_STEP(Material.WOODEN_DOUBLE_STEP, Tiers.TIER1 +
	// "WOODEN_DOUBLE_STEP");
	// WOODEN_STEP(Material.WOODEN_STEP, Tiers.TIER1 + "WOODEN_STEP");
	// COCOA(Material.COCOA, Tiers.TIER1 + "COCOA");
	// SANDSTONE_STAIRS(Material.SANDSTONE_STAIRS, Tiers.TIER1 +
	// "SANDSTONE_STAIRS");
	// EMERALD_ORE(Material.EMERALD_ORE, Tiers.TIER1 + "EMERALD_ORE");
	// ENDER_CHEST(Material.ENDER_CHEST, Tiers.TIER1 + "ENDER_CHEST");
	// TRIPWIRE_HOOK(Material.TRIPWIRE_HOOK, Tiers.TIER1 + "TRIPWIRE_HOOK");
	// TRIPWIRE(Material.TRIPWIRE, Tiers.TIER1 + "TRIPWIRE");
	// EMERALD_BLOCK(Material.EMERALD_BLOCK, Tiers.TIER1 + "EMERALD_BLOCK");
	// SPRUCE_WOODEN_STAIRS(Material.SPRUCE_WOODEN_STAIRS, Tiers.TIER1 +
	// "SPRUCE_WOODEN_STAIRS");
	// BIRCH_WOODEN_STAIRS(Material.BIRCH_WOODEN_STAIRS, Tiers.TIER1 +
	// "BIRCH_WOODEN_STAIRS");
	// JUNGLE_WOODEN_STAIRS(Material.JUNGLE_WOODEN_STAIRS, Tiers.TIER1 +
	// "JUNGLE_WOODEN_STAIRS");
	// COMMAND(Material.COMMAND, Tiers.TIER1 + "COMMAND");
	// BEACON(Material.BEACON, Tiers.TIER1 + "BEACON");
	// COBBLE_WALL(Material.COBBLE_WALL, Tiers.TIER1 + "COBBLE_WALL");
	// FLOWER_POT(Material.FLOWER_POT, Tiers.TIER1 + "FLOWER_POT");
	// CARROT(Material.CARROT, Tiers.TIER1 + "CARROT");
	// POTATO(Material.POTATO, Tiers.TIER1 + "POTATO");
	// WOODEN_BUTTON(Material.WOODEN_BUTTON, Tiers.TIER1 + "WOODEN_BUTTON");
	// SKULL(Material.SKULL, Tiers.TIER1 + "SKULL");
	// ANVIL(Material.ANVIL, Tiers.TIER1 + "ANVIL");
	// TRAPPED_CHEST(Material.TRAPPED_CHEST, Tiers.TIER1 + "TRAPPED_CHEST");
	// GOLDEN_PLATE(Material.GOLDEN_PLATE, Tiers.TIER1 + "GOLDEN_PLATE");
	// IRON_PLATE(Material.IRON_PLATE, Tiers.TIER1 + "IRON_PLATE");
	// REDSTONE_COMPARATOR_OFF(Material.REDSTONE_COMPARATOR_OFF, Tiers.TIER1 +
	// "REDSTONE_COMPARATOR_OFF");
	// REDSTONE_COMPARATOR_ON(Material.REDSTONE_COMPARATOR_ON, Tiers.TIER1 +
	// "REDSTONE_COMPARATOR_ON");
	// DAYLIGHT_DETECTOR(Material.DAYLIGHT_DETECTOR, Tiers.TIER1 +
	// "DAYLIGHT_DETECTOR");
	// REDSTONE_BLOCK(Material.REDSTONE_BLOCK, Tiers.TIER1 + "REDSTONE_BLOCK");
	// QUARTZ_ORE(Material.QUARTZ_ORE, Tiers.TIER1 + "QUARTZ_ORE");
	// HOPPER(Material.HOPPER, Tiers.TIER1 + "HOPPER");
	// QUARTZ_BLOCK(Material.QUARTZ_BLOCK, Tiers.TIER1 + "QUARTZ_BLOCK");
	// QUARTZ_STAIRS(Material.QUARTZ_STAIRS, Tiers.TIER1 + "QUARTZ_STAIRS");
	// ACTIVATOR_RAIL(Material.ACTIVATOR_RAIL, Tiers.TIER1 + "ACTIVATOR_RAIL");
	// DROPPER(Material.DROPPER, Tiers.TIER1 + "DROPPER");
	// STAINED_CLAY(Material.STAINED_CLAY, Tiers.TIER1 + "STAINED_CLAY");
	// STAINED_GLASS_PANE(Material.STAINED_GLASS_PANE, Tiers.TIER1 +
	// "STAINED_GLASS_PANE");
	// LEAVES_2(Material.LEAVES_2, Tiers.TIER1 + "LEAVES_2");
	// LOG_2(Material.LOG_2, Tiers.TIER1 + "LOG_2");
	// ACACIA_STAIRS(Material.ACACIA_STAIRS, Tiers.TIER1 + "ACACIA_STAIRS");
	// DARK_OAK_STAIRS(Material.DARK_OAK_STAIRS, Tiers.TIER1 +
	// "DARK_OAK_STAIRS");
	// HAY_BLOCK(Material.HAY_BLOCK, Tiers.TIER1 + "HAY_BLOCK");
	// TERRACOTTA(Material.TERRACOTTA, Tiers.TIER1 + "TERRACOTTA");
	// COAL_BLOCK(Material.COAL_BLOCK, Tiers.TIER1 + "COAL_BLOCK");
	// PACKED_ICE(Material.PACKED_ICE, Tiers.TIER1 + "PACKED_ICE");
	// IRON_SHOVEL(Material.IRON_SHOVEL, Tiers.TIER1 + "IRON_SHOVEL");
	// IRON_PICKAXE(Material.IRON_PICKAXE, Tiers.TIER1 + "IRON_PICKAXE");
	// IRON_AXE(Material.IRON_AXE, Tiers.TIER1 + "IRON_AXE");
	// FLINT_AND_STEEL(Material.FLINT_AND_STEEL, Tiers.TIER1 +
	// "FLINT_AND_STEEL");
	// APPLE(Material.APPLE, Tiers.TIER1 + "APPLE");
	// BOW(Material.BOW, Tiers.TIER1 + "BOW");
	// ARROW(Material.ARROW, Tiers.TIER1 + "ARROW");
	// DIAMOND(Material.DIAMOND, Tiers.TIER1 + "DIAMOND");
	// IRON_SWORD(Material.IRON_SWORD, Tiers.TIER1 + "IRON_SWORD");
	// WOODEN_SWORD(Material.WOODEN_SWORD, Tiers.TIER1 + "WOODEN_SWORD");
	// WOODEN_SHOVEL(Material.WOODEN_SHOVEL, Tiers.TIER1 + "WOODEN_SHOVEL");
	// WOODEN_PICKAXE(Material.WOODEN_PICKAXE, Tiers.TIER1 + "WOODEN_PICKAXE");
	// WOODEN_AXE(Material.WOODEN_AXE, Tiers.TIER1 + "WOODEN_AXE");
	// STONE_SWORD(Material.STONE_SWORD, Tiers.TIER1 + "STONE_SWORD");
	// STONE_SHOVEL(Material.STONE_SHOVEL, Tiers.TIER1 + "STONE_SHOVEL");
	// STONE_PICKAXE(Material.STONE_PICKAXE, Tiers.TIER1 + "STONE_PICKAXE");
	// STONE_AXE(Material.STONE_AXE, Tiers.TIER1 + "STONE_AXE");
	// DIAMOND_SWORD(Material.DIAMOND_SWORD, Tiers.TIER1 + "DIAMOND_SWORD");
	// DIAMOND_SHOVEL(Material.DIAMOND_SHOVEL, Tiers.TIER1 + "DIAMOND_SHOVEL");
	// DIAMOND_PICKAXE(Material.DIAMOND_PICKAXE, Tiers.TIER1 +
	// "DIAMOND_PICKAXE");
	// DIAMOND_AXE(Material.DIAMOND_AXE, Tiers.TIER1 + "DIAMOND_AXE");
	// STICK(Material.STICK, Tiers.TIER1 + "STICK");
	// BOWL(Material.BOWL, Tiers.TIER1 + "BOWL");
	// MUSHROOM_SOUP(Material.MUSHROOM_STEW, Tiers.TIER1 + "MUSHROOM_SOUP");
	// GOLDEN_SWORD(Material.GOLDEN_SWORD, Tiers.TIER1 + "GOLDEN_SWORD");
	// GOLDEN_SHOVEL(Material.GOLDEN_SHOVEL, Tiers.TIER1 + "GOLDEN_SHOVEL");
	// GOLDEN_PICKAXE(Material.GOLDEN_PICKAXE, Tiers.TIER1 + "GOLDEN_PICKAXE");
	// GOLDEN_AXE(Material.GOLDEN_AXE, Tiers.TIER1 + "GOLDEN_AXE");
	// STRING(Material.STRING, Tiers.TIER1 + "STRING");
	// FEATHER(Material.FEATHER, Tiers.TIER1 + "FEATHER");
	// GUNPOWDER(Material.GUNPOWDER, Tiers.TIER1 + "GUNPOWDER");
	// WOODEN_HOE(Material.WOODEN_HOE, Tiers.TIER1 + "WOODEN_HOE");
	// STONE_HOE(Material.STONE_HOE, Tiers.TIER1 + "STONE_HOE");
	// IRON_HOE(Material.IRON_HOE, Tiers.TIER1 + "IRON_HOE");
	// DIAMOND_HOE(Material.DIAMOND_HOE, Tiers.TIER1 + "DIAMOND_HOE");
	// GOLDEN_HOE(Material.GOLDEN_HOE, Tiers.TIER1 + "GOLDEN_HOE");
	// WHEAT_SEEDS(Material.WHEAT_SEEDS, Tiers.TIER1 + "SEEDS");
	// WHEAT(Material.WHEAT, Tiers.TIER1 + "WHEAT");
	// BREAD(Material.BREAD, Tiers.TIER1 + "BREAD");
	// LEATHER_HELMET(Material.LEATHER_HELMET, Tiers.TIER1 + "LEATHER_HELMET");
	// LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE, Tiers.TIER1 +
	// "LEATHER_CHESTPLATE");
	// LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, Tiers.TIER1 +
	// "LEATHER_LEGGINGS");
	// LEATHER_BOOTS(Material.LEATHER_BOOTS, Tiers.TIER1 + "LEATHER_BOOTS");
	// CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, Tiers.TIER1 +
	// "CHAINMAIL_HELMET");
	// CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, Tiers.TIER1 +
	// "CHAINMAIL_CHESTPLATE");
	// CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, Tiers.TIER1 +
	// "CHAINMAIL_LEGGINGS");
	// CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, Tiers.TIER1 +
	// "CHAINMAIL_BOOTS");
	// IRON_HELMET(Material.IRON_HELMET, Tiers.TIER1 + "IRON_HELMET");
	// IRON_CHESTPLATE(Material.IRON_CHESTPLATE, Tiers.TIER1 +
	// "IRON_CHESTPLATE");
	// IRON_LEGGINGS(Material.IRON_LEGGINGS, Tiers.TIER1 + "IRON_LEGGINGS");
	// IRON_BOOTS(Material.IRON_BOOTS, Tiers.TIER1 + "IRON_BOOTS");
	// DIAMOND_HELMET(Material.DIAMOND_HELMET, Tiers.TIER1 + "DIAMOND_HELMET");
	// DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, Tiers.TIER1 +
	// "DIAMOND_CHESTPLATE");
	// DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, Tiers.TIER1 +
	// "DIAMOND_LEGGINGS");
	// DIAMOND_BOOTS(Material.DIAMOND_BOOTS, Tiers.TIER1 + "DIAMOND_BOOTS");
	// GOLDEN_HELMET(Material.GOLDEN_HELMET, Tiers.TIER1 + "GOLDEN_HELMET");
	// GOLDEN_CHESTPLATE(Material.GOLDEN_CHESTPLATE, Tiers.TIER1 +
	// "GOLDEN_CHESTPLATE");
	// GOLDEN_LEGGINGS(Material.GOLDEN_LEGGINGS, Tiers.TIER1 +
	// "GOLDEN_LEGGINGS");
	// GOLDEN_BOOTS(Material.GOLDEN_BOOTS, Tiers.TIER1 + "GOLDEN_BOOTS");
	// FLINT(Material.FLINT, Tiers.TIER1 + "FLINT");
	// PORK(Material.PORK, Tiers.TIER1 + "PORK");
	// GRILLED_PORK(Material.GRILLED_PORK, Tiers.TIER1 + "GRILLED_PORK");
	// PAINTING(Material.PAINTING, Tiers.TIER1 + "PAINTING");
	// GOLDEN_APPLE(Material.GOLDEN_APPLE, Tiers.TIER1 + "GOLDEN_APPLE");
	// SIGN(Material.SIGN, Tiers.TIER1 + "SIGN");
	// WOODEN_DOOR(Material.WOODEN_DOOR, Tiers.TIER1 + "WOODEN_DOOR");
	// BUCKET(Material.BUCKET, Tiers.TIER1 + "BUCKET");
	// WATER_BUCKET(Material.WATER_BUCKET, Tiers.TIER1 + "WATER_BUCKET");
	// LAVA_BUCKET(Material.LAVA_BUCKET, Tiers.TIER1 + "LAVA_BUCKET");
	// MINECART(Material.MINECART, Tiers.TIER1 + "MINECART");
	// SADDLE(Material.SADDLE, Tiers.TIER1 + "SADDLE");
	// IRON_DOOR(Material.IRON_DOOR, Tiers.TIER1 + "IRON_DOOR");
	// REDSTONE(Material.REDSTONE, Tiers.TIER1 + "REDSTONE");
	// SNOW_BALL(Material.SNOW_BALL, Tiers.TIER1 + "SNOW_BALL");
	// BOAT(Material.BOAT, Tiers.TIER1 + "BOAT");
	// LEATHER(Material.LEATHER, Tiers.TIER1 + "LEATHER");
	// MILK_BUCKET(Material.MILK_BUCKET, Tiers.TIER1 + "MILK_BUCKET");
	// CLAY_BRICK(Material.CLAY_BRICK, Tiers.TIER1 + "CLAY_BRICK");
	// CLAY_BALL(Material.CLAY_BALL, Tiers.TIER1 + "CLAY_BALL");
	// SUGAR_CANE(Material.SUGAR_CANE, Tiers.TIER1 + "SUGAR_CANE");
	// PAPER(Material.PAPER, Tiers.TIER1 + "PAPER");
	// BOOK(Material.BOOK, Tiers.TIER1 + "BOOK");
	// SLIME_BALL(Material.SLIME_BALL, Tiers.TIER1 + "SLIME_BALL");
	// STORAGE_MINECART(Material.STORAGE_MINECART, Tiers.TIER1 +
	// "STORAGE_MINECART");
	// POWERED_MINECART(Material.POWERED_MINECART, Tiers.TIER1 +
	// "POWERED_MINECART");
	// EGG(Material.EGG, Tiers.TIER1 + "EGG");
	// COMPASS(Material.COMPASS, Tiers.TIER1 + "COMPASS");
	// FISHING_ROD(Material.FISHING_ROD, Tiers.TIER1 + "FISHING_ROD");
	// WATCH(Material.WATCH, Tiers.TIER1 + "WATCH");
	// GLOWSTONE_DUST(Material.GLOWSTONE_DUST, Tiers.TIER1 + "GLOWSTONE_DUST");
	// RAW_FISH(Material.RAW_FISH, Tiers.TIER1 + "RAW_FISH");
	// COOKED_FISH(Material.COOKED_FISH, Tiers.TIER1 + "COOKED_FISH");
	// INK_SACK(Material.INK_SACK, Tiers.TIER1 + "INK_SACK");
	// BONE(Material.BONE, Tiers.TIER1 + "BONE");
	// SUGAR(Material.SUGAR, Tiers.TIER1 + "SUGAR");
	// CAKE(Material.CAKE, Tiers.TIER1 + "CAKE");
	// BED(Material.BED, Tiers.TIER1 + "BED");
	// DIODE(Material.DIODE, Tiers.TIER1 + "DIODE");
	// COOKIE(Material.COOKIE, Tiers.TIER1 + "COOKIE");
	// MAP(Material.MAP, Tiers.TIER1 + "MAP");
	// SHEARS(Material.SHEARS, Tiers.TIER1 + "SHEARS");
	// MELON(Material.MELON, Tiers.TIER1 + "MELON");
	// PUMPKIN_SEEDS(Material.PUMPKIN_SEEDS, Tiers.TIER1 + "PUMPKIN_SEEDS");
	// MELON_SEEDS(Material.MELON_SEEDS, Tiers.TIER1 + "MELON_SEEDS");
	// RAW_BEEF(Material.RAW_BEEF, Tiers.TIER1 + "RAW_BEEF");
	// COOKED_BEEF(Material.COOKED_BEEF, Tiers.TIER1 + "COOKED_BEEF");
	// RAW_CHICKEN(Material.RAW_CHICKEN, Tiers.TIER1 + "RAW_CHICKEN");
	// COOKED_CHICKEN(Material.COOKED_CHICKEN, Tiers.TIER1 + "COOKED_CHICKEN");
	// ROTTEN_FLESH(Material.ROTTEN_FLESH, Tiers.TIER1 + "ROTTEN_FLESH");
	// ENDER_PEARL(Material.ENDER_PEARL, Tiers.TIER1 + "ENDER_PEARL");
	// BLAZE_ROD(Material.BLAZE_ROD, Tiers.TIER1 + "BLAZE_ROD");
	// GHAST_TEAR(Material.GHAST_TEAR, Tiers.TIER1 + "GHAST_TEAR");
	// GOLDEN_NUGGET(Material.GOLDEN_NUGGET, Tiers.TIER1 + "GOLDEN_NUGGET");
	// NETHER_STALK(Material.NETHER_STALK, Tiers.TIER1 + "NETHER_STALK");
	// POTION(Material.POTION, Tiers.TIER1 + "POTION");
	// GLASS_BOTTLE(Material.GLASS_BOTTLE, Tiers.TIER1 + "GLASS_BOTTLE");
	// SPIDER_EYE(Material.SPIDER_EYE, Tiers.TIER1 + "SPIDER_EYE");
	// FERMENTED_SPIDER_EYE(Material.FERMENTED_SPIDER_EYE, Tiers.TIER1 +
	// "FERMENTED_SPIDER_EYE");
	// BLAZE_POWDER(Material.BLAZE_POWDER, Tiers.TIER1 + "BLAZE_POWDER");
	// MAGMA_CREAM(Material.MAGMA_CREAM, Tiers.TIER1 + "MAGMA_CREAM");
	// BREWING_STAND_ITEM(Material.BREWING_STAND_ITEM, Tiers.TIER1 +
	// "BREWING_STAND_ITEM");
	// CAULDRON_ITEM(Material.CAULDRON_ITEM, Tiers.TIER1 + "CAULDRON_ITEM");
	// EYE_OF_ENDER(Material.EYE_OF_ENDER, Tiers.TIER1 + "EYE_OF_ENDER");
	// SPECKLED_MELON(Material.SPECKLED_MELON, Tiers.TIER1 + "SPECKLED_MELON");
	// MONSTER_EGG(Material.MONSTER_EGG, Tiers.TIER1 + "MONSTER_EGG");
	// EXP_BOTTLE(Material.EXP_BOTTLE, Tiers.TIER1 + "EXP_BOTTLE");
	// FIREBALL(Material.FIREBALL, Tiers.TIER1 + "FIREBALL");
	// BOOK_AND_QUILL(Material.BOOK_AND_QUILL, Tiers.TIER1 + "BOOK_AND_QUILL");
	// WRITTEN_BOOK(Material.WRITTEN_BOOK, Tiers.TIER1 + "WRITTEN_BOOK");
	// EMERALD(Material.EMERALD, Tiers.TIER1 + "EMERALD");
	// ITEM_FRAME(Material.ITEM_FRAME, Tiers.TIER1 + "ITEM_FRAME");
	// FLOWER_POT_ITEM(Material.FLOWER_POT_ITEM, Tiers.TIER1 +
	// "FLOWER_POT_ITEM");
	// CARROT_ITEM(Material.CARROT_ITEM, Tiers.TIER1 + "CARROT_ITEM");
	// POTATO_ITEM(Material.POTATO_ITEM, Tiers.TIER1 + "POTATO_ITEM");
	// BAKED_POTATO(Material.BAKED_POTATO, Tiers.TIER1 + "BAKED_POTATO");
	// POISONOUS_POTATO(Material.POISONOUS_POTATO, Tiers.TIER1 +
	// "POISONOUS_POTATO");
	// EMPTY_MAP(Material.EMPTY_MAP, Tiers.TIER1 + "EMPTY_MAP");
	// GOLDEN_CARROT(Material.GOLDEN_CARROT, Tiers.TIER1 + "GOLDEN_CARROT");
	// SKULL_ITEM(Material.SKULL_ITEM, Tiers.TIER1 + "SKULL_ITEM");
	// CARROT_STICK(Material.CARROT_STICK, Tiers.TIER1 + "CARROT_STICK");
	// NETHER_STAR(Material.NETHER_STAR, Tiers.TIER1 + "NETHER_STAR");
	// PUMPKIN_PIE(Material.PUMPKIN_PIE, Tiers.TIER1 + "PUMPKIN_PIE");
	// FIREWORK(Material.FIREWORK, Tiers.TIER1 + "FIREWORK");
	// FIREWORK_CHARGE(Material.FIREWORK_CHARGE, Tiers.TIER1 +
	// "FIREWORK_CHARGE");
	// ENCHANTED_BOOK(Material.ENCHANTED_BOOK, Tiers.TIER1 + "ENCHANTED_BOOK");
	// REDSTONE_COMPARATOR(Material.REDSTONE_COMPARATOR, Tiers.TIER1 +
	// "REDSTONE_COMPARATOR");
	// NETHER_BRICK_ITEM(Material.NETHER_BRICK_ITEM, Tiers.TIER1 +
	// "NETHER_BRICK_ITEM");
	// QUARTZ(Material.QUARTZ, Tiers.TIER1 + "QUARTZ");
	// EXPLOSIVE_MINECART(Material.EXPLOSIVE_MINECART, Tiers.TIER1 +
	// "EXPLOSIVE_MINECART");
	// HOPPER_MINECART(Material.HOPPER_MINECART, Tiers.TIER1 +
	// "HOPPER_MINECART");
	// IRON_BARDING(Material.IRON_BARDING, Tiers.TIER1 + "IRON_BARDING");
	// GOLDEN_BARDING(Material.GOLDEN_BARDING, Tiers.TIER1 + "GOLDEN_BARDING");
	// DIAMOND_BARDING(Material.DIAMOND_BARDING, Tiers.TIER1 +
	// "DIAMOND_BARDING");
	// LEASH(Material.LEASH, Tiers.TIER1 + "LEASH");
	// NAME_TAG(Material.NAME_TAG, Tiers.TIER1 + "NAME_TAG");

}
