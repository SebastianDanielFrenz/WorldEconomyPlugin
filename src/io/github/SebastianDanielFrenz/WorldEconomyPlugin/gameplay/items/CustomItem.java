package io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomMaterialLevel;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.gameplay.CustomToolType;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.blast_furnaces.BasicBlastFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage1;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage2;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.furnaces.BasicFurnaceStage3;
import io.github.SebastianDanielFrenz.WorldEconomyPlugin.machines.sieves.BasicSieveStage1;

public enum CustomItem {

	COAL_ORE(Material.COAL_ORE, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	COAL(Material.COAL, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	CHARCOAL(Material.CHARCOAL, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	COAL_DUST(Material.COAL, Tier.TIER1, "Coal Dust", ItemCategory.PROCESSED_MATERIALS),
	ASH(Material.BONE_MEAL, Tier.TIER1, "Ash", ItemCategory.OTHER),

	// Iron Items
	IRON_ORE(Material.IRON_ORE, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	IRON_INGOT(Material.IRON_INGOT, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	IRON_PLATE(Material.PAPER, Tier.TIER1, "Iron Plate", ItemCategory.PROCESSED_MATERIALS),
	IRON_ROD(Material.STICK, Tier.TIER1, "Iron Rod", ItemCategory.PROCESSED_MATERIALS),

	IRON_PICKAXE(Material.IRON_PICKAXE, Tier.TIER1, ItemCategory.EQUIPMENT),
	IRON_HELMET(Material.IRON_HELMET, Tier.TIER1, ItemCategory.EQUIPMENT),
	IRON_CHESTPLATE(Material.IRON_CHESTPLATE, Tier.TIER1, ItemCategory.EQUIPMENT),
	IRON_LEGGINGS(Material.IRON_LEGGINGS, Tier.TIER1, ItemCategory.EQUIPMENT),
	IRON_BOOTS(Material.IRON_BOOTS, Tier.TIER1, ItemCategory.EQUIPMENT),

	// Redstone Items
	REDSTONE(Material.REDSTONE, Tier.TIER1, ItemCategory.TECHNOLOGY),

	// Steel Items
	STEEL_INGOT(Material.IRON_INGOT, Tier.TIER3, "Steel Ingot", ItemCategory.RAW_MATERIALS), // enchanted
	STEEL_PLATE(Material.PAPER, Tier.TIER3, "Steel Plate", ItemCategory.PROCESSED_MATERIALS), // enchanted
	STEEL_ROD(Material.STICK, Tier.TIER3, "Steel Rod", ItemCategory.PROCESSED_MATERIALS), // enchanted

	// Copper Items
	COPPER_INGOT(Material.GOLD_INGOT, Tier.TIER1, "Copper Ingot", ItemCategory.RAW_MATERIALS), // enchanted
	COPPER_PLATE(Material.PAPER, Tier.TIER1, "Copper Plate", ItemCategory.PROCESSED_MATERIALS), // enchanted

	// bronze items
	BRONZE_INGOT(Material.IRON_INGOT, Tier.TIER1, "Bronze Ingot", ItemCategory.RAW_MATERIALS),
	BRONZE_PLATE(Material.PAPER, Tier.TIER1, "Bronze Plate", ItemCategory.PROCESSED_MATERIALS),
	BRONZE_ROD(Material.STICK, Tier.TIER1, "Bronze Rod", ItemCategory.PROCESSED_MATERIALS),

	// aluminum
	ALUMINUM_INGOT(Material.IRON_INGOT, Tier.TIER1, "Aluminum Ingot", ItemCategory.RAW_MATERIALS),
	ALUMINUM_PLATE(Material.PAPER, Tier.TIER1, "Aluminum Plate", ItemCategory.PROCESSED_MATERIALS),
	ALUMINUM_ROD(Material.STICK, Tier.TIER1, "Aluminum Rod", ItemCategory.PROCESSED_MATERIALS),

	// TIN
	TIN_INGOT(Material.IRON_INGOT, Tier.TIER1, "Tin Ingot", ItemCategory.RAW_MATERIALS),
	TIN_PLATE(Material.PAPER, Tier.TIER1, "Tin Plate", ItemCategory.PROCESSED_MATERIALS),
	TIN_ROD(Material.STICK, Tier.TIER1, "Tin Rod", ItemCategory.PROCESSED_MATERIALS),

	// osmium
	OSMIUM_INGOT(Material.IRON_INGOT, Tier.TIER1, "Osmium Ingot", ItemCategory.RAW_MATERIALS),
	OSMIUM_PLATE(Material.PAPER, Tier.TIER1, "Osmium Plate", ItemCategory.PROCESSED_MATERIALS),
	OSMIUM_ROD(Material.STICK, Tier.TIER1, "Osmium Rod", ItemCategory.PROCESSED_MATERIALS),

	// lead
	LEAD_INGOT(Material.IRON_INGOT, Tier.TIER1, "Lead Ingot", ItemCategory.RAW_MATERIALS),
	LEAD_PLATE(Material.PAPER, Tier.TIER1, "Lead Plate", ItemCategory.PROCESSED_MATERIALS),
	LEAD_ROD(Material.STICK, Tier.TIER1, "Lead Rod", ItemCategory.PROCESSED_MATERIALS),

	// gold
	GOLD_ORE(Material.GOLD_ORE, Tier.TIER2, ItemCategory.RAW_MATERIALS),
	GOLD_NUGGET(Material.GOLD_NUGGET, Tier.TIER2, ItemCategory.RAW_MATERIALS),
	GOLD_INGOT(Material.GOLD_INGOT, Tier.TIER2, ItemCategory.RAW_MATERIALS),
	GOLD_ROD(Material.STICK, Tier.TIER2, "Gold Rod", ItemCategory.PROCESSED_MATERIALS),

	GOLD_CABLE(Material.GOLD_NUGGET, Tier.TIER2, "Gold Cable", ItemCategory.TECHNOLOGY),

	// silicon

	// materials
	CLAY_BALL(Material.CLAY_BALL, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	RAW_HARDENED_COBBLESTONE(Material.COBBLESTONE, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),
	RAW_HARDENED_COBBLESTONE_PLATE(Material.PAPER, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),
	HARDENED_COBBLESTONE(Material.COBBLESTONE, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),
	HARDENED_COBBLESTONE_PLATE(Material.PAPER, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),
	PROCESSED_COBBLESTONE(Material.STONE, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),
	PROCESSED_COBBLESTONE_PLATE(Material.PAPER, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),

	// fluids
	OIL_BUCKET(Material.LAVA_BUCKET, Tier.TIER1, "Oil Bucket", ItemCategory.RAW_MATERIALS),

	// buckets
	BUCKET(Material.BUCKET, Tier.TIER1, ItemCategory.OTHER),
	WATER_BUCKET(Material.WATER_BUCKET, Tier.TIER1, ItemCategory.OTHER),

	// clay buckets
	RAW_CLAY_BUCKET(Material.BUCKET, Tier.TIER1, ItemCategory.OTHER),
	CLAY_BUCKET(Material.BUCKET, Tier.TIER1, "Clay Bucket", ItemCategory.OTHER),
	WATER_CLAY_BUCKET(Material.WATER_BUCKET, Tier.TIER1, "Water Clay Bucket", ItemCategory.OTHER),

	// machines
	// furnaces
	BASIC_FURNACE_STAGE1(new BasicFurnaceStage1(null).getKategory().display, Tier.TIER1, "Basic Furnace Stage 1", ItemCategory.MACHINES),
	BASIC_FURNACE_STAGE2(new BasicFurnaceStage2(null).getKategory().display, Tier.TIER1, "Basic Furnace Stage 2", ItemCategory.MACHINES),
	BASIC_FURNACE_STAGE3(new BasicFurnaceStage3(null).getKategory().display, Tier.TIER1, "Basic Furnace Stage 3", ItemCategory.MACHINES),
	// sieves
	BASIC_SIEVE_STAGE1(new BasicSieveStage1(null).getKategory().display, Tier.TIER1, "Basic Sieve Stage 1", ItemCategory.MACHINES),
	// blast furnaces
	BASIC_BLAST_FURNACE_STAGE1(new BasicBlastFurnaceStage1(null).getKategory().display, Tier.TIER1, "Basic Blast Furnace Stage 1",
			ItemCategory.MACHINES),
	// camp fire
	CAMPFIRE(Material.CAMPFIRE, Tier.TIER1, ItemCategory.MACHINES),

	// tools
	// shovels
	WOODEN_SHOVEL(Material.WOODEN_SHOVEL, Tier.TIER1, "Wooden Shovel", ItemCategory.EQUIPMENT,
			new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.WOOD) }),
	COBBLESTONE_SHOVEL(Material.STONE_SHOVEL, Tier.TIER1, "Cobblestone Shovel", ItemCategory.EQUIPMENT,
			new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.COBBLESTONE) }),
	HARDENED_COBBLESTONE_SHOVEL(Material.STONE_SHOVEL, Tier.TIER1, "Hardened Cobblestone Shovel", ItemCategory.EQUIPMENT,
			new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.HARDENED_COBBLESTONE) }),
	PROCESSED_COBBLESTONE_SHOVEL(Material.STONE_SHOVEL, Tier.TIER1, "Processed Cobblestone Shovel", ItemCategory.EQUIPMENT,
			new ItemDetail[] { new ToolItemDetail(CustomToolType.SHOVEL, CustomMaterialLevel.PROCESSED_COBBLESTONE) }),

	// pickaxes
	PROCESSED_COBBLESTONE_PICKAXE(Material.STONE_PICKAXE, Tier.TIER1, "Processed Cobblestone Pickaxe", ItemCategory.EQUIPMENT,
			new ItemDetail[] { new ToolItemDetail(CustomToolType.PICKAXE, CustomMaterialLevel.PROCESSED_COBBLESTONE) }),

	// materials
	STICK(Material.STICK, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),

	// Vanilla Items
	STONE(Material.STONE, Tier.TIER1, ItemCategory.OTHER),
	GRASS_BLOCK(Material.GRASS_BLOCK, Tier.TIER1, ItemCategory.OTHER),
	DIRT(Material.DIRT, Tier.TIER1, ItemCategory.OTHER),
	COBBLESTONE(Material.COBBLESTONE, Tier.TIER1, ItemCategory.OTHER),
	OAK_PLANKS(Material.OAK_PLANKS, Tier.TIER1, ItemCategory.OTHER),
	OAK_LOG(Material.OAK_LOG, Tier.TIER1, ItemCategory.OTHER),
	COARSE_DIRT(Material.COARSE_DIRT, Tier.TIER1, ItemCategory.OTHER),
	SAND(Material.SAND, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	GRANITE(Material.GRANITE, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	POLISHED_GRANITE(Material.POLISHED_GRANITE, Tier.TIER1, ItemCategory.PROCESSED_MATERIALS),
	DIORITE(Material.DIORITE, Tier.TIER1, ItemCategory.RAW_MATERIALS),
	POLISHED_DIORITE(Material),
	ANDESITE(Material.ANDESITE, Tier.TIER1, ItemCategory.RAW_MATERIALS),;

	private CustomItem(Material base, Tier tier, String name, ItemCategory category) {
		base_material = base;
		raw_item_name = name;
		item_name = tier.color.toString() + name;
		this.tier = tier;
		this.category = category;
		details = new ItemDetail[] {};
	}

	private CustomItem(Material base, Tier tier, ItemCategory category) {
		base_material = base;
		raw_item_name = null;
		item_name = null;
		this.tier = tier;
		this.category = category;
		details = new ItemDetail[] {};
	}

	private CustomItem(Material base, Tier tier, String name, ItemCategory category, ItemDetail[] details) {
		base_material = base;
		raw_item_name = name;
		item_name = tier.color.toString() + name;
		this.tier = tier;
		this.category = category;
		this.details = details;
	}

	private CustomItem(Material base, Tier tier, ItemCategory category, ItemDetail[] details) {
		base_material = base;
		raw_item_name = null;
		item_name = null;
		this.tier = tier;
		this.category = category;
		this.details = details;
	}

	public final Material base_material;
	public final String raw_item_name;
	public final String item_name;
	public final Tier tier;
	public final ItemCategory category;
	public final ItemDetail[] details;

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

	public static CustomItem getItem(Material material, String name) {
		for (CustomItem item : CustomItem.values()) {
			if (item.base_material == material && name.equals(item.item_name)) {
				return item;
			}
		}
		return null;
	}

	public static CustomItem getItem(ItemStack stack) {
		return getItem(stack.getType(), stack.getItemMeta().getDisplayName());
	}

	public static void convertVanillaItemStack(ItemStack itemStack) {
		ItemMeta meta = itemStack.getItemMeta();
		if (meta.getDisplayName() == null) {
			meta.setDisplayName(getItem(itemStack).item_name);
		}
	}

	public boolean hasDetail(ItemDetailType type) {
		for (ItemDetail detail : details) {
			if (detail.type == type) {
				return true;
			}
		}
		return false;
	}

	public boolean hasDetail(ItemDetail detail) {
		for (ItemDetail _detail : details) {
			if (_detail.type == detail.type) {
				return _detail.data.equals(detail.data);
			}
		}
		return false;
	}

	public ItemDetail getDetail(ItemDetailType type) {
		for (ItemDetail detail : details) {
			if (detail.type == type) {
				return detail;
			}
		}
		return null;
	}

	// Coal

	// OAK_SAPLING(Material.OAK_SAPLING, Tier.TIER1 , "SAPLING");
	// BEDROCK(Material.BEDROCK, Tier.TIER1 , "BEDROCK");
	// WATER(Material.WATER, Tier.TIER1 , "WATER");
	// LAVA(Material.LAVA, Tier.TIER1 , "LAVA");
	// SAND(Material.SAND, Tier.TIER1 , "SAND");
	// GRAVEL(Material.GRAVEL, Tier.TIER1 , "GRAVEL");
	// OAK_LOG(Material.OAK_LOG, Tier.TIER1 , "LOG");
	// LEAVES(Material.LEAVES, Tier.TIER1 , "LEAVES");
	// SPONGE(Material.SPONGE, Tier.TIER1 , "SPONGE");
	// GLASS(Material.GLASS, Tier.TIER1 , "GLASS");
	// LAPIS_ORE(Material.LAPIS_ORE, Tier.TIER1 , "LAPIS_ORE");
	// LAPIS_BLOCK(Material.LAPIS_BLOCK, Tier.TIER1 , "LAPIS_BLOCK");
	// DISPENSER(Material.DISPENSER, Tier.TIER1 , "DISPENSER");
	// SANDSTONE(Material.SANDSTONE, Tier.TIER1 , "SANDSTONE");
	// NOTE_BLOCK(Material.NOTE_BLOCK, Tier.TIER1 , "NOTE_BLOCK");
	// POWERED_RAIL(Material.POWERED_RAIL, Tier.TIER1 , "POWERED_RAIL");
	// DETECTOR_RAIL(Material.DETECTOR_RAIL, Tier.TIER1 , "DETECTOR_RAIL");
	// DEAD_BUSH(Material.DEAD_BUSH, Tier.TIER1 , "DEAD_BUSH");
	// BROWN_MUSHROOM(Material.BROWN_MUSHROOM, Tier.TIER1 , "BROWN_MUSHROOM");
	// RED_MUSHROOM(Material.RED_MUSHROOM, Tier.TIER1 , "RED_MUSHROOM");
	// BRICK(Material.BRICK, Tier.TIER1 , "BRICK");
	// TNT(Material.TNT, Tier.TIER1 , "TNT");
	// BOOKSHELF(Material.BOOKSHELF, Tier.TIER1 , "BOOKSHELF");
	// MOSSY_COBBLESTONE(Material.MOSSY_COBBLESTONE, Tier.TIER1 ,
	// "MOSSY_COBBLESTONE");
	// OBSIDIAN(Material.OBSIDIAN, Tier.TIER1 , "OBSIDIAN");
	// TORCH(Material.TORCH, Tier.TIER1 , "TORCH");
	// FIRE(Material.FIRE, Tier.TIER1 , "FIRE");
	// CHEST(Material.CHEST, Tier.TIER1 , "CHEST");
	// FURNACE(Material.FURNACE, Tier.TIER1 , "FURNACE");
	// OAK_DOOR(Material.OAK_DOOR, Tier.TIER1 , "WOODEN_DOOR");
	// LADDER(Material.LADDER, Tier.TIER1 , "LADDER");
	// RAILS(Material.RAIL, Tier.TIER1 , "RAILS");
	// COBBLESTONE_STAIRS(Material.COBBLESTONE_STAIRS, Tier.TIER1 ,
	// "COBBLESTONE_STAIRS");
	// LEVER(Material.LEVER, Tier.TIER1 , "LEVER");
	// REDSTONE_ORE(Material.REDSTONE_ORE, Tier.TIER1 , "REDSTONE_ORE");
	// STONE_BUTTON(Material.STONE_BUTTON, Tier.TIER1 , "STONE_BUTTON");
	// SNOW(Material.SNOW, Tier.TIER1 , "SNOW");
	// ICE(Material.ICE, Tier.TIER1 , "ICE");
	// SNOW_BLOCK(Material.SNOW_BLOCK, Tier.TIER1 , "SNOW_BLOCK");
	// CACTUS(Material.CACTUS, Tier.TIER1 , "CACTUS");
	// CLAY(Material.CLAY, Tier.TIER1 , "CLAY");
	// JUKEBOX(Material.JUKEBOX, Tier.TIER1 , "JUKEBOX");
	// OAK_FENCE(Material.OAK_FENCE, Tier.TIER1 , "FENCE");
	// PUMPKIN(Material.PUMPKIN, Tier.TIER1 , "PUMPKIN");
	// NETHERRACK(Material.NETHERRACK, Tier.TIER1 , "NETHERRACK");
	// SOUL_SAND(Material.SOUL_SAND, Tier.TIER1 , "SOUL_SAND");
	// GLOWSTONE(Material.GLOWSTONE, Tier.TIER1 , "GLOWSTONE");
	// JACK_O_LANTERN(Material.JACK_O_LANTERN, Tier.TIER1 , "JACK_O_LANTERN");
	// STONE_BRICK(Material.STONE_BRICKS, Tier.TIER1 , "SMOOTH_BRICK");
	// PUMPKIN_STEM(Material.PUMPKIN_STEM, Tier.TIER1 , "PUMPKIN_STEM");
	// MELON_STEM(Material.MELON_STEM, Tier.TIER1 , "MELON_STEM");
	// VINE(Material.VINE, Tier.TIER1 , "VINE");
	// BRICK_STAIRS(Material.BRICK_STAIRS, Tier.TIER1 , "BRICK_STAIRS");
	// SMOOTH_STAIRS(Material.SMOOTH_STAIRS, Tier.TIER1 , "SMOOTH_STAIRS");
	// MYCEL(Material.MYCEL, Tier.TIER1 , "MYCEL");
	// WATER_LILY(Material.WATER_LILY, Tier.TIER1 , "WATER_LILY");
	// NETHER_BRICK(Material.NETHER_BRICK, Tier.TIER1 , "NETHER_BRICK");
	// NETHER_FENCE(Material.NETHER_FENCE, Tier.TIER1 , "NETHER_FENCE");
	// NETHER_BRICK_STAIRS(Material.NETHER_BRICK_STAIRS, Tier.TIER1 ,
	// "NETHER_BRICK_STAIRS");
	// NETHER_WARTS(Material.NETHER_WARTS, Tier.TIER1 , "NETHER_WARTS");
	// ENCHANTMENT_TABLE(Material.ENCHANTMENT_TABLE, Tier.TIER1 ,
	// "ENCHANTMENT_TABLE");
	// BREWING_STAND(Material.BREWING_STAND, Tier.TIER1 , "BREWING_STAND");
	// CAULDRON(Material.CAULDRON, Tier.TIER1 , "CAULDRON");
	// ENDER_PORTAL(Material.ENDER_PORTAL, Tier.TIER1 , "ENDER_PORTAL");
	// ENDER_PORTAL_FRAME(Material.ENDER_PORTAL_FRAME, Tier.TIER1 ,
	// "ENDER_PORTAL_FRAME");
	// ENDER_STONE(Material.ENDER_STONE, Tier.TIER1 , "ENDER_STONE");
	// DRAGON_EGG(Material.DRAGON_EGG, Tier.TIER1 , "DRAGON_EGG");
	// REDSTONE_LAMP_OFF(Material.REDSTONE_LAMP_OFF, Tier.TIER1 ,
	// "REDSTONE_LAMP_OFF");
	// REDSTONE_LAMP_ON(Material.REDSTONE_LAMP_ON, Tier.TIER1 ,
	// "REDSTONE_LAMP_ON");
	// WOODEN_DOUBLE_STEP(Material.WOODEN_DOUBLE_STEP, Tier.TIER1 ,
	// "WOODEN_DOUBLE_STEP");
	// WOODEN_STEP(Material.WOODEN_STEP, Tier.TIER1 , "WOODEN_STEP");
	// COCOA(Material.COCOA, Tier.TIER1 , "COCOA");
	// SANDSTONE_STAIRS(Material.SANDSTONE_STAIRS, Tier.TIER1 ,
	// "SANDSTONE_STAIRS");
	// EMERALD_ORE(Material.EMERALD_ORE, Tier.TIER1 , "EMERALD_ORE");
	// ENDER_CHEST(Material.ENDER_CHEST, Tier.TIER1 , "ENDER_CHEST");
	// TRIPWIRE_HOOK(Material.TRIPWIRE_HOOK, Tier.TIER1 , "TRIPWIRE_HOOK");
	// TRIPWIRE(Material.TRIPWIRE, Tier.TIER1 , "TRIPWIRE");
	// EMERALD_BLOCK(Material.EMERALD_BLOCK, Tier.TIER1 , "EMERALD_BLOCK");
	// SPRUCE_WOODEN_STAIRS(Material.SPRUCE_WOODEN_STAIRS, Tier.TIER1 ,
	// "SPRUCE_WOODEN_STAIRS");
	// BIRCH_WOODEN_STAIRS(Material.BIRCH_WOODEN_STAIRS, Tier.TIER1 ,
	// "BIRCH_WOODEN_STAIRS");
	// JUNGLE_WOODEN_STAIRS(Material.JUNGLE_WOODEN_STAIRS, Tier.TIER1 ,
	// "JUNGLE_WOODEN_STAIRS");
	// COMMAND(Material.COMMAND, Tier.TIER1 , "COMMAND");
	// BEACON(Material.BEACON, Tier.TIER1 , "BEACON");
	// COBBLE_WALL(Material.COBBLE_WALL, Tier.TIER1 , "COBBLE_WALL");
	// FLOWER_POT(Material.FLOWER_POT, Tier.TIER1 , "FLOWER_POT");
	// CARROT(Material.CARROT, Tier.TIER1 , "CARROT");
	// POTATO(Material.POTATO, Tier.TIER1 , "POTATO");
	// WOODEN_BUTTON(Material.WOODEN_BUTTON, Tier.TIER1 , "WOODEN_BUTTON");
	// SKULL(Material.SKULL, Tier.TIER1 , "SKULL");
	// ANVIL(Material.ANVIL, Tier.TIER1 , "ANVIL");
	// TRAPPED_CHEST(Material.TRAPPED_CHEST, Tier.TIER1 , "TRAPPED_CHEST");
	// GOLDEN_PLATE(Material.GOLDEN_PLATE, Tier.TIER1 , "GOLDEN_PLATE");
	// IRON_PLATE(Material.IRON_PLATE, Tier.TIER1 , "IRON_PLATE");
	// REDSTONE_COMPARATOR_OFF(Material.REDSTONE_COMPARATOR_OFF, Tier.TIER1 ,
	// "REDSTONE_COMPARATOR_OFF");
	// REDSTONE_COMPARATOR_ON(Material.REDSTONE_COMPARATOR_ON, Tier.TIER1 ,
	// "REDSTONE_COMPARATOR_ON");
	// DAYLIGHT_DETECTOR(Material.DAYLIGHT_DETECTOR, Tier.TIER1 ,
	// "DAYLIGHT_DETECTOR");
	// REDSTONE_BLOCK(Material.REDSTONE_BLOCK, Tier.TIER1 , "REDSTONE_BLOCK");
	// QUARTZ_ORE(Material.QUARTZ_ORE, Tier.TIER1 , "QUARTZ_ORE");
	// HOPPER(Material.HOPPER, Tier.TIER1 , "HOPPER");
	// QUARTZ_BLOCK(Material.QUARTZ_BLOCK, Tier.TIER1 , "QUARTZ_BLOCK");
	// QUARTZ_STAIRS(Material.QUARTZ_STAIRS, Tier.TIER1 , "QUARTZ_STAIRS");
	// ACTIVATOR_RAIL(Material.ACTIVATOR_RAIL, Tier.TIER1 , "ACTIVATOR_RAIL");
	// DROPPER(Material.DROPPER, Tier.TIER1 , "DROPPER");
	// STAINED_CLAY(Material.STAINED_CLAY, Tier.TIER1 , "STAINED_CLAY");
	// STAINED_GLASS_PANE(Material.STAINED_GLASS_PANE, Tier.TIER1 ,
	// "STAINED_GLASS_PANE");
	// LEAVES_2(Material.LEAVES_2, Tier.TIER1 , "LEAVES_2");
	// LOG_2(Material.LOG_2, Tier.TIER1 , "LOG_2");
	// ACACIA_STAIRS(Material.ACACIA_STAIRS, Tier.TIER1 , "ACACIA_STAIRS");
	// DARK_OAK_STAIRS(Material.DARK_OAK_STAIRS, Tier.TIER1 ,
	// "DARK_OAK_STAIRS");
	// HAY_BLOCK(Material.HAY_BLOCK, Tier.TIER1 , "HAY_BLOCK");
	// TERRACOTTA(Material.TERRACOTTA, Tier.TIER1 , "TERRACOTTA");
	// COAL_BLOCK(Material.COAL_BLOCK, Tier.TIER1 , "COAL_BLOCK");
	// PACKED_ICE(Material.PACKED_ICE, Tier.TIER1 , "PACKED_ICE");
	// IRON_SHOVEL(Material.IRON_SHOVEL, Tier.TIER1 , "IRON_SHOVEL");
	// IRON_PICKAXE(Material.IRON_PICKAXE, Tier.TIER1 , "IRON_PICKAXE");
	// IRON_AXE(Material.IRON_AXE, Tier.TIER1 , "IRON_AXE");
	// FLINT_AND_STEEL(Material.FLINT_AND_STEEL, Tier.TIER1 ,
	// "FLINT_AND_STEEL");
	// APPLE(Material.APPLE, Tier.TIER1 , "APPLE");
	// BOW(Material.BOW, Tier.TIER1 , "BOW");
	// ARROW(Material.ARROW, Tier.TIER1 , "ARROW");
	// DIAMOND(Material.DIAMOND, Tier.TIER1 , "DIAMOND");
	// IRON_SWORD(Material.IRON_SWORD, Tier.TIER1 , "IRON_SWORD");
	// WOODEN_SWORD(Material.WOODEN_SWORD, Tier.TIER1 , "WOODEN_SWORD");
	// WOODEN_SHOVEL(Material.WOODEN_SHOVEL, Tier.TIER1 , "WOODEN_SHOVEL");
	// WOODEN_PICKAXE(Material.WOODEN_PICKAXE, Tier.TIER1 , "WOODEN_PICKAXE");
	// WOODEN_AXE(Material.WOODEN_AXE, Tier.TIER1 , "WOODEN_AXE");
	// STONE_SWORD(Material.STONE_SWORD, Tier.TIER1 , "STONE_SWORD");
	// STONE_SHOVEL(Material.STONE_SHOVEL, Tier.TIER1 , "STONE_SHOVEL");
	// STONE_PICKAXE(Material.STONE_PICKAXE, Tier.TIER1 , "STONE_PICKAXE");
	// STONE_AXE(Material.STONE_AXE, Tier.TIER1 , "STONE_AXE");
	// DIAMOND_SWORD(Material.DIAMOND_SWORD, Tier.TIER1 , "DIAMOND_SWORD");
	// DIAMOND_SHOVEL(Material.DIAMOND_SHOVEL, Tier.TIER1 , "DIAMOND_SHOVEL");
	// DIAMOND_PICKAXE(Material.DIAMOND_PICKAXE, Tier.TIER1 ,
	// "DIAMOND_PICKAXE");
	// DIAMOND_AXE(Material.DIAMOND_AXE, Tier.TIER1 , "DIAMOND_AXE");
	// STICK(Material.STICK, Tier.TIER1 , "STICK");
	// BOWL(Material.BOWL, Tier.TIER1 , "BOWL");
	// MUSHROOM_SOUP(Material.MUSHROOM_STEW, Tier.TIER1 , "MUSHROOM_SOUP");
	// GOLDEN_SWORD(Material.GOLDEN_SWORD, Tier.TIER1 , "GOLDEN_SWORD");
	// GOLDEN_SHOVEL(Material.GOLDEN_SHOVEL, Tier.TIER1 , "GOLDEN_SHOVEL");
	// GOLDEN_PICKAXE(Material.GOLDEN_PICKAXE, Tier.TIER1 , "GOLDEN_PICKAXE");
	// GOLDEN_AXE(Material.GOLDEN_AXE, Tier.TIER1 , "GOLDEN_AXE");
	// STRING(Material.STRING, Tier.TIER1 , "STRING");
	// FEATHER(Material.FEATHER, Tier.TIER1 , "FEATHER");
	// GUNPOWDER(Material.GUNPOWDER, Tier.TIER1 , "GUNPOWDER");
	// WOODEN_HOE(Material.WOODEN_HOE, Tier.TIER1 , "WOODEN_HOE");
	// STONE_HOE(Material.STONE_HOE, Tier.TIER1 , "STONE_HOE");
	// IRON_HOE(Material.IRON_HOE, Tier.TIER1 , "IRON_HOE");
	// DIAMOND_HOE(Material.DIAMOND_HOE, Tier.TIER1 , "DIAMOND_HOE");
	// GOLDEN_HOE(Material.GOLDEN_HOE, Tier.TIER1 , "GOLDEN_HOE");
	// WHEAT_SEEDS(Material.WHEAT_SEEDS, Tier.TIER1 , "SEEDS");
	// WHEAT(Material.WHEAT, Tier.TIER1 , "WHEAT");
	// BREAD(Material.BREAD, Tier.TIER1 , "BREAD");
	// LEATHER_HELMET(Material.LEATHER_HELMET, Tier.TIER1 , "LEATHER_HELMET");
	// LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE, Tier.TIER1 ,
	// "LEATHER_CHESTPLATE");
	// LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, Tier.TIER1 ,
	// "LEATHER_LEGGINGS");
	// LEATHER_BOOTS(Material.LEATHER_BOOTS, Tier.TIER1 , "LEATHER_BOOTS");
	// CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, Tier.TIER1 ,
	// "CHAINMAIL_HELMET");
	// CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, Tier.TIER1 ,
	// "CHAINMAIL_CHESTPLATE");
	// CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, Tier.TIER1 ,
	// "CHAINMAIL_LEGGINGS");
	// CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, Tier.TIER1 ,
	// "CHAINMAIL_BOOTS");
	// IRON_HELMET(Material.IRON_HELMET, Tier.TIER1 , "IRON_HELMET");
	// IRON_CHESTPLATE(Material.IRON_CHESTPLATE, Tier.TIER1 ,
	// "IRON_CHESTPLATE");
	// IRON_LEGGINGS(Material.IRON_LEGGINGS, Tier.TIER1 , "IRON_LEGGINGS");
	// IRON_BOOTS(Material.IRON_BOOTS, Tier.TIER1 , "IRON_BOOTS");
	// DIAMOND_HELMET(Material.DIAMOND_HELMET, Tier.TIER1 , "DIAMOND_HELMET");
	// DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, Tier.TIER1 ,
	// "DIAMOND_CHESTPLATE");
	// DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, Tier.TIER1 ,
	// "DIAMOND_LEGGINGS");
	// DIAMOND_BOOTS(Material.DIAMOND_BOOTS, Tier.TIER1 , "DIAMOND_BOOTS");
	// GOLDEN_HELMET(Material.GOLDEN_HELMET, Tier.TIER1 , "GOLDEN_HELMET");
	// GOLDEN_CHESTPLATE(Material.GOLDEN_CHESTPLATE, Tier.TIER1 ,
	// "GOLDEN_CHESTPLATE");
	// GOLDEN_LEGGINGS(Material.GOLDEN_LEGGINGS, Tier.TIER1 ,
	// "GOLDEN_LEGGINGS");
	// GOLDEN_BOOTS(Material.GOLDEN_BOOTS, Tier.TIER1 , "GOLDEN_BOOTS");
	// FLINT(Material.FLINT, Tier.TIER1 , "FLINT");
	// PORK(Material.PORK, Tier.TIER1 , "PORK");
	// GRILLED_PORK(Material.GRILLED_PORK, Tier.TIER1 , "GRILLED_PORK");
	// PAINTING(Material.PAINTING, Tier.TIER1 , "PAINTING");
	// GOLDEN_APPLE(Material.GOLDEN_APPLE, Tier.TIER1 , "GOLDEN_APPLE");
	// SIGN(Material.SIGN, Tier.TIER1 , "SIGN");
	// WOODEN_DOOR(Material.WOODEN_DOOR, Tier.TIER1 , "WOODEN_DOOR");
	// BUCKET(Material.BUCKET, Tier.TIER1 , "BUCKET");
	// WATER_BUCKET(Material.WATER_BUCKET, Tier.TIER1 , "WATER_BUCKET");
	// LAVA_BUCKET(Material.LAVA_BUCKET, Tier.TIER1 , "LAVA_BUCKET");
	// MINECART(Material.MINECART, Tier.TIER1 , "MINECART");
	// SADDLE(Material.SADDLE, Tier.TIER1 , "SADDLE");
	// IRON_DOOR(Material.IRON_DOOR, Tier.TIER1 , "IRON_DOOR");
	// REDSTONE(Material.REDSTONE, Tier.TIER1 , "REDSTONE");
	// SNOW_BALL(Material.SNOW_BALL, Tier.TIER1 , "SNOW_BALL");
	// BOAT(Material.BOAT, Tier.TIER1 , "BOAT");
	// LEATHER(Material.LEATHER, Tier.TIER1 , "LEATHER");
	// MILK_BUCKET(Material.MILK_BUCKET, Tier.TIER1 , "MILK_BUCKET");
	// CLAY_BRICK(Material.CLAY_BRICK, Tier.TIER1 , "CLAY_BRICK");
	// CLAY_BALL(Material.CLAY_BALL, Tier.TIER1 , "CLAY_BALL");
	// SUGAR_CANE(Material.SUGAR_CANE, Tier.TIER1 , "SUGAR_CANE");
	// PAPER(Material.PAPER, Tier.TIER1 , "PAPER");
	// BOOK(Material.BOOK, Tier.TIER1 , "BOOK");
	// SLIME_BALL(Material.SLIME_BALL, Tier.TIER1 , "SLIME_BALL");
	// STORAGE_MINECART(Material.STORAGE_MINECART, Tier.TIER1 ,
	// "STORAGE_MINECART");
	// POWERED_MINECART(Material.POWERED_MINECART, Tier.TIER1 ,
	// "POWERED_MINECART");
	// EGG(Material.EGG, Tier.TIER1 , "EGG");
	// COMPASS(Material.COMPASS, Tier.TIER1 , "COMPASS");
	// FISHING_ROD(Material.FISHING_ROD, Tier.TIER1 , "FISHING_ROD");
	// WATCH(Material.WATCH, Tier.TIER1 , "WATCH");
	// GLOWSTONE_DUST(Material.GLOWSTONE_DUST, Tier.TIER1 , "GLOWSTONE_DUST");
	// RAW_FISH(Material.RAW_FISH, Tier.TIER1 , "RAW_FISH");
	// COOKED_FISH(Material.COOKED_FISH, Tier.TIER1 , "COOKED_FISH");
	// INK_SACK(Material.INK_SACK, Tier.TIER1 , "INK_SACK");
	// BONE(Material.BONE, Tier.TIER1 , "BONE");
	// SUGAR(Material.SUGAR, Tier.TIER1 , "SUGAR");
	// CAKE(Material.CAKE, Tier.TIER1 , "CAKE");
	// BED(Material.BED, Tier.TIER1 , "BED");
	// DIODE(Material.DIODE, Tier.TIER1 , "DIODE");
	// COOKIE(Material.COOKIE, Tier.TIER1 , "COOKIE");
	// MAP(Material.MAP, Tier.TIER1 , "MAP");
	// SHEARS(Material.SHEARS, Tier.TIER1 , "SHEARS");
	// MELON(Material.MELON, Tier.TIER1 , "MELON");
	// PUMPKIN_SEEDS(Material.PUMPKIN_SEEDS, Tier.TIER1 , "PUMPKIN_SEEDS");
	// MELON_SEEDS(Material.MELON_SEEDS, Tier.TIER1 , "MELON_SEEDS");
	// RAW_BEEF(Material.RAW_BEEF, Tier.TIER1 , "RAW_BEEF");
	// COOKED_BEEF(Material.COOKED_BEEF, Tier.TIER1 , "COOKED_BEEF");
	// RAW_CHICKEN(Material.RAW_CHICKEN, Tier.TIER1 , "RAW_CHICKEN");
	// COOKED_CHICKEN(Material.COOKED_CHICKEN, Tier.TIER1 , "COOKED_CHICKEN");
	// ROTTEN_FLESH(Material.ROTTEN_FLESH, Tier.TIER1 , "ROTTEN_FLESH");
	// ENDER_PEARL(Material.ENDER_PEARL, Tier.TIER1 , "ENDER_PEARL");
	// BLAZE_ROD(Material.BLAZE_ROD, Tier.TIER1 , "BLAZE_ROD");
	// GHAST_TEAR(Material.GHAST_TEAR, Tier.TIER1 , "GHAST_TEAR");
	// GOLDEN_NUGGET(Material.GOLDEN_NUGGET, Tier.TIER1 , "GOLDEN_NUGGET");
	// NETHER_STALK(Material.NETHER_STALK, Tier.TIER1 , "NETHER_STALK");
	// POTION(Material.POTION, Tier.TIER1 , "POTION");
	// GLASS_BOTTLE(Material.GLASS_BOTTLE, Tier.TIER1 , "GLASS_BOTTLE");
	// SPIDER_EYE(Material.SPIDER_EYE, Tier.TIER1 , "SPIDER_EYE");
	// FERMENTED_SPIDER_EYE(Material.FERMENTED_SPIDER_EYE, Tier.TIER1 ,
	// "FERMENTED_SPIDER_EYE");
	// BLAZE_POWDER(Material.BLAZE_POWDER, Tier.TIER1 , "BLAZE_POWDER");
	// MAGMA_CREAM(Material.MAGMA_CREAM, Tier.TIER1 , "MAGMA_CREAM");
	// BREWING_STAND_ITEM(Material.BREWING_STAND_ITEM, Tier.TIER1 ,
	// "BREWING_STAND_ITEM");
	// CAULDRON_ITEM(Material.CAULDRON_ITEM, Tier.TIER1 , "CAULDRON_ITEM");
	// EYE_OF_ENDER(Material.EYE_OF_ENDER, Tier.TIER1 , "EYE_OF_ENDER");
	// SPECKLED_MELON(Material.SPECKLED_MELON, Tier.TIER1 , "SPECKLED_MELON");
	// MONSTER_EGG(Material.MONSTER_EGG, Tier.TIER1 , "MONSTER_EGG");
	// EXP_BOTTLE(Material.EXP_BOTTLE, Tier.TIER1 , "EXP_BOTTLE");
	// FIREBALL(Material.FIREBALL, Tier.TIER1 , "FIREBALL");
	// BOOK_AND_QUILL(Material.BOOK_AND_QUILL, Tier.TIER1 , "BOOK_AND_QUILL");
	// WRITTEN_BOOK(Material.WRITTEN_BOOK, Tier.TIER1 , "WRITTEN_BOOK");
	// EMERALD(Material.EMERALD, Tier.TIER1 , "EMERALD");
	// ITEM_FRAME(Material.ITEM_FRAME, Tier.TIER1 , "ITEM_FRAME");
	// FLOWER_POT_ITEM(Material.FLOWER_POT_ITEM, Tier.TIER1 ,
	// "FLOWER_POT_ITEM");
	// CARROT_ITEM(Material.CARROT_ITEM, Tier.TIER1 , "CARROT_ITEM");
	// POTATO_ITEM(Material.POTATO_ITEM, Tier.TIER1 , "POTATO_ITEM");
	// BAKED_POTATO(Material.BAKED_POTATO, Tier.TIER1 , "BAKED_POTATO");
	// POISONOUS_POTATO(Material.POISONOUS_POTATO, Tier.TIER1 ,
	// "POISONOUS_POTATO");
	// EMPTY_MAP(Material.EMPTY_MAP, Tier.TIER1 , "EMPTY_MAP");
	// GOLDEN_CARROT(Material.GOLDEN_CARROT, Tier.TIER1 , "GOLDEN_CARROT");
	// SKULL_ITEM(Material.SKULL_ITEM, Tier.TIER1 , "SKULL_ITEM");
	// CARROT_STICK(Material.CARROT_STICK, Tier.TIER1 , "CARROT_STICK");
	// NETHER_STAR(Material.NETHER_STAR, Tier.TIER1 , "NETHER_STAR");
	// PUMPKIN_PIE(Material.PUMPKIN_PIE, Tier.TIER1 , "PUMPKIN_PIE");
	// FIREWORK(Material.FIREWORK, Tier.TIER1 , "FIREWORK");
	// FIREWORK_CHARGE(Material.FIREWORK_CHARGE, Tier.TIER1 ,
	// "FIREWORK_CHARGE");
	// ENCHANTED_BOOK(Material.ENCHANTED_BOOK, Tier.TIER1 , "ENCHANTED_BOOK");
	// REDSTONE_COMPARATOR(Material.REDSTONE_COMPARATOR, Tier.TIER1 ,
	// "REDSTONE_COMPARATOR");
	// NETHER_BRICK_ITEM(Material.NETHER_BRICK_ITEM, Tier.TIER1 ,
	// "NETHER_BRICK_ITEM");
	// QUARTZ(Material.QUARTZ, Tier.TIER1 , "QUARTZ");
	// EXPLOSIVE_MINECART(Material.EXPLOSIVE_MINECART, Tier.TIER1 ,
	// "EXPLOSIVE_MINECART");
	// HOPPER_MINECART(Material.HOPPER_MINECART, Tier.TIER1 ,
	// "HOPPER_MINECART");
	// IRON_BARDING(Material.IRON_BARDING, Tier.TIER1 , "IRON_BARDING");
	// GOLDEN_BARDING(Material.GOLDEN_BARDING, Tier.TIER1 , "GOLDEN_BARDING");
	// DIAMOND_BARDING(Material.DIAMOND_BARDING, Tier.TIER1 ,
	// "DIAMOND_BARDING");
	// LEASH(Material.LEASH, Tier.TIER1 , "LEASH");
	// NAME_TAG(Material.NAME_TAG, Tier.TIER1 , "NAME_TAG");

}
